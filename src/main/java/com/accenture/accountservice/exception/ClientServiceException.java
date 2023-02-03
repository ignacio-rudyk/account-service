package com.accenture.accountservice.exception;

public class ClientServiceException extends AccountServiceException {

    private static Integer CODE = -3;

    private static String CLIENT_SERVICE_EXCEPTION = "Falló la operación del cliente en la comunicacion con otro servicio";

    public ClientServiceException() {
        super(CLIENT_SERVICE_EXCEPTION, CODE);
    }
}
