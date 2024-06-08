package net.fitriandfriends.egringotts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Data
@Getter
@Setter
@ToString
public class CurrencyExchange implements Comparable<CurrencyExchange> {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_exchange_rate_id")
    private Long currencyExchangeRateID;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "from_currency_id", referencedColumnName = "currency_id")
    private Currency fromCurrency;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "to_currency_id", referencedColumnName = "currency_id")
    private Currency toCurrency;

    @Column(name = "rate")
    private Double rate;

    // Always in the currency listed in fromCurrency
    @Column(name = "processing_fee")
    private Double processingFee;

    // Constructors
    public CurrencyExchange() {}

    public CurrencyExchange(Currency fromCurrency, Currency toCurrency, Double rate, Double processingFee) {

        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
        this.processingFee = processingFee;

    }

    @Override
    public int compareTo(CurrencyExchange otherCurrencyExchange) {

        return currencyExchangeRateID.compareTo(otherCurrencyExchange.currencyExchangeRateID);

    }

    // Accessor, mutator, and toString methods are handled by Lombok

}