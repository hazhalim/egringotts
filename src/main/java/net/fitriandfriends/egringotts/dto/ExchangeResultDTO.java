package net.fitriandfriends.egringotts.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeResultDTO {

    // Instance variables
    private double initialAmount;
    private double exchangedAmount;
    private double totalProcessingFee;
    private String processingFees;


    // Constructor
    public ExchangeResultDTO(double initialAmount, double exchangedAmount, double totalProcessingFee, Map<String, Double> processingFees) {

        this.initialAmount = initialAmount;
        this.exchangedAmount = exchangedAmount;
        this.totalProcessingFee = totalProcessingFee;
        this.processingFees = processingFees.toString();

    }

}