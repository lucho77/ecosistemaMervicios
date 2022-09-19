package com.examenes.util;

public class BadRequestException extends APIException {
    public BadRequestException(String message, ErrorCode code, String description) {
        super(400, message, code, description);
    }
}
