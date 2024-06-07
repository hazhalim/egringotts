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
public class CurrencyConversionRate {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_conversion_rate_id")
    private Long currencyConversionRateID;

    @ManyToOne
    @JoinColumn(name = "from_currency_id", referencedColumnName = "currency_id")
    private Currency fromCurrency;

    @ManyToOne
    @JoinColumn(name = "to_currency_id", referencedColumnName = "currency_id")
    private Currency toCurrency;

    @Column(name = "rate")
    private Double rate;

    // Constructors
    public CurrencyConversionRate() {}

    public CurrencyConversionRate(Currency fromCurrency, Currency toCurrency, Double rate) {

        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;

    }

    // Accessor, mutator, and toString methods are handled by Lombok

}