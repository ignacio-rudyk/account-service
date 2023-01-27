package com.accenture.accountservice.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private Long id;

    private BigDecimal amount;

    private Date createdAt;

    private Long paymentMethodId;

    private String cbuDestination;

    public Transaction() {
        this(null,null,null,null,null);
    }

    public Transaction(Long id, BigDecimal amount, Date createdAt, Long paymentMethodId, String cbuDestination) {
        super();
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.paymentMethodId = paymentMethodId;
        this.cbuDestination = cbuDestination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getCbuDestination() {
        return cbuDestination;
    }

    public void setCbuDestination(String cbuDestination) {
        this.cbuDestination = cbuDestination;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", paymentMethodId=" + paymentMethodId +
                ", cbuDestination='" + cbuDestination + '\'' +
                '}';
    }

}
