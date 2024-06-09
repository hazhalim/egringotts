package net.fitriandfriends.egringotts.controller;

import net.fitriandfriends.egringotts.dto.AccountDTO;
import net.fitriandfriends.egringotts.repository.AccountRepository;
import net.fitriandfriends.egringotts.service.AccountService;
import net.fitriandfriends.egringotts.repository.SecurityAnswerRepository;
import net.fitriandfriends.egringotts.base.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SecurityAnswerRepository securityAnswerRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Account>> searchMatchingAccounts(@RequestParam(required = false) String fullName, @RequestParam(required = false) String telephoneNumber) {

        List<Account> matchingAccounts = accountService.searchMatchingAccounts(fullName, telephoneNumber);

        return ResponseEntity.ok(matchingAccounts);

    }

    @GetMapping("/sort")
    public ResponseEntity<List<Account>> getSortedAccounts(@RequestParam String sortBy, @RequestParam(defaultValue = "ascending") String order) {

        List<Account> sortedAccounts;

        if (order.equalsIgnoreCase("descending")) {

            sortedAccounts = accountService.getAllAccountsSortedDescending(sortBy);

        } else {

            sortedAccounts = accountService.getAllAccountsSortedAscending(sortBy);

        }

        return ResponseEntity.ok(sortedAccounts);

    }

    @GetMapping("/{accountId}/username")
    public ResponseEntity<String> getUsername(@PathVariable Long accountId) {

        if (accountId != null) {

            return ResponseEntity.ok(accountService.getUsername(accountId));

        } else {

            throw new IllegalArgumentException("Account ID cannot be null.");

        }

    }

    @GetMapping("/{accountId}/settings")
    public ResponseEntity<AccountDTO> getAccountSettings(@PathVariable Long accountId) {

        if (accountId != null) {

            return ResponseEntity.ok(accountService.getAccountSettings(accountId));

        } else {

            throw new IllegalArgumentException("Account ID cannot be null.");

        }

    }

    @PostMapping("/{accountId}/settings/update")
    public ResponseEntity<String> updateAccount(@PathVariable Long accountId, @RequestBody AccountDTO accountDTO) {

        if (accountId != null) {

            return ResponseEntity.ok(accountService.updateAccountSettings(accountId, accountDTO));

        } else {

            throw new IllegalArgumentException("Account ID cannot be null.");

        }

    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {

        return ResponseEntity.ok(accountService.getAccountCount());

    }

}