package com.stackroute.farmrevol.repositories;

import com.stackroute.farmrevol.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

	User findByEmail(String email);
}