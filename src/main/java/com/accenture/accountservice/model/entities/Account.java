package com.accenture.accountservice.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

@Entity
@Table(name = "account")
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
    private Long userID;

    public Account() {
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
        return "Account{" +
                "id=" + id +
                ", numberAccount='" + numberAccount + '\'' +
                ", funds=" + funds +
                ", cbu='" + cbu + '\'' +
                ", userID=" + userID +
                '}';
    }
}
