package com.stackroute.farmrevol.repositories;

import com.stackroute.farmrevol.models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products, String> {
	
	@Override
    void delete(Products deleted);
}