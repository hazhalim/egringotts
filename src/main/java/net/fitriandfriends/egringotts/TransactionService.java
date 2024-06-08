package net.fitriandfriends.egringotts;

import freemarker.template.TemplateException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    // Create a transaction instance on the database
    @CacheEvict(value = {"transactionHistoru", "transactionsByAccountId"}, allEntries = true)
    public Transaction createTransaction(Transaction transaction) {

        return transactionRepository.save(transaction);

    }

    // Get the transaction of an account by account ID
    @Cacheable("transactionHistory")
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

//    // Get all transactions of an account (by Account object)
//    @Cacheable("transactionHistory")
//    public List<Transaction> getTransactionHistory(Account account) {
//
//        // From DB, get the transactions of an account
//        List<Transaction> transactions = transactionRepository.findByFromAccount(account);
//
//        // Initialise a PensievePast instance
//        PensievePast pensievePast = new PensievePast();
//
//        // Push each transaction in the list to the stack (the least recent transaction is at the bottom)
//        pensievePast.pushTransactionsToStack(transactions);
//
//        // Return the new list of transactions by getting the pensieve past (the most recent transaction is at the top)
//        return pensievePast.getPensievePast();
//
//    }

    // Get all transactions of an account with specific filters (by account ID, through the repository)
    @Cacheable("transactionsByAccountId")
    public List<Transaction> getFilteredTransactions(Long accountId, String category, Date startDate, Date endDate, Double amountThreshold) {

        return transactionRepository.findFilteredTransactions(accountId, category, startDate, endDate, amountThreshold);

    }

    // Other service methods
    @Transactional
    public Transaction performTransaction(String type, Account fromAccount, Account toAccount, String paymentMethod, Card card, Double amount, Currency currency, String category, String description) throws TemplateException, IOException, InsufficientBalanceException {

        // Get the balances, ensure that the fromAccount has enough balance
        Balance fromAccountBalance = balanceRepository.findByAccountAndCurrency(fromAccount, currency);
        Balance toAccountBalance = balanceRepository.findByAccountAndCurrency(toAccount, currency);

        if (fromAccountBalance == null || fromAccountBalance.getBalance() < amount) {

            throw new InsufficientBalanceException("Insufficient " + fromAccountBalance.getCurrency() + " in account: " + fromAccount.getUsername());

        }

        // Deduct the amount from the fromAccount balance
        if (toAccountBalance != null) {

            // Deduct the balance from the fromAccount in the currency
            fromAccountBalance.setBalance(fromAccountBalance.getBalance() - amount);
            balanceRepository.save(fromAccountBalance);

            // Increment the balance from the toAccount in the currency
            toAccountBalance.setBalance(toAccountBalance.getBalance() + amount);
            balanceRepository.save(toAccountBalance);

        }

        // Create a transaction and save it in the database
        Transaction transaction = new Transaction(type, fromAccount, toAccount, paymentMethod, card, amount, currency, fromAccountBalance, new Date(), category, description, null);

        String receiptFileName = TransactionReceiptGenerator.generateTransactionReceipt(transaction);

        transaction.setReceiptFileName(receiptFileName);

        return transactionRepository.save(transaction);

    }

}