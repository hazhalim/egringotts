package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityAnswerService securityAnswerService;

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/signup/account")
    public ResponseEntity<Account> registerAccount(@RequestBody AccountDTO accountDTO) {

        // Encode the sensitive information of the account
        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountDTO.setSecurityPIN(passwordEncoder.encode(accountDTO.getSecurityPIN()));
        accountDTO.setSecurityAnswer(passwordEncoder.encode(accountDTO.getSecurityAnswer()));

        Account createdAccount = accountService.createAccount(accountDTO);

        securityAnswerService.registerSecurityAnswer(createdAccount, createdAccount.getSecurityQuestion(), accountDTO.getSecurityAnswer());

        // Initialise the balances of the account in all available currencies, starting at 0.0
        balanceService.initialiseBalances(createdAccount);

        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);

    }

}