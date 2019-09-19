package com.stackroute.recommendation.model;

import java.util.ArrayList;

public class RecommendedLandsFromConsumer{

    private ArrayList<Land> land ;

    public ArrayList<Land> getLand() {
        return land;
    }

    public void setLand(ArrayList<Land> land) {
        this.land = land;
    }

    public RecommendedLandsFromConsumer(ArrayList<Land> land) {
        this.land = land;
    }

    public RecommendedLandsFromConsumer() {
    }

    @Override
    public String toString() {
        return "RecommendedLandsFromConsumer{" +
                "land=" + land +
                '}';
    }
}
