package com.accenture.accountservice.model.dto;

import java.io.Serializable;

public class SendingOfMoneyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cbuDestination;

    private String amount;

    public SendingOfMoneyDTO(){
        this(null, null);
    }

    public SendingOfMoneyDTO(String cbuDestination, String amount) {
        super();
        this.cbuDestination = cbuDestination;
        this.amount = amount;
    }

    public String getCbuDestination() {
        return cbuDestination;
    }

    public void setCbuDestination(String cbuDestination) {
        this.cbuDestination = cbuDestination;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SendingOfMoneyDTO{" +
                "cbuDestination='" + cbuDestination + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
