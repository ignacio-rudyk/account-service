package com.accenture.accountservice.model.dto;

import java.math.BigDecimal;

public class AccountDTO {

    private Long id;

    private String numberAccount;

    private BigDecimal funds;

    private String cbu;

    private Long userID;

    public AccountDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", numberAccount='" + numberAccount + '\'' +
                ", funds=" + funds +
                ", cbu='" + cbu + '\'' +
                ", userID=" + userID +
                '}';
    }
}
