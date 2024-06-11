package net.fitriandfriends.egringotts.controller;

import net.fitriandfriends.egringotts.repository.AccountRepository;
import net.fitriandfriends.egringotts.repository.SecurityQuestionRepository;
import net.fitriandfriends.egringotts.base.Account;
import net.fitriandfriends.egringotts.base.SecurityQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/securityquestions")
public class SecurityQuestionController {

    @Autowired
    private SecurityQuestionRepository securityQuestionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/all")
    public ResponseEntity<List<SecurityQuestion>> getAll() {

        return ResponseEntity.ok(securityQuestionRepository.findAll());

    }

    @GetMapping("/{username}")
    public ResponseEntity<SecurityQuestion> getSecurityQuestionByUsername(@PathVariable String username) {

        Optional<Account> account = accountRepository.findByUsername(username);

        return account.map(value -> ResponseEntity.ok(value.getSecurityQuestion())).orElseGet(() -> ResponseEntity.notFound().build());

    }

}