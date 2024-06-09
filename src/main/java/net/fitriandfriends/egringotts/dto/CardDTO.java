package net.fitriandfriends.egringotts.dto;

import lombok.Data;

@Data
public class CardDTO {

    // Instance variables
    private Long accountID;
    private String type;
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public CardDTO(Long accountID, String type, String cardNumber, String cvv, String expiryDate) {

        this.accountID = accountID;
        this.type = type;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;

    }

}