package com.djamware.springangularauth.repositories;

import com.djamware.springangularauth.models.Farmer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface FarmerRepository extends MongoRepository<Farmer, Long> {
    Farmer findByEmail(String email);
}
