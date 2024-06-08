package net.fitriandfriends.egringotts;

import freemarker.template.TemplateException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CurrencyExchangeService {

    private WeightedGraph<Currency, CurrencyExchange> currencyExchangeGraph = new WeightedGraph<>();

    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @PostConstruct
    public void initialiseGraph() {

        List<Currency> currencies = currencyRepository.findAll();
        List<CurrencyExchange> currencyExchanges = currencyExchangeRepository.findAll();

        for (Currency currency : currencies) {

            currencyExchangeGraph.addVertex(currency);

        }

        for (CurrencyExchange currencyExchange : currencyExchanges) {

            currencyExchangeGraph.addEdge(currencyExchange.getFromCurrency(), currencyExchange.getToCurrency(), currencyExchange);

        }

    }

    @Cacheable("exchangeCurrency")
    public ExchangeResult exchangeCurrency(Currency fromCurrency, Currency toCurrency, double amount) {

        // If the specified fromCurrency doesn't exist
        if (!currencyExchangeGraph.hasVertex(fromCurrency)) {

            throw new IllegalArgumentException(fromCurrency.getName() + " (" + fromCurrency.getAbbreviation() + ") is not a supported currency");

        }

        // If the specified toCurrency doesn't exist
        if (!currencyExchangeGraph.hasVertex(toCurrency)) {

            throw new IllegalArgumentException(toCurrency.getName() + " (" + toCurrency.getAbbreviation() + ") is not a supported currency");

        }

        // If there is a direct exchange rate from fromCurrency to toCurrency
        if (currencyExchangeGraph.hasEdge(fromCurrency, toCurrency)) {

            return exchangeDirectCurrency(fromCurrency, toCurrency, amount);

        // Else, use the shortest path to exchange the currency using Djikstra's algorithm
        } else {

            return exchangeIndirectCurrency(fromCurrency, toCurrency, amount);

        }

    }

    // The result of a currency exchange is encapsulated in this class
    // Contains: the new exchanged amount (toCurrency),
    // the total processing fees (fromCurrency),
    // each processing fee for each intermediate exchange (fromCurrency)
    @Data
    @Getter
    @Setter
    public static class ExchangeResult {

        // Instance variable
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

    @Cacheable("exchangeDirectCurrency")
    public ExchangeResult exchangeDirectCurrency(Currency fromCurrency, Currency toCurrency, double amount) {
        if (fromCurrency.compareTo(toCurrency) == 0) {

            HashMap<String, Double> processingFees = new LinkedHashMap<>();
            processingFees.put(fromCurrency.getAbbreviation(), 0.0);

            return new ExchangeResult(0, 0, 0, processingFees);

        } else {

            CurrencyExchange exchange = currencyExchangeGraph.getEdgeWeight(fromCurrency, toCurrency);
            Double rate = exchange.getRate();
            Double processingFee = exchange.getProcessingFee();

            if (rate != null && processingFee != null) {

                Map<String, Double> processingFees = new LinkedHashMap<>();
                String key = fromCurrency.getAbbreviation() + ", " + toCurrency.getAbbreviation();
                processingFees.put(key, amount * processingFee);

                return new ExchangeResult(amount,amount * rate, amount * processingFee, processingFees);

            } else {

                throw new IllegalArgumentException("No exchange rate from " + fromCurrency.getAbbreviation() + " to " + toCurrency.getAbbreviation());

            }

        }

    }

    @Cacheable("exchangeIndirectCurrency")
    public ExchangeResult exchangeIndirectCurrency(Currency fromCurrency, Currency toCurrency, double amount) {

        // If the currencies are the same
        if (fromCurrency.compareTo(toCurrency) == 0) {

            HashMap<String, Double> processingFees = new LinkedHashMap<>();
            String key = fromCurrency.getAbbreviation() + ", " + toCurrency.getAbbreviation();
            processingFees.put(key, 0.0);

            return new ExchangeResult(0, 0, 0, processingFees);

        } else {

            Map<Currency, Double> distances = currencyExchangeGraph.shortestPath(fromCurrency);
            Map<Currency, CurrencyExchange> exchanges = currencyExchangeGraph.shortestPathWithExchanges(fromCurrency);

            // Get the total rate from fromCurrency to toCurrency through the shortest path
            Double totalRate = distances.get(toCurrency);

            if (totalRate != null) {

                // The final converted amount in the form of toCurrency
                double convertedAmount = amount * totalRate;

                // Initialise the total processing fee
                double totalProcessingFee = 0.0;

                Map<String, Double> processingFees = new LinkedHashMap<>();

                // Start calculating rates and processing fees from toCurrency
                Currency currentCurrency = toCurrency;

                // While the current traversal currency is not the fromCurrency
                while (!currentCurrency.equals(fromCurrency)) {

                    CurrencyExchange exchange = exchanges.get(currentCurrency);
                    double processingFee = amount * exchange.getProcessingFee();

                    // Add the processing fee to the processing fees map, key format: "fromCurrency, toCurrency"
                    String key = fromCurrency.getAbbreviation() + ", " + toCurrency.getAbbreviation();
                    processingFees.put(currentCurrency.getAbbreviation(), processingFee);

                    // Add the processing fee to the total processing fee
                    totalProcessingFee += processingFee;

                    // Traverse to the next currency (in direction of fromCurrency)
                    currentCurrency = exchange.getFromCurrency();

                }

                // Finally, return the exchange result containing the converted amount, total processing fee, and processing fee in each step
                return new ExchangeResult(amount, convertedAmount, totalProcessingFee, processingFees);

            } else {

                throw new IllegalArgumentException("No exchange path from " + fromCurrency.getAbbreviation() + " to " + toCurrency.getAbbreviation());

            }

        }

    }

//    @Cacheable("exchangeIndirectCurrency")
//    public ExchangeResult exchangeIndirectCurrencyDetailed(Currency fromCurrency, Currency toCurrency, double amount) {
//        if (fromCurrency.compareTo(toCurrency) == 0) {
//            return new ExchangeResult(amount, 0.0, new HashMap<>());
//        } else {
//            Map<Currency, Double> distances = currencyExchangeGraph.shortestPath(fromCurrency);
//            Map<Currency, CurrencyExchange> exchanges = currencyExchangeGraph.shortestPathWithExchanges(fromCurrency);
//            Double totalRate = distances.get(toCurrency);
//
//            if (totalRate != null) {
//                double convertedAmount = amount * totalRate;
//                double totalProcessingFee = 0.0;
//                Map<String, Double> processingFees = new LinkedHashMap<>();
//
//                Currency currentCurrency = toCurrency;
//                while (!currentCurrency.equals(fromCurrency)) {
//                    CurrencyExchange exchange = exchanges.get(currentCurrency);
//                    double processingFee = amount * exchange.getProcessingFee();
//                    processingFees.put(currentCurrency.getAbbreviation(), processingFee);
//                    totalProcessingFee += processingFee;
//                    currentCurrency = exchange.getFromCurrency();
//                }
//
//                return new ExchangeResult(convertedAmount, totalProcessingFee, processingFees);
//            } else {
//                throw new IllegalArgumentException("No conversion path from " + fromCurrency.getAbbreviation() + " to " + toCurrency.getAbbreviation());
//            }
//        }
//    }

    @Transactional
    @CacheEvict(value = {"exchangeCurrency", "exchangeDirectCurrency", "exchangeIndirectCurrency"}, allEntries = true)
    public boolean processCurrencyExchange(Account account, Currency fromCurrency, Currency toCurrency, ExchangeResult exchangeResult) throws InsufficientBalanceException, TemplateException, IOException {

        // Not-null checks
        if (account == null)
            throw new IllegalArgumentException("Account cannot be null");

        if (fromCurrency == null)
            throw new IllegalArgumentException("From currency cannot be null");

        if (toCurrency == null)
            throw new IllegalArgumentException("To currency cannot be null");

        if (exchangeResult == null)
            throw new IllegalArgumentException("Exchange result cannot be null");

        // Find the account's balance for the fromCurrency and toCurrency
        Balance fromCurrencyAccountBalance = balanceRepository.findByAccountAndCurrency(account, fromCurrency);
        Balance toCurrencyAccountBalance = balanceRepository.findByAccountAndCurrency(account, toCurrency);

        // Find the amount to be deducted from the fromCurrency account balance
        Double totalFromAccountBalanceDeduction = exchangeResult.initialAmount + exchangeResult.totalProcessingFee;

        // Find the amount to be incremented to the toCurrency account balance
        Double totalToAccountBalanceIncrement = exchangeResult.exchangedAmount;

        // If the account has enough balance to process the currency exchange
        if (fromCurrencyAccountBalance.getBalance() >= totalFromAccountBalanceDeduction) {

            fromCurrencyAccountBalance.setBalance(fromCurrencyAccountBalance.getBalance() - totalFromAccountBalanceDeduction);
            toCurrencyAccountBalance.setBalance(toCurrencyAccountBalance.getBalance() + totalToAccountBalanceIncrement);

            // Save the updated balances
            balanceRepository.save(fromCurrencyAccountBalance);
            balanceRepository.save(toCurrencyAccountBalance);

            // Generate transactions for the currency exchange
            String description = "Currency Exchange: " + exchangeResult.initialAmount + " " + fromCurrency.getAbbreviation() + " to " + exchangeResult.exchangedAmount + " " + fromCurrency.getAbbreviation();

            // Decrement transaction in fromCurrency's balance
            Transaction decrementTransaction = new Transaction("Withdrawal", account, account, "Online Banking", null, totalFromAccountBalanceDeduction, fromCurrency, fromCurrencyAccountBalance, new Date(), "Currency Exchange", description, null);
            decrementTransaction.setReceiptFileName(TransactionReceiptGenerator.generateTransactionReceipt(decrementTransaction));

            // Increment transaction in toCurrency's balance
            Transaction incrementTransaction = new Transaction("Deposit", account, account, "Online Banking", null, totalToAccountBalanceIncrement, toCurrency, toCurrencyAccountBalance, new Date(), "Currency Exchange", description, null);
            incrementTransaction.setReceiptFileName(TransactionReceiptGenerator.generateTransactionReceipt(incrementTransaction));

            // Save the transactions
            transactionRepository.save(decrementTransaction);
            transactionRepository.save(incrementTransaction);

            return true;

        } else {

            throw new InsufficientBalanceException("Insufficient balance in account " + account.getAccountID() + " to process currency exchange");

        }

    }

}