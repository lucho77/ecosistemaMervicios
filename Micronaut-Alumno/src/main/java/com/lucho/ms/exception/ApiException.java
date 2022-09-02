package com.lucho.ms.exception;

import com.lucho.ms.util.ErrorCode;

public class ApiException extends Exception{

	private final int status;
	private final ErrorCode code;
	private final String description;
	protected ApiException(int status, String message, ErrorCode code, String description) {
	        super(message);
	        this.status = status;
	        this.code = code;
	        this.description = description;
	 }

	    public int getStatus() {
	        return status;
	    }

	    public ErrorCode getCode() {
	        return code;
	    }

	    public String getDescription() {
	        return description;
	    }
}
