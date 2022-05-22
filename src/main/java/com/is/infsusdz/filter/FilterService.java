package com.is.infsusdz.filter;

import com.is.infsusdz.ads.CarFindAd;
import com.is.infsusdz.ads.CarFindAdRepository;
import com.is.infsusdz.users.CarFindUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class FilterService {

    @Autowired
    private CarFindAdRepository carFindAdRepo;

    @Autowired
    private CarFindUserRepository carFindUserRepo;

    public ResponseEntity adsFilter(FilteredAd filter) {
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
}
