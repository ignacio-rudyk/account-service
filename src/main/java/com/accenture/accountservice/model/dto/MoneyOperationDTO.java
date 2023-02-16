package com.accenture.accountservice.model.dto;

import java.io.Serializable;

public class MoneyOperationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long accountId;

    private String amount;

    public MoneyOperationDTO(){
        this(null, null);
    }

    public MoneyOperationDTO(Long accountId, String amount) {
        super();
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MoneyOperationDTO{" +
                "accountId=" + accountId +
                ", amount='" + amount + '\'' +
                '}';
    }

}
