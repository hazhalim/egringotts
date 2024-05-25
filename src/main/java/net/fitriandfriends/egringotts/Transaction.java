package net.fitriandfriends.egringotts;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long fromAccountId;
    private Long toAccountId;

    private Double amount;
    private String currency;

    private Double fromAccountBalance;

    private Date transactionDate;
    private String category;

    public Transaction() {}

    public Transaction(Long fromAccountId, Long toAccountId, Double amount, String currency, Double balance, String category) {

        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;

        this.amount = amount;
        this.currency = currency;

        this.fromAccountBalance = balance;

        this.transactionDate = new Date();
        this.category = category;

    }

    public Long getTransactionId() {

        return transactionId;

    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long userId) {
        this.fromAccountId = userId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getFromAccountBalance() {
        return fromAccountBalance;
    }

    public void setFromAccountBalance(Double balance) {
        this.fromAccountBalance = balance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}