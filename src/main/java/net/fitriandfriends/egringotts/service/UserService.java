package net.fitriandfriends.egringotts.service;

import net.fitriandfriends.egringotts.base.User;
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

    // Accessor and mutator methods
    @CacheEvict(value = "users", allEntries = true)
    public T createUser(T user) {

        return userRepository.save(user);

    }

    @Cacheable("users")
    public List<T> getAllUsers() {

        return userRepository.findAll();

    }

    // Other service methods

}