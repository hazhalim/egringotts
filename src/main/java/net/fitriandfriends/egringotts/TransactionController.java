package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{accountId}")
    public ResponseEntity<Transaction> createTransaction(@PathVariable Long accountId, @RequestBody Transaction transaction) {

        Transaction createdTransaction = transactionService.createTransaction(accountId, transaction);

        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long accountId) {

        List<Transaction> transactions = transactionService.getTransactionHistory(accountId);

        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

    // Other endpoints

}