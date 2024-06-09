package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.fitriandfriends.egringotts.CurrencyExchangeService.*;

@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping("/convert")
    public ResponseEntity<ExchangeResultDTO> exchangeCurrency(@RequestBody ExchangeRequestDTO exchangeRequestDTO) {

        try {

            Currency fromCurrency = currencyRepository.findByCurrencyID(exchangeRequestDTO.getFromCurrencyID());
            Currency toCurrency = currencyRepository.findByCurrencyID(exchangeRequestDTO.getToCurrencyID());

            double amount = exchangeRequestDTO.getInitialAmount();

            ExchangeResult result = currencyExchangeService.exchangeCurrency(fromCurrency, toCurrency, amount);

            ExchangeResultDTO resultDTO = new CurrencyExchangeService.ExchangeResultDTO(result.getInitialAmount(), result.getExchangedAmount(), result.getTotalProcessingFee(), result.getProcessingFees());

            return ResponseEntity.ok(resultDTO);

        } catch (IllegalArgumentException exception) {

            return ResponseEntity.badRequest().body(null);

        }

    }

    @PostMapping("/process")
    public ResponseEntity<String> processExchange(@RequestBody ProcessExchangeRequestDTO processExchangeRequestDTO) {

        try {

            Account account = accountRepository.findByAccountID(processExchangeRequestDTO.getAccountID());

            Currency fromCurrency = currencyRepository.findByCurrencyID(processExchangeRequestDTO.getFromCurrencyID());
            Currency toCurrency = currencyRepository.findByCurrencyID(processExchangeRequestDTO.getToCurrencyID());

            double initialAmount = processExchangeRequestDTO.getInitialAmount();

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