package com.accenture.accountservice.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String numberAccount;

    @Column(nullable = false)
    private BigDecimal funds;

    @Column(nullable = false)
    private String cbu;

    @Column
    private Long userId;

    @Column(nullable = false)
    private Boolean isEnabled;

    public Account() {
        this(null);
    }

    public Account(Long userId) {
        super();
        this.userId = userId;
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

    public BigDecimal addFunds(BigDecimal amount) {
        this.funds = this.funds.add(amount);
        return this.funds;
    }

    public BigDecimal subtractFunds(BigDecimal amount) {
        this.funds = this.funds.subtract(amount);
        return this.funds;
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
        return "Account{" +
                "id=" + id +
                ", numberAccount='" + numberAccount + '\'' +
                ", funds=" + funds +
                ", cbu='" + cbu + '\'' +
                ", userID=" + userId +
                '}';
    }
}
