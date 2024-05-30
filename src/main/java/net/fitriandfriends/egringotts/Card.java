package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Card {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardID;

    @ManyToOne
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    private Account account;

    private String cardNumber;
    private String cvv;
    private Date expiryDate;

    public Card() {}

    public Card(Long cardID, Account account, String cardNumber, String cvv, Date expiryDate) {
        this.cardID = cardID;
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
}