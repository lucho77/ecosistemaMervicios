package com.examenes.util;

import java.util.List;

/**
 * todo : crear estructura para contener varios errores de validacion
 */
public class ValidationException extends APIException {
	   private final List<Error> errors;

	    public ValidationException(String message, ErrorCode code, String description) {
	        super(400, message, code, description);
	        this.errors = null;
	    }

	    public ValidationException(List<Error> errors) {
	        super(400, null, null, null);
	        this.errors = errors;
	    }

	    public List<Error> getErrors() {
	        return errors;
	    }

}
