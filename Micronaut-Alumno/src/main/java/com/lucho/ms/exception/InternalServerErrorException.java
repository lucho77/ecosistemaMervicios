package com.lucho.ms.exception;

import com.lucho.ms.util.ErrorCode;

public class InternalServerErrorException extends ApiException {
	 public InternalServerErrorException(String message, ErrorCode code, String description) {
	        super(500, message, code, description);
	    }
}
