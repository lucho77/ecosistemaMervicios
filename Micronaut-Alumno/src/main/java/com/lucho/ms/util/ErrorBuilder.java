package com.lucho.ms.util;

import java.util.ArrayList;
import java.util.List;

import com.lucho.ms.exception.ApiException;

public final class ErrorBuilder {

	public static List<Error> buildError(ApiException exception) {
        List<Error> errors = new ArrayList<>();
        Error error = new Error();
        error.setCode(exception.getCode().toString());
        error.setDescription(exception.getDescription());
        error.setMessage(exception.getMessage());
        errors.add(error);
        return errors;
    }
	
}
