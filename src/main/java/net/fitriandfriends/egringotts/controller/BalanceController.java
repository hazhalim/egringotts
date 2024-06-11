package net.fitriandfriends.egringotts.controller;

import lombok.Data;
import net.fitriandfriends.egringotts.base.Balance;
import net.fitriandfriends.egringotts.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Data
    public static class BalanceDTO {

        Long accountID;


    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Balance>> getBalancesByAccountId(@PathVariable Long accountId) {

        List<Balance> balances = balanceService.getBalancesByAccountId(accountId);

        if (balances.isEmpty()) {

            return ResponseEntity.noContent().build();

        } else {

            return new ResponseEntity<>(balances, HttpStatus.OK);

        }

    }

    @GetMapping("/{accountID}/{currencyID}")
    public ResponseEntity<Balance> getBalanceByAccountId(@PathVariable Long accountID, @PathVariable Long currencyID) {

        Balance balance = balanceService.getBalanceByAccountIdAndCurrencyId(accountID, currencyID);

        if (balance == null) {

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.ok(balance);

        }

    }

    // Other endpoints

}