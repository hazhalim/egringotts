package net.fitriandfriends.egringotts;

import lombok.Data;

// Data Transfer Object (DTO) for exchange requests
@Data
public class ExchangeRequest {

    // Instance
    private Long fromCurrencyID;
    private Long toCurrencyID;
    private double initialAmount;

    public ExchangeRequest(Long fromCurrencyID, Long toCurrencyID, double initialAmount) {

        this.fromCurrencyID = fromCurrencyID;
        this.toCurrencyID = toCurrencyID;
        this.initialAmount = initialAmount;

    }

}