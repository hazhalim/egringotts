package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {

        Transaction createdTransaction = transactionService.createTransaction(transaction);

        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long userId) {

        List<Transaction> transactions = transactionService.getTransactionHistory(userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

    // Other endpoints

}