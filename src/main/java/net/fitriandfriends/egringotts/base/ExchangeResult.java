package net.fitriandfriends.egringotts.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

// The result of a currency exchange is encapsulated in this class
// Contains: the new exchanged amount (toCurrency),
// the total processing fees (fromCurrency),
// each processing fee for each intermediate exchange (fromCurrency)
@Data
@Getter
@Setter
public class ExchangeResult {

    // Instance variables
    private double initialAmount;
    private double exchangedAmount;
    private double totalProcessingFee;
    private Map<String, Double> processingFees;

    public ExchangeResult(double initialAmount, double exchangedAmount, double totalProcessingFee, Map<String, Double> processingFees) {

        this.initialAmount = initialAmount;
        this.exchangedAmount = exchangedAmount;
        this.totalProcessingFee = totalProcessingFee;
        this.processingFees = processingFees;

    }

}