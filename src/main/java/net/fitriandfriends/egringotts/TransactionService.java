package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Create a transaction instance on the database
    public Transaction createTransaction(Transaction transaction) {

        return transactionRepository.save(transaction);

    }

    // Get all transactions of an account
    public List<Transaction> getTransactionHistory(Long accountId) {

        return transactionRepository.findByAccountId(accountId);

    }

    // Other service methods

}