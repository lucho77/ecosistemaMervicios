package com.examenes.util;

public class UnauthorizedException extends APIException {
    public UnauthorizedException(String message, ErrorCode code, String description) {
        super(401, message, code, description);
    }
}
