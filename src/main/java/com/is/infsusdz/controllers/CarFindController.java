package com.is.infsusdz.controllers;

import com.is.infsusdz.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarFindController {

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    @PostMapping(path="/api/login", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity verifyUser(@RequestBody LoginData userLogin) {
        CarFindUser usr = carFindUserRepo.findCarFindUserByEmailAndPassword(userLogin.getEmail(),
                                                                        userLogin.getPassword());
        if (usr != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("login success");
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("user not found");
        }
    }

    @PostMapping(path="/api/signup")
    public ResponseEntity signup(@RequestBody SignupData userSignup) {
        CarFindUser tmp = carFindUserRepo.findCarFindUserByUserName(userSignup.getUserName());
        if (tmp == null) {
            CarFindUser usr = new CarFindUser();
            usr.setUserName(userSignup.getUserName());
            usr.setEmail(userSignup.getEmail());
            usr.setPassword(userSignup.getPassword());
            Map<String, Object> usrInfo = new HashMap<>();
            usrInfo.put("name", userSignup.getName());
            usrInfo.put("surname", userSignup.getSurname());
            usrInfo.put("phoneNo", userSignup.getPhoneNo());
            Map<String, Object> placeInfo = new HashMap<>();
            placeInfo.put("country", userSignup.getCountry());
            placeInfo.put("city", userSignup.getCity());
            placeInfo.put("address", userSignup.getAddress());
            usrInfo.put("place", placeInfo);
            usr.setUserInfo(usrInfo);
            carFindUserRepo.save(usr);
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("signup ok");
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("username taken");
        }
    }

    @GetMapping(path="/api/users/{username}", produces = "application/json")
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

    @GetMapping(path="/api/ads/all", produces = "application/json")
    public ResponseEntity getAllAds() {
        List<CarFindAd> carAd = carFindAdRepo.findAll();
        for (CarFindAd car : carAd) {
            System.out.println(car.getMake());
        }
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

    @PostMapping(path="/api/ads", produces = "application/json")
    public ResponseEntity getAdsFilter(@RequestBody String filter) {
        List<String> fil = List.of(filter.split(":"));
        Integer first = Integer.parseInt(fil.get(0));
        Integer second = Integer.parseInt(fil.get(1));
        List<CarFindAd> carAd = carFindAdRepo.findCarFindAdByYearBetween(first, second);
        if (carAd.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("There are no ads with that filter");
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(carAd);
        }
    }
}
