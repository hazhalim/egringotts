package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Card {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardID;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    @Column(name = "type")
    private String type;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "expiry_date")
    private Date expiryDate;

    // Constructors
    public Card() {}

    public Card(Account account, String type, String cardNumber, String cvv, Date expiryDate) {

        this.type = type;
        this.account = account;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;

    }

    public Long getCardID() {
        return cardID;
    }

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {

        return "Card{" +
                "cardID=" + cardID +
                ", account=" + account +
                ", type='" + type + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expiryDate=" + expiryDate +
                '}';

    }

}