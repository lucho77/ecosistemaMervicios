package com.examenes.util;

public class InternalServerErrorException extends APIException {
    public InternalServerErrorException(String message, ErrorCode code, String description) {
        super(500, message, code, description);
    }
}
