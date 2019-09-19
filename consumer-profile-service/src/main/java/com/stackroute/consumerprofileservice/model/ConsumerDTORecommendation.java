package com.stackroute.consumerprofileservice.model;

import org.springframework.data.annotation.Id;

public class ConsumerDTORecommendation {
    @Id
    private String email;
    private String fullname;

    public ConsumerDTORecommendation(String email, String fullname) {
        this.email = email;
        this.fullname = fullname;
    }

    public ConsumerDTORecommendation() {
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
        return "ConsumerDTORecommendation{" +
                "email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
