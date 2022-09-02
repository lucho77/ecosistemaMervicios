package com.lucho.ms.exception;

import com.lucho.ms.util.ErrorCode;

public class ValidationException extends ApiException {
	 public ValidationException(String message, ErrorCode code, String description) {
	        super(400, message, code, description);
	    }
}
