package net.fitriandfriends.egringotts.service;

import net.fitriandfriends.egringotts.base.Account;
import net.fitriandfriends.egringotts.base.Transaction;
import net.fitriandfriends.egringotts.repository.AccountRepository;
import net.fitriandfriends.egringotts.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final JavaMailSender mailSender;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final TransactionRepository transactionRepository;

    public EmailService(JavaMailSender mailSender, AccountRepository accountRepository, TransactionRepository transactionRepository) {

        this.mailSender = mailSender;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Async
    public void sendTestEmail() {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("mnfitrifzn2004@gmail.com");
        message.setSubject("Test Email");
        message.setText("This is a test email from E-Gringotts Bank.");

        mailSender.send(message);

    }

    @Async
    public void sendAccountCreationEmail(Long accountId) {

        Account account = accountRepository.findByAccountID(accountId);

        SimpleMailMessage message = new SimpleMailMessage();

        String emailAddress = account.getEmailAddress();

        String subject = "[E-Gringotts Bank] Welcome to the bank, " + account.getFullName() + "!";

        String body = "Dear " + account.getFullName() + ", \n\n"
                + "Welcome to the wizarding bank of E-Gringotts!\n" +
                "We just wanted to let you know that your account creation was successful.\n" +
                "Your account ID is: " + account.getAccountID() + "\n" +
                "Your username is: " + account.getUsername() + "\n" +
                "We hope you'll enjoy the magical banking experience with us!\n\n" +
                "Yours magically,\n" +
                "The Goblins of E-Gringotts Bank";

        // Recipient of the email
        message.setTo(emailAddress);

        // Subject of the email
        message.setSubject(subject);

        // Text of the email
        message.setText(body);

        // Send the email
        mailSender.send(message);

    }

    public void sendTransactionEmail(Long accountId, Long transactionId) {

        Account account = accountRepository.findByAccountID(accountId);
        Transaction transaction = transactionRepository.findByTransactionId(transactionId);

        String emailAddress = account.getEmailAddress();

        String subject = "[E-Gringotts Bank] Transaction: " + transaction.getDescription();

        String body = "Dear " + account.getFullName() + ",\n\n" +
                "You have just completed a transaction at E-Gringotts Bank with the following details:\n\n" +
                "Transaction ID: " + transaction.getTransactionId() + "\n" +
                "Type: " + transaction.getType() + "\n" +
                "From: " + transaction.getFromAccount().getFullName() + "\n" +
                "To: " + transaction.getToAccount().getFullName() + "\n" +
                "Amount: " + transaction.getAmount() + "\n" +
                "Currency: " + transaction.getCurrency().getName() + "\n" +
                "Description: " + transaction.getDescription() + "\n" +
                "Date: " + transaction.getDate() + "\n\n" +
                "Thank you for using E-Gringotts Bank!";

        SimpleMailMessage message = new SimpleMailMessage();

        // Recipient of the email
        message.setTo(emailAddress);

        // Subject of the email
        message.setSubject(subject);

        // Text of the email
        message.setText(body);

        // Send the email
        mailSender.send(message);

    }

}