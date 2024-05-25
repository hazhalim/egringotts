package net.fitriandfriends.egringotts;

import jakarta.persistence.*;

@Entity
public class Card {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public Card() {}

    public Card(Long accountId, String cardNumber, String cvv, String expiryDate) {

        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;

    }

}