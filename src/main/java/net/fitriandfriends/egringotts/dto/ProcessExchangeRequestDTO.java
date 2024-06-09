package net.fitriandfriends.egringotts.dto;

import lombok.Data;

// Data Transfer Object (DTO) for processing exchange requests
@Data
public class ProcessExchangeRequestDTO {

    private Long accountID;
    private Long fromCurrencyID;
    private Long toCurrencyID;
    private double initialAmount;

    public ProcessExchangeRequestDTO(Long accountID, Long fromCurrencyID, Long toCurrencyID, double initialAmount) {

        this.accountID = accountID;
        this.fromCurrencyID = fromCurrencyID;
        this.toCurrencyID = toCurrencyID;
        this.initialAmount = initialAmount;

    }

}