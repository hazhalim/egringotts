package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/account/{accountID}")
    public ResponseEntity<List<Balance>> getBalancesByAccountId(@PathVariable Long accountID) {

        List<Balance> balances = balanceService.getBalancesByAccountId(accountID);

        if (balances.isEmpty()) {

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.ok(balances);

        }

    }

    @GetMapping("/account/{accountID}/{currency}")
    public ResponseEntity<Balance> getBalancesByAccountId(@PathVariable Long accountID, @PathVariable String currency) {

        Balance balance = balanceService.getCurrencyBalanceByAccountId(accountID, currency);

        if (balance == null) {

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.ok(balance);

        }

    }

    // Other endpoints

}