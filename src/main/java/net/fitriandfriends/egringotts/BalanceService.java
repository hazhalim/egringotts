package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    // Get all balances of an account
    public List<Balance> getBalancesByAccountId(Long accountID) {

        return balanceRepository.findByAccount_AccountID(accountID);

    }

    // Get a balance of a certain currency of an account
    public Balance getCurrencyBalanceByAccountId(Long accountID, String currency) {

        return balanceRepository.findByAccount_AccountIDAndCurrency(accountID, currency);

    }

    // Other service methods

}