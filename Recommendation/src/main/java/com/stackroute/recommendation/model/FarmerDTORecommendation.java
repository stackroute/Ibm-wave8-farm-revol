package com.stackroute.recommendation.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class FarmerDTORecommendation {

    @Id
    private String email;
    private String fullname;

    public FarmerDTORecommendation(String email, String fullname) {
        this.email = email;
        this.fullname = fullname;
    }

    public FarmerDTORecommendation() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
