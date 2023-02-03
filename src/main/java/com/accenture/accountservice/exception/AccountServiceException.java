package com.accenture.accountservice.exception;

public class AccountServiceException extends Exception {

    private Integer code;

    private static String ACCOUNT_SERVICE_EXCEPTION = "Hubo un error en el servicio";

    public AccountServiceException() {
        this(ACCOUNT_SERVICE_EXCEPTION, -1);
    }

    public AccountServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
