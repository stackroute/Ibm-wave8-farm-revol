package com.stackroute.recommendation.model;


public class BookingDTORecommendation {

    private String consumerEmail;
    private String farmerEmail;

    public String getConsumerEmail() {
        return consumerEmail;
    }

    public void setConsumerEmail(String consumerEmail) {
        this.consumerEmail = consumerEmail;
    }

    public String getFarmerEmail() {
        return farmerEmail;
    }

    public void setFarmerEmail(String farmerEmail) {
        this.farmerEmail = farmerEmail;
    }

    public BookingDTORecommendation() {
    }

    @Override
    public String toString() {
        return "BookingDTORecommendation{" +
                "consumerEmail='" + consumerEmail + '\'' +
                ", farmerEmail='" + farmerEmail + '\'' +
                '}';
    }
}
