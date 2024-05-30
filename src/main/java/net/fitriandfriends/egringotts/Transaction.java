package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Transaction {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionID")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "fromAccountID", referencedColumnName = "accountID")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "toAccountID", referencedColumnName = "accountID")
    private Account toAccount;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "fromAccountBalance")
    private Double fromAccountBalance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "category")
    private String category;

    // Constructors
    public Transaction() {}

    public Transaction(Account fromAccount, Account toAccount, Double amount, String currency, Double fromAccountBalance, String category) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.fromAccountBalance = fromAccountBalance;
        this.date = new Date();
        this.category = category;
    }

    // Accessor and mutator methods
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

    // Other methods
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", fromAccountBalance=" + fromAccountBalance +
                ", date=" + date +
                ", category='" + category + '\'' +
                '}';
    }

}