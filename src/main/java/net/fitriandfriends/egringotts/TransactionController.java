package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to create a transaction
    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

    }

    // Endpoint to get all transactions of an account
    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long accountId) {

        List<Transaction> transactions = transactionService.getTransactionHistory(accountId);

        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

    // Endpoint to filter transactions of an account by specified criteria
    @GetMapping("/filter")
    public List<Transaction> getFilteredTransactions(

            @RequestParam Long accountId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) Double amountThreshold) {

        return transactionService.getFilteredTransactions(accountId, category, startDate, endDate, amountThreshold);

    }

    // Other endpoints

}