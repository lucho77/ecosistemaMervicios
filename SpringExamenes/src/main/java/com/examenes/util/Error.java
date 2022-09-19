package com.examenes.util;

public class Error {

    private String message;
    private String code;
    private String description;

    public Error(String message, String code, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public Error(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Error(String description) {
        this.description = description;
    }

    public Error() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}