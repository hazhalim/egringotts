package net.fitriandfriends.egringotts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table
@Data
@Getter
@Setter
@ToString
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

    // Accessor, mutator, and toString methods are handled by Lombok

}