package com.examenes.util;

public class APIException extends Exception {

    private final int status;
    private final ErrorCode code;
    private final String description;

    protected APIException(int status, String message, ErrorCode code, String description) {
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
