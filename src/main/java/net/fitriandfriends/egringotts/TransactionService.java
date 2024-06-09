package net.fitriandfriends.egringotts;

import freemarker.template.TemplateException;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CardService cardService;

    // Get a transaction by its ID
    @Cacheable("transaction")
    public Transaction getTransactionById(Long transactionId) {

        return transactionRepository.findByTransactionId(transactionId);

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
    @CacheEvict(value = {"transactionHistory", "transactionsByAccountId", "transaction"}, allEntries = true)
    public Transaction performTransaction(TransactionTransfer transactionTransfer) throws TemplateException, IOException, InsufficientBalanceException {

        Account fromAccount = accountService.getAccountByAccountId(transactionTransfer.getFromAccountId());
        Account toAccount = accountService.getAccountByAccountId(transactionTransfer.getToAccountId());
        Card card = cardService.getCardByCardId(transactionTransfer.getCardId());
        Currency currency = currencyService.getCurrencyByCurrencyId(transactionTransfer.getCurrencyId());
        Double amount = transactionTransfer.getAmount();

        // Ensure that the fromAccount and toAccount are not the same
        if (fromAccount.equals(toAccount)) {

            throw new IllegalArgumentException("The fromAccount and toAccount cannot be the same.");

        }

        // Verify the security PIN of the fromAccount
        if (!accountService.verifySecurityPIN(fromAccount.getAccountID(), transactionTransfer.getSecurityPIN())) {

            throw new IllegalArgumentException("Invalid security PIN.");

        }

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
        Transaction transaction = new Transaction(transactionTransfer.getType(), fromAccount, toAccount, transactionTransfer.getPaymentMethod(), card, amount, currency, fromAccountBalance, new Date(), transactionTransfer.getCategory(), transactionTransfer.getDescription(), null);

        String receiptFileName = TransactionReceiptGenerator.generateTransactionReceipt(transaction);

        transaction.setReceiptFileName(receiptFileName);

        return transactionRepository.save(transaction);

    }

    public byte[] getPdfContentForTransaction(Long transactionId) {

        // Retrieve the transaction with the given ID from the database
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);

        if (transaction != null) {

            // Get the file path of the PDF stored in the transaction
            String filePath = transaction.getReceiptFileName();

            // Read the file and convert it to a byte array
            try (InputStream inputStream = new FileInputStream(filePath)) {

                return IOUtils.toByteArray(inputStream);

            } catch (IOException exception) {

                exception.printStackTrace();

            }

        }

        return null;

    }

    public Long getTransactionCount() {

        return (long) transactionRepository.findAll().size();

    }

    public Double getTransactionSumByCurrency(Long currencyId) {

        return transactionRepository.sumAmountByCurrency_CurrencyID(currencyId);

    }

}