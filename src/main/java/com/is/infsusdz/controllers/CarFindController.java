package com.is.infsusdz.controllers;

import com.is.infsusdz.users.CarFindAd;
import com.is.infsusdz.users.CarFindAdRepository;
import com.is.infsusdz.users.CarFindUser;
import com.is.infsusdz.users.CarFindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarFindController {

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    @GetMapping(path="/users/{username}", produces = "application/json")
    public ResponseEntity getUser(@PathVariable String username) {

        CarFindUser user1 = carFindUserRepo.findCarFindUserByUserName(username);
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

    @GetMapping(path="/ads/all", produces = "application/json")
    public ResponseEntity getAllAds() {
        List<CarFindAd> carAd = carFindAdRepo.findAll();
        if (carAd.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("There are no ads");
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(carAd);
        }
    }

    @GetMapping(path="/ads/{owner}", produces = "application/json")
    public ResponseEntity getAdsFromOwner(@PathVariable String owner) {
        List<CarFindAd> carAd = carFindAdRepo.findCarFindAdByOwner(owner);
        if (carAd.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("There are no ads from " + owner);
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(carAd);
        }
    }
}
