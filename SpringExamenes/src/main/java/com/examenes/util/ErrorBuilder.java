package com.examenes.util;

import java.util.ArrayList;
import java.util.List;

public final class ErrorBuilder {

	 private ErrorBuilder() {
	    }

	    public static List<Error> buildError(APIException exception) {
	        List<Error> errors = new ArrayList<>();
	        Error error = buildError(exception.getCode().toString(), exception.getDescription(), exception.getMessage());
	        errors.add(error);
	        return errors;
	    }

	    public static Error buildError(String code, String description, String message) {
	        Error error = new Error();
	        error.setCode(code);
	        error.setDescription(description);
	        error.setMessage(message);
	        return error;
	    }
}
