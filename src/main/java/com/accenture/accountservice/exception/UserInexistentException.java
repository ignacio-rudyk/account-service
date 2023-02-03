package com.accenture.accountservice.exception;

public class UserInexistentException extends AccountServiceException {

    private static Integer CODE = -4;

    private static String USER_INEXISTENT_EXCEPTION_MSG = "El usuario solicitado no existe";

    public UserInexistentException() {
        super(USER_INEXISTENT_EXCEPTION_MSG, CODE);
    }

}
