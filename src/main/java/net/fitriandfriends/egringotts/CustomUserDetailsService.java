package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> account = accountRepository.findByUsername(username);

        if (account.isPresent()) {

            var accountObj = account.get();

            return User.builder()
                    .username(accountObj.getUsername())
                    .password(accountObj.getPassword())
                    .roles(getRole(accountObj))
                    .build();

        } else {

            throw new UsernameNotFoundException(username);

        }

    }

    private String getRole(Account account) {

        String role = account.getUser().getType();

        if (role == null) {

            return "Silver Snitch";

        } else {

            return account.getUser().getType();

        }

    }

}