package com.stackroute.recommendation.model;

import java.util.List;

public class Farmers {
    List<Farmer> farmers;

    public Farmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }

    public Farmers() {
    }

    public List<Farmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<Farmer> farmers) {
        this.farmers = farmers;

    }

    @Override
    public String toString() {
        return "Farmers{" +
                "farmers=" + farmers +
                '}';
    }
}
