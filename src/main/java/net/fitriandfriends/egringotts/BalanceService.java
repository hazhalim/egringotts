package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

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