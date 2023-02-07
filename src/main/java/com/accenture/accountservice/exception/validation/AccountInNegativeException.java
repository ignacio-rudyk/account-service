package com.accenture.accountservice.exception.validation;

import com.accenture.accountservice.exception.ValidationException;

public class AccountInNegativeException extends ValidationException {

    private static Integer CODE = 5;

    private static String ACCOUNT_IN_NEGATIVE_EXCEPTION = "Los fondos de la cuenta son negativos";

    public AccountInNegativeException(){
        super(ACCOUNT_IN_NEGATIVE_EXCEPTION, CODE);
    }

}
