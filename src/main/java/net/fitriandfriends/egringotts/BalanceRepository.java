package net.fitriandfriends.egringotts;

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

    // Find a balance of a certain currency of an account
    public Balance findByAccountAndCurrency(Account account, String currency);

    // Find a balance of a certain currency of an account at a certain account ID
    public Balance findByAccount_AccountIDAndCurrency(Long accountID, String currency);

    // Other queries

}