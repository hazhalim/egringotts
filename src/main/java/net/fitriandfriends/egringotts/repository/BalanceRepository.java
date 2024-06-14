package net.fitriandfriends.egringotts.repository;

import net.fitriandfriends.egringotts.base.Balance;
import net.fitriandfriends.egringotts.base.Account;
import net.fitriandfriends.egringotts.base.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    // Find all balances
    public List<Balance> findAll();

    // Find all balances of an account
    public List<Balance> findByAccount(Account account);

    // Find all balances of an account ID
    public List<Balance> findByAccount_AccountID(Long accountID);

    public Balance findByAccountAndCurrency_CurrencyID(Account account, Long currencyID);

    public Balance findByAccountAndCurrency(Account account, Currency currency);

    public Balance findByAccount_AccountIDAndCurrency(Long accountID, Currency currency);

    public Balance findByAccount_AccountIDAndCurrency_CurrencyID(Long accountID, Long currencyID);

    // Other queries

}