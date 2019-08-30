package com.djamware.springangularauth.repositories;

import com.djamware.springangularauth.models.Consumer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, Long> {
 Consumer findByEmail(String email);
}
