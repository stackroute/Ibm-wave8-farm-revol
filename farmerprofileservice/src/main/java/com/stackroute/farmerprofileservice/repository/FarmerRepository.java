package com.stackroute.farmerprofileservice.repository;

import com.stackroute.farmerprofileservice.models.Farmer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmerRepository extends MongoRepository<Farmer, String> {


}