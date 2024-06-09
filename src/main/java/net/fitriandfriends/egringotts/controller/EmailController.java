package net.fitriandfriends.egringotts.controller;

import net.fitriandfriends.egringotts.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emails")
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {

        this.emailService = emailService;

    }

    @GetMapping("/test")
    public void sendTestEmail() {

        emailService.sendTestEmail();

    }

    @GetMapping("/{accountId}")
    public void sendAccountCreationEmail(@RequestParam Long accountId) {

        emailService.sendAccountCreationEmail(accountId);

    }

    @GetMapping("/{accountId}/{transactionId}")
    public void sendTransactionEmail(@RequestParam Long accountId, @RequestParam Long transactionId) {

        emailService.sendTransactionEmail(accountId, transactionId);

    }

}