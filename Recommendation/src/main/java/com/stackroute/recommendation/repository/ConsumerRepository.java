package com.stackroute.recommendation.repository;

import com.stackroute.recommendation.model.Consumer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends Neo4jRepository<Consumer,String> {
}
