package com.accenture.accountservice.exception;

public class AccountDAOException extends Exception {

    private static String ACCOUNT_DAO_EXCEPTION = "Hubo un error al acceder a los datos";

    public AccountDAOException() {
        this(ACCOUNT_DAO_EXCEPTION);
    }

    public AccountDAOException(String message) {
        super(message);
    }

}
