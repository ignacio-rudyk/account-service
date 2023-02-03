package com.accenture.accountservice.model.dto;

import java.math.BigDecimal;

public class AccountDTO {

    private Long id;

    private String numberAccount;

    private BigDecimal funds;

    private String cbu;

    private Long userId;

    private Boolean isEnabled;

    public AccountDTO() {
        this(null);
    }

    public AccountDTO(Long userID) {
        super();
        this.userId = userID;
        this.funds = new BigDecimal(0);
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", numberAccount='" + numberAccount + '\'' +
                ", funds=" + funds +
                ", cbu='" + cbu + '\'' +
                ", userID=" + userId +
                '}';
    }
}
