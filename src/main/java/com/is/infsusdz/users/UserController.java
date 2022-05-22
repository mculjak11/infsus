package com.is.infsusdz.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    UserService us = new UserService();

    @GetMapping(path="/api/users/{username}", produces = "application/json")
    public ResponseEntity getByUsername(@PathVariable String username) {
        return us.getUser(username);
    }
}
