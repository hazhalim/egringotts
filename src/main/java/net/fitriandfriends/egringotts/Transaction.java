package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Transaction {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "from_account_id", referencedColumnName = "account_id")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id", referencedColumnName = "account_id")
    private Account toAccount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
    private Card card;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "from_account_balance")
    private Double fromAccountBalance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "category")
    private String category;

    @Column(name = "receipt_file_path")
    private String receiptFileName;

    // Constructors
    public Transaction() {}

    public Transaction(Account fromAccount, Account toAccount, String paymentMethod, Card card, Double amount, String currency, Double fromAccountBalance, Date date, String category, String receiptFileName) {

        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.paymentMethod = paymentMethod;
        this.card = card;
        this.amount = amount;
        this.currency = currency;
        this.fromAccountBalance = fromAccountBalance;
        this.date = date;
        this.category = category;
        this.receiptFileName = receiptFileName;

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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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

    public String getReceiptFileName() {
        return receiptFileName;
    }

    public void setReceiptFileName(String receiptFilePath) {
        this.receiptFileName = receiptFilePath;
    }

    // Other methods
    @Override
    public String toString() {

        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", card=" + card +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", fromAccountBalance=" + fromAccountBalance +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", receiptFilePath='" + receiptFileName + '\'' +
                '}';

    }

}