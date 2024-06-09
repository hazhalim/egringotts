package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityAnswerService {

    @Autowired
    private SecurityAnswerRepository securityAnswerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityAnswer registerSecurityAnswer(Account account, SecurityQuestion securityQuestion, String answer) {

        SecurityAnswer securityAnswer = new SecurityAnswer(account, securityQuestion, answer);

        return securityAnswerRepository.save(securityAnswer);

    }

    public Boolean validateSecurityAnswer(SecurityAnswerDTO securityAnswerDTO) {

        Account account = accountRepository.findByAccountID(securityAnswerDTO.getAccountId());

        SecurityAnswer securityAnswer = securityAnswerRepository.findByAccount(account);

        if (securityAnswer != null) {

            return passwordEncoder.matches(securityAnswerDTO.getAnswer(), securityAnswer.getAnswer());

        } else {

            throw new IllegalArgumentException("The security answer associated with the account cannot be null.");

        }

    }

}