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
    public List<Transaction> getTransactionHistory(Account account) {

        // From DB, get the transactions of an account
        List<Transaction> transactions = transactionRepository.findByFromAccount(account);

        // Initialise a PensievePast instance
        PensievePast pensievePast = new PensievePast();

        // Push each transaction in the list to the stack (the least recent transaction is at the bottom)
        pensievePast.pushTransactionsToStack(transactions);

        // Return the new list of transactions by getting the pensieve past (the most recent transaction is at the top)
        return pensievePast.getPensievePast();

    }

    public List<Transaction> getTransactionHistory(Long accountId) {

        // From DB, get the transactions of an account
        List<Transaction> transactions = transactionRepository.findByFromAccount_AccountID(accountId);

        // Initialise a PensievePast instance
        PensievePast pensievePast = new PensievePast();

        // Push each transaction in the list to the stack (the least recent transaction is at the bottom)
        pensievePast.pushTransactionsToStack(transactions);

        // Return the new list of transactions by getting the pensieve past (the most recent transaction is at the top)
        return pensievePast.getPensievePast();

    }

    // Other service methods

}