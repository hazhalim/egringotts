package net.fitriandfriends.egringotts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table
@Data
public class Transaction {

    // Accessor and mutator methods
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

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    private Currency currency;

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

    public Transaction(Account fromAccount, Account toAccount, String paymentMethod, Card card, Double amount, Currency currency, Double fromAccountBalance, Date date, String category, String receiptFileName) {

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

    // Accessor, mutator, and toString methods are handled by Lombok

}