package com.is.infsusdz.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarFindAdRepository extends MongoRepository<CarFindAd, String> {
    public CarFindAd findCarFindAdById(String id);
    public List<CarFindAd> findAll();
    public List<CarFindAd> findCarFindAdByYearIsBetween(Integer first, Integer second);
    public List<CarFindAd> findCarFindAdByPriceIsBetween(Integer price1, Integer price2);
    public List<CarFindAd> findCarFindAdByKwIsBetween(Integer kw1, Integer kw2);
    public List<CarFindAd> findCarFindAdByCm3IsBetween(Integer cm31, Integer cm32);
    public List<CarFindAd> findCarFindAdByKmIsBetween(Integer km1, Integer km2);
    public List<CarFindAd> findCarFindAdByAdType(String adType);
    public List<CarFindAd> findCarFindAdByMake(String make);
    public List<CarFindAd> findCarFindAdByModel(String model);
    public List<CarFindAd> findCarFindAdByCategory(String category);
    public List<CarFindAd> findCarFindAdByFuel(String fuel);
    public List<CarFindAd> findCarFindAdByShifter(String shifter);
    public List<CarFindAd> findCarFindAdByCity(String city);
}
