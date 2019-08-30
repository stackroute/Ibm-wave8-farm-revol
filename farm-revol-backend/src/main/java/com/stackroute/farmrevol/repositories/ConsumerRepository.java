package com.stackroute.farmrevol.repositories;

import com.stackroute.farmrevol.models.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {

}
