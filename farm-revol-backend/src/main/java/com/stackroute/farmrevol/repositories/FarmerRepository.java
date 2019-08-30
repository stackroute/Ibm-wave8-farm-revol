package com.stackroute.farmrevol.repositories;

import com.stackroute.farmrevol.models.Farmer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmerRepository extends MongoRepository<Farmer, String> {


}
