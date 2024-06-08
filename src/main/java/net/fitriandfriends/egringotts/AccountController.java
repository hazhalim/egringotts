package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/search")
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

//    @PostMapping("/search")
//    public ResponseEntity<List<Account>> searchMatchingAccounts(@RequestBody String fullNameOrTelephoneNumber, @RequestParam(defaultValue = "true") boolean isFullName) {
//
//        List<Account> matchingAccounts;
//
//        if (isFullName) {
//
//            matchingAccounts = accountService.searchAccountByFullName(fullNameOrTelephoneNumber);
//
//        } else {
//
//            matchingAccounts = accountService.searchAccountByTelephoneNumber(fullNameOrTelephoneNumber);
//
//        }
//
//        return ResponseEntity.ok(matchingAccounts);
//
//    }

    // Other endpoints

}