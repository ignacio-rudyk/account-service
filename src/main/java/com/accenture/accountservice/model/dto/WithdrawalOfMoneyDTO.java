package com.accenture.accountservice.model.dto;

import java.io.Serializable;

public class WithdrawalOfMoneyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numberAccount;

    private String amount;

    public WithdrawalOfMoneyDTO() {
        this(null, null);
    }

    public WithdrawalOfMoneyDTO(String numberAccount, String amount) {
        super();
        this.numberAccount = numberAccount;
        this.amount = amount;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "WithdrawalOfMoneyDTO{" +
                "numberAccount='" + numberAccount + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
