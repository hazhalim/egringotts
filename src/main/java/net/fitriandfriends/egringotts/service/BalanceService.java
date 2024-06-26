package net.fitriandfriends.egringotts.service;

import net.fitriandfriends.egringotts.base.Balance;
import net.fitriandfriends.egringotts.base.Account;
import net.fitriandfriends.egringotts.base.Currency;
import net.fitriandfriends.egringotts.repository.BalanceRepository;
import net.fitriandfriends.egringotts.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    // Create balances of an account upon registration
    @CacheEvict(value = {"balancesByAccountId", "currencyBalanceByAccountId"}, allEntries = true)
    public List<Balance> initialiseBalances(Account account) {

        List<Balance> balances = new ArrayList<>();

        for (Currency currency : currencyRepository.findAll()) {

            Balance balance;

            if (account.getUser().getType().equals("GOBLIN")) {

                balance = new Balance(account, currency, 1000000.0);

            } else if (account.getUser().getType().equals("PLATINUM_PATRONUS")) {

                balance = new Balance(account, currency, 10000.0);

            } else if (account.getUser().getType().equals("GOLDEN_GALLEON")) {

                balance = new Balance(account, currency, 5000.0);

            } else if (account.getUser().getType().equals("SILVER_SNITCH")) {

                balance = new Balance(account, currency, 2500.0);

            } else {

                throw new IllegalArgumentException("Invalid account type");

            }

            balances.add(balance);

        }

        return balanceRepository.saveAll(balances);

    }

    // Get all balances of an account
    @Cacheable("balancesByAccountId")
    public List<Balance> getBalancesByAccountId(Long accountID) {

        return balanceRepository.findByAccount_AccountID(accountID);

    }

    // Get a balance of a certain currency of an account
    @Cacheable("currencyBalanceByAccountId")
    public Balance getBalanceByAccountIdAndCurrencyId(Long accountID, Long currencyID) {

        return balanceRepository.findByAccount_AccountIDAndCurrency_CurrencyID(accountID, currencyID);

    }

    // Other service methods

}