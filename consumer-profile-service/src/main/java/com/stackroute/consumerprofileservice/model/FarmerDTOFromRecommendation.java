package com.stackroute.consumerprofileservice.model;

public class FarmerDTOFromRecommendation {

    private String email;
    private String fullname;

    public FarmerDTOFromRecommendation() {
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
        return "FarmerDTOFromRecommendation{" +
                "email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
