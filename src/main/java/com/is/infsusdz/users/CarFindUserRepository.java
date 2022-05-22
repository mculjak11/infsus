package com.is.infsusdz.users;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarFindUserRepository extends MongoRepository<CarFindUser, String> {
    public CarFindUser findCarFindUserByUsername(String userName);
    public CarFindUser findCarFindUserByEmailAndPassword(String email, String password);
}
