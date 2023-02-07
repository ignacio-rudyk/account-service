package com.accenture.accountservice.exception.validation;

import com.accenture.accountservice.exception.ValidationException;

import javax.persistence.criteria.CriteriaBuilder;

public class AmountNegativeException extends ValidationException {

    private static Integer CODE = 3;

    private static String AMOUNT_NEGATIVE_EXCEPTION = "El monto debe ser positivo";

    public AmountNegativeException(){
        super(AMOUNT_NEGATIVE_EXCEPTION, CODE);
    }
}
