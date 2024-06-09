package net.fitriandfriends.egringotts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions
    List<Transaction> findAll();

    // Find all transactions of an account where funds were transferred from that account
    List<Transaction> findByFromAccount(Account fromAccount);
    List<Transaction> findByFromAccount_AccountID(Long fromAccountID);

    Transaction findByTransactionID(Long transactionID);

    // Find all transactions where funds were transferred to that account
    List<Transaction> findByToAccount(Account toAccount);
    List<Transaction> findByToAccount_AccountID(Long toAccountID);

    // Transfer Filter Select
    @Query("SELECT t FROM Transaction t WHERE (t.fromAccount.accountID = :accountId OR t.toAccount.accountID = :accountId) AND (:category IS NULL OR t.category = :category) AND (:startDate IS NULL OR t.date >= :startDate) AND (:endDate IS NULL OR t.date <= :endDate) AND (:amountThreshold IS NULL OR t.amount >= :amountThreshold)")
    List<Transaction> findFilteredTransactions(

            @Param("accountId") Long accountId,
            @Param("category") String category,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("amountThreshold") Double amountThreshold

    );

}