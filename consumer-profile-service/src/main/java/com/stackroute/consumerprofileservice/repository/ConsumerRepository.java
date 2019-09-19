package com.stackroute.consumerprofileservice.repository;


import com.stackroute.consumerprofileservice.model.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {

}
