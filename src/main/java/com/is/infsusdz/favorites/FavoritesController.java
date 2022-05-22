package com.is.infsusdz.favorites;

import com.is.infsusdz.ads.CarFindAdRepository;
import com.is.infsusdz.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FavoritesController {

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    FavoritesService fs = new FavoritesService();

    @PostMapping(path="/api/ads/getFavorites")
    public ResponseEntity getFavoriteAds(@RequestBody FavoriteGet favGet) {
        return fs.getFavorite(favGet);
    }

    @PostMapping(path="/api/ads/addFavorite")
    public ResponseEntity setFavoriteAd(@RequestBody FavoriteData favoriteData) {
        return fs.setFavorite(favoriteData);
    }

    @DeleteMapping(path="/api/ads/delFavorite")
    public ResponseEntity deleteFavoriteAd(@RequestBody FavoriteData favoriteData) {
        return fs.deleteFavorite(favoriteData);
    }
}
