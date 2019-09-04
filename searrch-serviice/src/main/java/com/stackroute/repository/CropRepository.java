package com.stackroute.repository;

import com.stackroute.domain.Crop;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface CropRepository extends MongoRepository<Crop,Integer> {
    @Query("{cropName:'?0'}")
    List<Crop> getCropByName(String name);
}
