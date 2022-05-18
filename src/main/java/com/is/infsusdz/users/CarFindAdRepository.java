package com.is.infsusdz.users;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarFindAdRepository extends MongoRepository<CarFindAd, String> {
    public CarFindAd findCarFindAdById(String id);
    public List<CarFindAd> findAll();
    public List<CarFindAd> findCarFindAdByOwner(String owner);
}
