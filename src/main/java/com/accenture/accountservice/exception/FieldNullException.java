package com.accenture.accountservice.exception;

public class FieldNullException extends ValidationException {

    private static Integer CODE = 2;

    private static String FIELD_NULL_EXCEPTION = "Campo nulo";

    public FieldNullException() {
        super(FIELD_NULL_EXCEPTION, CODE);
    }

}
