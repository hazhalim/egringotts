package net.fitriandfriends.egringotts.service;

import net.fitriandfriends.egringotts.base.Currency;
import net.fitriandfriends.egringotts.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Currency getCurrencyByCurrencyId(Long currencyId) {

        return currencyRepository.findByCurrencyID(currencyId);

    }

    public List<Currency> getAllCurrencies() {

        return currencyRepository.findAll();

    }

    public Currency addCurrency(String name, String abbreviation) {

        Currency currency = new Currency(name, abbreviation);

        return currencyRepository.save(currency);

    }

    public Currency addCurrency(Currency currency) {

        return currencyRepository.save(currency);

    }

}