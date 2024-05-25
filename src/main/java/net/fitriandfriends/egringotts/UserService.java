package net.fitriandfriends.egringotts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService<T extends User> {

    // Instance variables
    @Autowired
    private UserRepository<T> userRepository;

    // Constructor
    public UserService(UserRepository<T> userRepository) {

        this.userRepository = userRepository;

    }

    // Accessor and mutator methods
    public T createUser(T user) {

        return userRepository.save(user);

    }

    public List<T> getAllUsers() {

        return userRepository.findAll();

    }

    // Other methods

}