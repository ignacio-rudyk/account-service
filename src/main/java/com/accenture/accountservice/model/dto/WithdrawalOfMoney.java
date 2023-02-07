package com.accenture.accountservice.model.dto;

public class WithdrawalOfMoney {

    private String numberAccount;

    private String amount;

    public WithdrawalOfMoney() {
        this(null, null);
    }

    public WithdrawalOfMoney(String numberAccount, String amount) {
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
}
