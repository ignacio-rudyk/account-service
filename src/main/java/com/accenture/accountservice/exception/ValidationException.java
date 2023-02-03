package com.accenture.accountservice.exception;

public class ValidationException extends AccountServiceException {
    private static Integer CODE = 1;

    private static String VALIDATION_EXCEPTION = "Hubo un error en la validacion de datos";

    public ValidationException(){
        super(VALIDATION_EXCEPTION, CODE);
    }
    public ValidationException(String message, Integer code) {
        super(message, code);
    }

}
