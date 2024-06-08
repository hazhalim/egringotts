package net.fitriandfriends.egringotts;

import lombok.Data;

// Data Transfer Object (DTO) for processing exchange requests
@Data
public class ProcessExchangeRequest {

    private Long accountID;
    private Long fromCurrencyID;
    private Long toCurrencyID;
    private double initialAmount;

    public ProcessExchangeRequest(Long accountID, Long fromCurrencyID, Long toCurrencyID, double initialAmount) {

        this.accountID = accountID;
        this.fromCurrencyID = fromCurrencyID;
        this.toCurrencyID = toCurrencyID;
        this.initialAmount = initialAmount;

    }

}