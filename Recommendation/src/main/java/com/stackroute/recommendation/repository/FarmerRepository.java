package com.stackroute.recommendation.repository;

import com.stackroute.recommendation.model.Farmer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmerRepository extends Neo4jRepository<Farmer,String> {

    @Query("MATCH (a:Consumer),(b:Farmer)\n" +
            "WHERE a.email = {consumerEmail} AND b.email = {farmerEmail}\n" +
            "CREATE (a)-[r:Booked]->(b)\n" +
            "RETURN type(r)")
    String createRelation(@Param("consumerEmail") String consumerEmail, @Param("farmerEmail")String farmerEmail);


    @Query("match (c:Consumer{email:{email}})-[r1:Booked]->(f:Farmer)\n" +
            "            match (f:Farmer)<-[r2:Booked]-(c1:Consumer)\n" +
            "                match (c1:Consumer)-[r3:Booked]->(f1:Farmer) return distinct f1")
    List<Farmer> recommend(@Param("email") String email);
}
