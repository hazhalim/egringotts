package net.fitriandfriends.egringotts.controller;

import net.fitriandfriends.egringotts.service.UserService;
import net.fitriandfriends.egringotts.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController<T extends User> {

    @Autowired
    private UserService<T> userService;

    @PostMapping("/register")
    public ResponseEntity<T> register(@RequestBody T user) {

        T createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    // Other endpoints

}