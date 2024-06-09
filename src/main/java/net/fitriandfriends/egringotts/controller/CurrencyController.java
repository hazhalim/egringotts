package net.fitriandfriends.egringotts.controller;

import net.fitriandfriends.egringotts.repository.CurrencyRepository;
import net.fitriandfriends.egringotts.service.CurrencyService;
import net.fitriandfriends.egringotts.base.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAllCurrencies() {

        return ResponseEntity.ok(currencyRepository.findAll());

    }

    @PostMapping("/add")
    public Currency addCurrency(@RequestBody Currency currency) {

        return currencyService.addCurrency(currency);

    }

}