package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.Currency;
import net.fitriandfriends.egringotts.base.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    // Get all currency exchange rates
    List<CurrencyExchange> findAll();

    // Get currency exchange rates by fromCurrency
    List<CurrencyExchange> findByFromCurrency(Currency fromCurrency);

    // Get currency exchange rates by toCurrency
    List<CurrencyExchange> findByToCurrency(Currency toCurrency);

    // Get a currency exchange rate by fromCurrency and toCurrency
    CurrencyExchange findByFromCurrencyAndToCurrency(Currency fromCurrency, Currency toCurrency);

}