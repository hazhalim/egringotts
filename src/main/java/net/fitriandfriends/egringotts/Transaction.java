package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "fromAccountID", referencedColumnName = "fromAccountID")
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "toAccountID", referencedColumnName = "toAccountID")
    private Account toAccount;
    private Double amount;
    private String currency;

    private Double fromAccountBalance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String category;

    public Transaction() {
    }

    public Transaction(Account fromAccount, Account toAccount, Double amount, String currency, Double fromAccountBalance, String category) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.fromAccountBalance = fromAccountBalance;
        this.date = new Date();
        this.category = category;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
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

    public void setFromAccountBalance(Double fromAccountBalance) {
        this.fromAccountBalance = fromAccountBalance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}