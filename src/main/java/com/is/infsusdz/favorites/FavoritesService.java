package com.is.infsusdz.favorites;

import com.is.infsusdz.ads.CarFindAd;
import com.is.infsusdz.ads.CarFindAdRepository;
import com.is.infsusdz.users.CarFindUser;
import com.is.infsusdz.users.CarFindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

public class FavoritesService {

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    public ResponseEntity getFavorite(FavoriteGet favGet) {
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

    public ResponseEntity setFavorite(FavoriteData favoriteData) {
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

    public ResponseEntity deleteFavorite(FavoriteData favoriteData) {
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
}
