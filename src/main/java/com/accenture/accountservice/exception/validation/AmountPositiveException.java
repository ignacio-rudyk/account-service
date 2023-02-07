package com.accenture.accountservice.exception.validation;

import com.accenture.accountservice.exception.ValidationException;

public class AmountPositiveException extends ValidationException {

    private static Integer CODE = 4;

    private static String AMOUNT_POSITIVE_EXCEPTION = "El monto debe ser negativo";

    public AmountPositiveException() { super(AMOUNT_POSITIVE_EXCEPTION, CODE); }
}
