package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {

        return accountRepository.save(account);

    }

    // Other service methods

}