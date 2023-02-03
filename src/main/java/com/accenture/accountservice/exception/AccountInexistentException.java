package com.accenture.accountservice.exception;

public class AccountInexistentException extends AccountServiceException {

    private static Integer CODE = -2;

    private static String ACCOUNT_INEXISTENT_EXCEPTION_MSG = "La cuenta solicitada no existe";

    public AccountInexistentException() {
        super(ACCOUNT_INEXISTENT_EXCEPTION_MSG, CODE);
    }

}
