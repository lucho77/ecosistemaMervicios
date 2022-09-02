package com.lucho.ms.controller;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucho.ms.constants.AlumnoConstants;
import com.lucho.ms.exception.AlumnoException;
import com.lucho.ms.exception.ApiException;
import com.lucho.ms.exception.InternalServerErrorException;
import com.lucho.ms.exception.ValidationException;
import com.lucho.ms.util.ErrorBuilder;
import com.lucho.ms.util.ErrorCode;
import com.lucho.ms.util.Response;

import io.micronaut.core.convert.exceptions.ConversionErrorException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;

@Controller
public class ErrorController {

    protected final Logger log = LoggerFactory.getLogger(getClass());


    /**
     * Captura las APIException
     *
     * @param request
     * @param exception
     * @return
     */
    @Error(global = true)
    public HttpResponse<Response> apiException(HttpRequest<?> request, ApiException exception) {
        return buildResponse(request, exception);
    }
 
    /**
     * Captura los ConversionErrorException (HTTP 400)
     *
     * @param request
     * @param exception
     * @return
     */
    @Error(global = true)
    public HttpResponse<Response> badRequest(HttpRequest<?> request, ConversionErrorException exception) {
        ValidationException validationException = new ValidationException(AlumnoConstants.BAD_REQUEST_MESSAGE,
                ErrorCode.ERROR_DE_VALIDACION, exception.getMessage());
        return buildResponse(request, validationException);
    }

    /**
     * Captura las ConstraintViolationException (HTTP 400)
     *
     * @param request
     * @param exception
     * @return
     */
    @Error(global = true)
    public HttpResponse<Response> badRequest(HttpRequest<?> request, ConstraintViolationException exception) {
        ValidationException validationException = new ValidationException(AlumnoConstants.BAD_REQUEST_MESSAGE,
                ErrorCode.BODY_REQUEST_MAL_FORMADO, exception.getMessage());
        return buildResponse(request, validationException);
    }

    
    /**
     * Captura las Exception (HTTP 500)
     *
     * @param request
     * @param exception
     * @return
     */
    @Error(global = true)
    public HttpResponse<Response> internalServerError(HttpRequest<?> request, Exception exception) {
        InternalServerErrorException internalServerErrorException = new InternalServerErrorException(AlumnoConstants.SERVER_ERROR_MESSAGE,
                ErrorCode.ERROR_INTERNO_DEL_SERVIDOR, exception.getLocalizedMessage());
        return buildResponse(request, internalServerErrorException);
    }

    /**
     * Captura las ComunicacionApiException (HTTP 500)
     *
     * @param request
     * @param exception
     * @return
     */
    @Error(global = true)
    public HttpResponse<Response> communicationServerError(HttpRequest<?> request, AlumnoException exception) {
        InternalServerErrorException internalServerErrorException = new InternalServerErrorException(AlumnoConstants.SERVER_ERROR_MESSAGE,
                ErrorCode.ERROR_COMUNICACION_EXTERNA, exception.getLocalizedMessage());
        return buildResponse(request, internalServerErrorException);
    }

    private HttpResponse<Response> buildResponse(HttpRequest<?> request, ApiException exception) {
        log.error("Ocurrio un ERROR en la llamada", exception);
        List<com.lucho.ms.util.Error> errors = ErrorBuilder.buildError(exception);
        Date date = new Date();
        Response response = new Response();
        response.setPath(request.getPath());
        response.setStatus(exception.getStatus());
        response.setErrors(errors);
        response.setTimestamp(date.toString());
        return HttpResponse.status(HttpStatus.valueOf(exception.getStatus()))
                .body(response);
    }
}
