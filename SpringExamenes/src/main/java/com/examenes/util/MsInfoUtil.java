package com.examenes.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * Clase con metodos utiles para el microservicio
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MsInfoUtil {

	/**
	 * Construye un mensaje para el Status Code de Http, concatenendo
	 * el codigo con su descripcion.
	 * @param status
	 * @return un status formateado
	 */
	public static String buildStatusString(int status) {
		return status + " - " + HttpStatus.getStatusText(status);
	}

	/**
	 * Levanta la fecha y hora actual, dandole un formato especifico.
	 * @return fecha en formato string.
	 */
	public static String getActualDateTime() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * Nos permite obtener el path de un request dado.
	 * @param request
	 * @return un path
	 */
	public static String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getDescription(false).replace("uri=", "");
	}
}
