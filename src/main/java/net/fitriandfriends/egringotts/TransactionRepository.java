package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions of an account
    List<Transaction> findByFromAccount(Account fromAccount);
    List<Transaction> findByFromAccount_AccountID(Long fromAccountID);

    // Find all transactions where funds were transferred to that account
    List<Transaction> findByToAccount(Account toAccount);
    List<Transaction> findByToAccount_AccountID(Long toAccountID);

}