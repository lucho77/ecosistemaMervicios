package com.examenes.util;


public class NotFoundException extends APIException {
    public NotFoundException(String message, ErrorCode code, String description) {
        super(404, message, code, description);
    }
}
