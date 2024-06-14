package net.fitriandfriends.egringotts.controller;

import net.fitriandfriends.egringotts.service.TransactionService;
import freemarker.template.TemplateException;
import net.fitriandfriends.egringotts.exception.InsufficientBalanceException;
import net.fitriandfriends.egringotts.dto.TransactionTransfer;
import net.fitriandfriends.egringotts.base.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to create a transaction
    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionTransfer transactionTransfer) throws TemplateException, InsufficientBalanceException, IOException {

        Transaction createdTransaction = transactionService.performTransaction(transactionTransfer);

        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

    }

    // Endpoint to get all transactions of an account
    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long accountId) {

        List<Transaction> transactions = transactionService.getTransactionHistory(accountId);

        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }

    // Endpoint to filter transactions of an account by specified criteria
    @GetMapping("/{accountId}/filter")
    public ResponseEntity<List<Transaction>> getFilteredTransactions(

            @RequestParam String accountId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) Double amountThreshold) {

        return new ResponseEntity<>(transactionService.getFilteredTransactions(Long.valueOf(accountId), category, startDate, endDate, amountThreshold), HttpStatus.OK);

    }

    // Endpoint to download transaction history as a PDF file
    @GetMapping("/{transactionId}/download")
    public ResponseEntity<byte[]> downloadTransactionReceipt(@PathVariable Long transactionId) {

        // Retrieve the stored PDF file associated with the transaction
        byte[] pdfContent = transactionService.getPdfContentForTransaction(transactionId);

        if (pdfContent != null) {

            // Set headers for the response
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("receipt", "receipt.pdf");
            headers.setContentLength(pdfContent.length);

            // Return the PDF content as a response entity
            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);

        } else {

            // If PDF content is not found, return a 404 Not Found response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTransactionCount() {

        return new ResponseEntity<>(transactionService.getTransactionCount(), HttpStatus.OK);

    }

    @GetMapping("/sum/{currencyId}")
    public ResponseEntity<Double> getTransactionSumByCurrency(@PathVariable Long currencyId) {

        return new ResponseEntity<>(transactionService.getTransactionSumByCurrency(currencyId), HttpStatus.OK);

    }

    // Other endpoints

}