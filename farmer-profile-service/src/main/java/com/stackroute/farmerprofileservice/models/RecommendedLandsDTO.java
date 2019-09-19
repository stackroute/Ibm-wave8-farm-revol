package com.stackroute.farmerprofileservice.models;

import java.util.ArrayList;

public class RecommendedLandsDTO {

    private ArrayList<Land> land ;

    public ArrayList<Land> getLand() {
        return land;
    }

    public void setLand(ArrayList<Land> land) {
        this.land = land;
    }

    public RecommendedLandsDTO(ArrayList<Land> land) {
        this.land = land;
    }

    public RecommendedLandsDTO() {
    }

    @Override
    public String toString() {
        return "RecommendedLandsDTO{" +
                "land=" + land +
                '}';
    }
}
