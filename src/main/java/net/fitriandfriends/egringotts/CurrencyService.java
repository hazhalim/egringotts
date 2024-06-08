package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Currency addCurrency(String name, String abbreviation) {

        Currency currency = new Currency(name, abbreviation);

        return currencyRepository.save(currency);

    }

}