package net.fitriandfriends.egringotts.service;

import net.fitriandfriends.egringotts.base.Account;
import net.fitriandfriends.egringotts.base.User;
import net.fitriandfriends.egringotts.repository.AccountRepository;
import net.fitriandfriends.egringotts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService<T extends User> {

    // Instance variables
    @Autowired
    private UserRepository<T> userRepository;

    @Autowired
    private AccountRepository accountRepository;

    // Accessor and mutator methods
    @CacheEvict(value = {"allUsers", "user"}, allEntries = true)
    public T createUser(T user) {

        return userRepository.save(user);

    }

    @Cacheable("allUsers")
    public List<T> getAllUsers() {

        return userRepository.findAll();

    }

    @Cacheable("user")
    public User getUserByAccountID(Long accountID) {

        Account account = accountRepository.findByAccountID(accountID);

        return account.getUser();

    }

    // Other service methods

}