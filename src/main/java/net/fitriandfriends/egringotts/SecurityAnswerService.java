package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityAnswerService {

    @Autowired
    private SecurityAnswerRepository securityAnswerRepository;

    public SecurityAnswer registerSecurityAnswer(Account account, SecurityQuestion securityQuestion, String answer) {

        SecurityAnswer securityAnswer = new SecurityAnswer(account, securityQuestion, answer);

        return securityAnswerRepository.save(securityAnswer);

    }

}