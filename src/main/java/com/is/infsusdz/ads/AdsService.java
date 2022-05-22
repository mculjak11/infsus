package com.is.infsusdz.ads;

import com.is.infsusdz.users.CarFindUser;
import com.is.infsusdz.users.CarFindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public class AdsService {

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    public ResponseEntity getAll() {
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

    public ResponseEntity getById(String id) {
        CarFindAd adById = carFindAdRepo.findCarFindAdById(id);
        if (adById != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(adById);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity getByOwner(String owner) {
        List<CarFindAd> carAd = carFindAdRepo.findCarFindAdByOwner(owner);
        if (carAd.isEmpty()) {
            return ResponseEntity.ok() .contentType(MediaType.TEXT_PLAIN) .body("There are no ads from " + owner);
        } else {
            return ResponseEntity.ok() .contentType(MediaType.APPLICATION_JSON) .body(carAd);
        }
    }


    public ResponseEntity getByTitle(String title) {
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

    public ResponseEntity create(CarFindAd adCreate) {
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

    public ResponseEntity update(CarFindAd adUpdate) {
        CarFindAd ad = carFindAdRepo.findCarFindAdById(adUpdate.getId());
        carFindAdRepo.delete(ad);
        carFindAdRepo.save(adUpdate);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Updated ad successfully");
    }

    public ResponseEntity delete(DeleteAd delAd) {
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
