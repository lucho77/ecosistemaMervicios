package com.examenes.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.examenes.util.APIException;
import com.examenes.util.Error;
import com.examenes.util.ErrorBuilder;
import com.examenes.util.ErrorCode;
import com.examenes.util.MsInfoUtil;
import com.examenes.util.Response;
import com.examenes.util.ValidationException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String OBJETO_REQUEST_INVALIDO = "Objeto request inválido";
	private static final String NO_OBTENER_CUERPO_MSG = "No se pudo obtener el cuerpo del mensaje";
	private static final String SE_HA_PRODUCIDO_UN_ERROR = "Se ha producido un error, intente nuevamente en unos minutos";
	private static final String ERROR_INTERNO = "Se ha producido un error, intente nuevamente en unos minutos";

	@ExceptionHandler(value = { ExamenException.class })
	protected ResponseEntity<Response> handleCuentaCorrientePolizaException(ExamenException ex, WebRequest request) {
		logger.error("handleExamenException() - validationErrorMsg: {0}", ex);
        return buildExceptionResponse(request, HttpStatus.SC_BAD_REQUEST, Arrays.asList(ErrorBuilder.buildError(
						ErrorCode.BODY_REQUEST_MAL_FORMADO.toString(), ex.getMessage(), OBJETO_REQUEST_INVALIDO)));
	}

	@ExceptionHandler(value = { org.springframework.web.bind.MethodArgumentNotValidException.class })
	protected ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		logger.error("handleMethodArgumentNotValidException() - validationErrorMsg: {0}", ex);

		String validationErrorMsg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return buildExceptionResponse(request, HttpStatus.SC_BAD_REQUEST, Arrays.asList(ErrorBuilder.buildError(
				ErrorCode.ERROR_DE_VALIDACION.toString(), validationErrorMsg, OBJETO_REQUEST_INVALIDO)));
	}

	@ExceptionHandler(value = { org.springframework.http.converter.HttpMessageNotReadableException.class })
	protected ResponseEntity<Response> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
			WebRequest request) {
		logger.error("handleHttpMessageNotReadableException(): validationErrorMsg: {0}", ex);
		return buildExceptionResponse(request, HttpStatus.SC_BAD_REQUEST, Arrays.asList(ErrorBuilder.buildError(
				ErrorCode.BODY_REQUEST_MAL_FORMADO.toString(), NO_OBTENER_CUERPO_MSG, OBJETO_REQUEST_INVALIDO)));
	}

	@ExceptionHandler(value = { java.lang.RuntimeException.class })
	protected ResponseEntity<Response> handleHttpRuntimeException(RuntimeException ex, WebRequest request) {
		logger.error("handleHttpRuntimeException: {0}", ex);
		return buildExceptionResponse(request, HttpStatus.SC_INTERNAL_SERVER_ERROR, Arrays.asList(ErrorBuilder.buildError(
				ErrorCode.ERROR_INTERNO_DEL_SERVIDOR.toString(), SE_HA_PRODUCIDO_UN_ERROR, ERROR_INTERNO)));
	}

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Response> handleValidationException(ValidationException ex, WebRequest request) {
		logger.error("handleValidationException: {0}", ex);
		List<Error> errors = ex.getErrors() != null ? ex.getErrors() : new ArrayList<>();
		if (errors.isEmpty()) {
			// la exception tiene un unico error
			errors.add(ErrorBuilder.buildError(ex.getCode().toString(), ex.getDescription(), ex.getMessage()));
		}
		return buildExceptionResponse(request, ex.getStatus(), errors);
	}

	@ExceptionHandler(APIException.class)
	protected ResponseEntity<Response> handleAPIException(APIException ex, WebRequest request) {
		logger.error("handleAPIException: {0}", ex);
		return buildExceptionResponse(request, ex.getStatus(), ErrorBuilder.buildError(ex));
	}

	/**
	 * Retorna el Response con la info de la Exception
	 *
	 * @param request
	 * @param status
	 * @param errors
	 * @return
	 */
	private ResponseEntity<Response> buildExceptionResponse(WebRequest request, Integer status, List<Error> errors) {
		Date date = new Date();
		Response response = new Response();
		response.setPath(request == null ? "" : MsInfoUtil.getPath(request));
		response.setStatus(status);
		response.setErrors(errors);
		response.setTimestamp(date.toString());
		return ResponseEntity.status(status)
				.body(response);
	}
	
}
