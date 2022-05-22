package com.is.infsusdz.users;

import com.is.infsusdz.ads.CarFindAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class UserService {

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    public ResponseEntity getUser(String username) {

        CarFindUser user1 = carFindUserRepo.findCarFindUserByUsername(username);
        if (user1 == null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("user with username: " + username + " not found");
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user1);
        }
    }
}
