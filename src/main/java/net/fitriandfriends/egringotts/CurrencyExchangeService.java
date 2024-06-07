package net.fitriandfriends.egringotts;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CurrencyExchangeService {

    @Autowired
    private BalanceRepository balanceRepository;
    private WeightedGraph<Currency, Double> currencyExchangeGraph;

    public CurrencyExchangeService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public void initialiseGraph(List<Currency> currencies, List<CurrencyExchange> currencyExchanges) {

        for (Currency currency : currencies) {

            currencyExchangeGraph.addVertex(currency);

        }

        for (CurrencyExchange rate : currencyExchanges) {

            currencyExchangeGraph.addEdge(rate.getFromCurrency(), rate.getToCurrency(), rate.getRate());

        }

    }

    public double convertDirectCurrency(Currency fromCurrency, Currency toCurrency, double amount) {

        if (fromCurrency.compareTo(toCurrency) == 0) {

            return amount;

        } else {

            Double rate = currencyExchangeGraph.getEdgeWeight(fromCurrency, toCurrency);

            if (rate != null) {

                return amount * rate;

            } else {

                throw new IllegalArgumentException("No conversion rate from " + fromCurrency.getAbbreviation() + " to " + toCurrency.getAbbreviation());

            }

        }

    }

    public double convertIndirectCurrency(Currency fromCurrency, Currency toCurrency, double amount) {

        if (fromCurrency.compareTo(toCurrency) == 0) {

            return amount;

        } else {

            Map<Currency, Double> distances = currencyExchangeGraph.shortestPath(fromCurrency);

            Double rate = distances.get(toCurrency);

            if (rate != null) {

                return amount * rate;

            } else {

                throw new IllegalArgumentException("No conversion path from " + fromCurrency.getAbbreviation() + " to " + toCurrency.getAbbreviation());

            }

        }

    }

    public double convertCurrency(Currency fromCurrency, Currency toCurrency, double amount) {

        if (!currencyExchangeGraph.hasVertex(fromCurrency)) {

            throw new IllegalArgumentException(fromCurrency.getName() + " (" + fromCurrency.getAbbreviation() + ") is not a supported currency");

        }

        if (!currencyExchangeGraph.hasVertex(toCurrency)) {

            throw new IllegalArgumentException(toCurrency.getName() + " (" + toCurrency.getAbbreviation() + ") is not a supported currency");

        }

        if (currencyExchangeGraph.hasEdge(fromCurrency, toCurrency)) {

            return convertDirectCurrency(fromCurrency, toCurrency, amount);

        } else {

            return convertIndirectCurrency(fromCurrency, toCurrency, amount);

        }

    }

    @Transactional
    public boolean processCurrencyExchange(Account account, Currency fromCurrency, Currency toCurrency, double amount) {

        double
        Balance fromCurrencyAccountBalance = balanceRepository.findByAccountAndCurrency(account, fromCurrency);
        Balance toCurrencyAccountBalance = balanceRepository.findByAccountAndCurrency(account, toCurrency);


        if (fro) {

            double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);

            account.setCurrency(toCurrency);
            account.setBalance(convertedAmount);

            return true;

        } else {

            return false;

        }

    }

}