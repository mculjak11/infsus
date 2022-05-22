package com.is.infsusdz.filter;

import com.is.infsusdz.ads.CarFindAdRepository;
import com.is.infsusdz.users.CarFindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    FilterService fs = new FilterService();

    @PostMapping(path="/api/ads/filter", produces = "application/json")
    public ResponseEntity getAdsFilter(@RequestBody FilteredAd filter) {
        return fs.adsFilter(filter);
    }
}
