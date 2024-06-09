package net.fitriandfriends.egringotts;

import lombok.Data;

@Data
public class TransactionTransfer {

    private String type;
    private Long fromAccountId;
    private Long toAccountId;
    private String paymentMethod;
    private Long cardId;
    private Double amount;
    private Long currencyId;
    String category;
    String description;
    String securityPIN;

    public TransactionTransfer(String type, Long fromAccountId, Long toAccountId, String paymentMethod, Long cardId, Double amount, Long currencyId, String category, String description, String securityPIN) {

        this.type = type;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.paymentMethod = paymentMethod;
        this.cardId = cardId;
        this.amount = amount;
        this.currencyId = currencyId;
        this.category = category;
        this.description = description;
        this.securityPIN = securityPIN;

    }

}