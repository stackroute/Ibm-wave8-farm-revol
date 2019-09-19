package com.stackroute.consumerprofileservice.model;

import com.stackroute.consumerprofileservice.model.FarmerDTOFromRecommendation;

import java.util.List;

public class Farmers {
    List<FarmerDTOFromRecommendation> farmers;

    @Override
    public String toString() {
        return "Farmers{" +
                "farmers=" + farmers +
                '}';
    }

    public Farmers(List<FarmerDTOFromRecommendation> farmers) {
        this.farmers = farmers;
    }

    public Farmers() {
    }

    public List<FarmerDTOFromRecommendation> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<FarmerDTOFromRecommendation> farmers) {
        this.farmers = farmers;
    }
}
