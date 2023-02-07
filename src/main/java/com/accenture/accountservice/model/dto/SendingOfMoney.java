package com.accenture.accountservice.model.dto;

public class SendingOfMoney {

    private String cbuDestination;

    private String amount;

    public SendingOfMoney(){
        this(null, null);
    }

    public SendingOfMoney(String cbuDestination, String amount) {
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

}
