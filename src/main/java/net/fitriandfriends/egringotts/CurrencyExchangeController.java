package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fitriandfriends.egringotts.CurrencyExchangeService.ExchangeResult;

@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    @PostMapping("/convert")
    public ResponseEntity<ExchangeResult> exchangeCurrency(@RequestBody ExchangeRequestDTO exchangeRequestDTO) {

        try {

            Currency fromCurrency = currencyRepository.findByCurrencyID(exchangeRequestDTO.getFromCurrencyID());
            Currency toCurrency = currencyRepository.findByCurrencyID(exchangeRequestDTO.getToCurrencyID());

            double amount = exchangeRequestDTO.getInitialAmount();

            ExchangeResult result = currencyExchangeService.exchangeCurrency(fromCurrency, toCurrency, amount);

            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException exception) {

            return ResponseEntity.badRequest().body(null);

        }

    }

    @PostMapping("/process")
    public ResponseEntity<String> processExchange(@RequestBody ProcessExchangeRequestDTO exchangeRequest) {

        try {

            Account account = accountRepository.findByAccountID(exchangeRequest.getAccountID());

            Currency fromCurrency = currencyRepository.findByCurrencyID(exchangeRequest.getFromCurrencyID());
            Currency toCurrency = currencyRepository.findByCurrencyID(exchangeRequest.getToCurrencyID());

            double initialAmount = exchangeRequest.getInitialAmount();

            ExchangeResult exchangeResult = currencyExchangeService.exchangeCurrency(fromCurrency, toCurrency, initialAmount);

            boolean success = currencyExchangeService.processCurrencyExchange(account, fromCurrency, toCurrency, exchangeResult);

            if (success) {

                return ResponseEntity.ok("The currency exchange processed successfully.");

            } else {

                return ResponseEntity.badRequest().body("The currency exchange failed.");

            }

        } catch (InsufficientBalanceException exception) {

            return ResponseEntity.badRequest().body("There is an insufficient balance for the originating currency.");

        } catch (Exception exception) {

            return ResponseEntity.badRequest().body("There was an error processing the currency exchange.");

        }

    }

    // Other methods

}