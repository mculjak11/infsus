package com.is.infsusdz.controllers;

import com.is.infsusdz.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
                    .body(usr.getUsername());
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("user not found");
        }
    }

    @PostMapping(path="/api/signup")
    public ResponseEntity signup(@RequestBody SignupData userSignup) {
        CarFindUser tmp = carFindUserRepo.findCarFindUserByUsername(userSignup.getUsername());
        if (tmp == null) {
            CarFindUser usr = new CarFindUser();
            usr.setUsername(userSignup.getUsername());
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

    @GetMapping(path="/api/ads/all", produces = "application/json")
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

    @GetMapping(path="/api/ads/id={id}", produces = "application/json")
    public ResponseEntity getAdById(@PathVariable String id) {
        CarFindAd adById = carFindAdRepo.findCarFindAdById(id);
        if (adById != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(adById);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/api/ads/owner={owner}", produces = "application/json")
    public ResponseEntity getAdsByOwner(@PathVariable String owner) {
        List<CarFindAd> carAd = carFindAdRepo.findCarFindAdByOwner(owner);
        if (carAd.isEmpty()) {
            return ResponseEntity.ok() .contentType(MediaType.TEXT_PLAIN) .body("There are no ads from " + owner);
        } else {
            return ResponseEntity.ok() .contentType(MediaType.APPLICATION_JSON) .body(carAd);
        }
    }


    @GetMapping(path="/api/ads/title={title}")
    public ResponseEntity getAdByTitle(@PathVariable String title) {
        List<CarFindAd> adByTitle = carFindAdRepo.findAll();
        List<CarFindAd> adsMatch = new ArrayList<>();
        for (CarFindAd ad : adByTitle) {
            if (ad.getTitle().toUpperCase().contains(title.toUpperCase())){
                adsMatch.add(ad);
            }
        }
        if (!adsMatch.isEmpty()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(adsMatch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path="/api/ads/getFavorites")
    public ResponseEntity getFavoriteAds(@RequestBody FavoriteGet favGet) {
        CarFindUser usr = carFindUserRepo.findCarFindUserByUsername(favGet.getUsername());
        List<CarFindAd> favoriteAds = new ArrayList<>();
        if(usr.getAds() != null) {
            List<String> favorites = usr.getAds().get("favoriteAds");
            if (favorites != null) {
                CarFindAd favAd = new CarFindAd();
                for (String id : favorites) {
                    favAd = carFindAdRepo.findCarFindAdById(id);
                    favoriteAds.add(favAd);
                }
            }
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(favoriteAds);
    }

    @PostMapping(path="/api/ads/addFavorite")
    public ResponseEntity setFavoriteAd(@RequestBody FavoriteData favoriteData) {
        CarFindUser usr = carFindUserRepo.findCarFindUserByUsername(favoriteData.getUsername());
        carFindUserRepo.delete(usr);
        Map<String,List<String>> tmpAds = usr.getAds();
        if (tmpAds != null) {
            List<String> favorites = usr.getAds().get("favoriteAds");
            if (favorites != null) {
                if (!tmpAds.get("favoriteAds").contains(favoriteData.getId()))
                    tmpAds.get("favoriteAds").add(favoriteData.getId());
            } else {
                tmpAds = new HashMap<>();
                tmpAds.put("favoriteAds", Collections.singletonList(favoriteData.getId()));
            }
        } else {
            tmpAds = new HashMap<>();
            tmpAds.put("favoriteAds", Collections.singletonList(favoriteData.getId()));
        }
        usr.setAds(tmpAds);
        carFindUserRepo.save(usr);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Added to favorites");
    }

    @PostMapping(path="/api/ads/delFavorite")
    public ResponseEntity deleteFavoriteAd(@RequestBody FavoriteData favoriteData) {
        CarFindUser usr = carFindUserRepo.findCarFindUserByUsername(favoriteData.getUsername());
        carFindUserRepo.delete(usr);
        Map<String,List<String>> tmpAds = usr.getAds();
        tmpAds.get("favoriteAds").remove(favoriteData.getId());
        usr.setAds(tmpAds);
        carFindUserRepo.save(usr);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Deleted from favorites");
    }


    @PostMapping(path="/api/ads/filter", produces = "application/json")
    public ResponseEntity getAdsFilter(@RequestBody FilteredAd filter) {
        List<List<String>> listAds = new ArrayList<>();
        List<CarFindAd> allAds = carFindAdRepo.findAll();
        List<String> allIds = new ArrayList<>();
        for (CarFindAd ad: allAds) {
            allIds.add(ad.getId());
        }
        List<CarFindAd> tmpAds = new ArrayList<>();
        List<String> tmpIds = new ArrayList<>();

        if (filter.getMake() != null && filter.getMake() != "") {
            tmpAds = carFindAdRepo.findCarFindAdByMake(filter.getMake());
            for (CarFindAd ad: tmpAds) {
                tmpIds.add(ad.getId());
            }
            listAds.add(tmpIds);
            tmpIds = new ArrayList<>();
        } else listAds.add(allIds);

        if (filter.getAdType() != null && filter.getAdType() != "") {
            tmpAds = carFindAdRepo.findCarFindAdByAdType(filter.getAdType());
            for (CarFindAd ad: tmpAds) {
                tmpIds.add(ad.getId());
            }
            listAds.add(tmpIds);
            tmpIds = new ArrayList<>();
        } else listAds.add(allIds);
        if (filter.getCategory() != null && filter.getCategory() != "") {
            tmpAds = carFindAdRepo.findCarFindAdByCategory(filter.getCategory());
            for (CarFindAd ad: tmpAds) {
                tmpIds.add(ad.getId());
            }
            listAds.add(tmpIds);
            tmpIds = new ArrayList<>();
        } else listAds.add(allIds);
        if (filter.getCity() != null && filter.getCity() != "") {
            tmpAds = carFindAdRepo.findCarFindAdByCity(filter.getCity());
            for (CarFindAd ad: tmpAds) {
                tmpIds.add(ad.getId());
            }
            listAds.add(tmpIds);
            tmpIds = new ArrayList<>();
        } else listAds.add(allIds);
        if (filter.getFuel() != null && filter.getFuel() != "") {
            tmpAds = carFindAdRepo.findCarFindAdByFuel(filter.getFuel());
            for (CarFindAd ad: tmpAds) {
                tmpIds.add(ad.getId());
            }
            listAds.add(tmpIds);
            tmpIds = new ArrayList<>();
        } else listAds.add(allIds);
        if (filter.getModel() != null && filter.getModel() != "") {
            tmpAds = carFindAdRepo.findCarFindAdByModel(filter.getModel());
            for (CarFindAd ad: tmpAds) {
                tmpIds.add(ad.getId());
            }
            listAds.add(tmpIds);
            tmpIds = new ArrayList<>();
        } else listAds.add(allIds);
        if (filter.getShifter() != null && filter.getShifter() != "") {
            tmpAds = carFindAdRepo.findCarFindAdByShifter(filter.getShifter());
            for (CarFindAd ad: tmpAds) {
                tmpIds.add(ad.getId());
            }
            listAds.add(tmpIds);
            tmpIds = new ArrayList<>();
        } else listAds.add(allIds);

        tmpAds = carFindAdRepo.findCarFindAdByYearIsBetween(filter.getYear1(), filter.getYear2());
        for (CarFindAd ad: tmpAds) {
            tmpIds.add(ad.getId());
        }
        listAds.add(tmpIds);

        tmpIds = new ArrayList<>();
        tmpAds = carFindAdRepo.findCarFindAdByPriceIsBetween(filter.getPrice1(), filter.getPrice2());
        for (CarFindAd ad: tmpAds) {
            tmpIds.add(ad.getId());
        }
        listAds.add(tmpIds);

        tmpIds = new ArrayList<>();
        tmpAds = carFindAdRepo.findCarFindAdByCm3IsBetween(filter.getCm31(), filter.getCm32());
        for (CarFindAd ad: tmpAds) {
            tmpIds.add(ad.getId());
        }
        listAds.add(tmpIds);

        tmpIds = new ArrayList<>();
        tmpAds = carFindAdRepo.findCarFindAdByKwIsBetween(filter.getKw1(), filter.getKw2());
        for (CarFindAd ad: tmpAds) {
            tmpIds.add(ad.getId());
        }
        listAds.add(tmpIds);

        tmpIds = new ArrayList<>();
        tmpAds = carFindAdRepo.findCarFindAdByKmIsBetween(filter.getKm1(), filter.getKm2());
        for (CarFindAd ad: tmpAds) {
            tmpIds.add(ad.getId());
        }
        listAds.add(tmpIds);
        for (int i = 1; i < 12; i++) {
            listAds.get(0).retainAll(listAds.get(i));
        }
        List<CarFindAd> finalAdList = new ArrayList<>();
        CarFindAd finalAd = new CarFindAd();
        for (String s: listAds.get(0)) {
            finalAd = carFindAdRepo.findCarFindAdById(s);
            finalAdList.add(finalAd);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(finalAdList);
    }

    @PostMapping(path="/api/ads/action", produces = "application/json")
    public ResponseEntity createAd(@RequestBody CarFindAd adCreate) {
        carFindAdRepo.save(adCreate);
        CarFindUser usr = carFindUserRepo.findCarFindUserByUsername(adCreate.getOwner());
        carFindUserRepo.delete(usr);
        Map<String,List<String>> tmpAds = usr.getAds();
        if (tmpAds != null) {
            if (tmpAds.get("activeAds") != null){
                tmpAds.get("activeAds").add(adCreate.getId());
            } else {
                tmpAds = new HashMap<>();
                tmpAds.put("activeAds", Collections.singletonList(adCreate.getId()));
            }
        } else {
            tmpAds = new HashMap<>();
            tmpAds.put("activeAds", Collections.singletonList(adCreate.getId()));
        }
        usr.setAds(tmpAds);
        carFindUserRepo.save(usr);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Successfully created ad");
    }

    @PutMapping(path="/api/ads/action")
    public ResponseEntity updateAd(@RequestBody CarFindAd adUpdate) {
        CarFindAd ad = carFindAdRepo.findCarFindAdById(adUpdate.getId());
        carFindAdRepo.delete(ad);
        carFindAdRepo.save(adUpdate);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Updated ad successfully");
    }

    @DeleteMapping(path="/api/ads/action")
    public ResponseEntity deleteAd(@RequestBody DeleteAd delAd) {
        CarFindAd ad = carFindAdRepo.findCarFindAdById(delAd.getId());
        carFindAdRepo.delete(ad);
        CarFindUser usr = carFindUserRepo.findCarFindUserByUsername(ad.getOwner());
        carFindUserRepo.delete(usr);
        Map<String,List<String>> tmpAds = usr.getAds();
        tmpAds.get("activeAds").remove(delAd.getId());
        usr.setAds(tmpAds);
        carFindUserRepo.save(usr);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Deleted ad successfully");
    }
}
