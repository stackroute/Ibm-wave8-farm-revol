package com.stackroute.recommendation.model;


import java.util.ArrayList;

public class Land {


    public static final String SEQUENCE_NAME="users_sequence";

    private Long id;
    String farmerId;
    float landSize;
    double landPrice;
    //Location of the Land
    String location;
    ArrayList<String> crop;
    String image;
    boolean available = true;


    //All the orders of the corresponding land
    ArrayList<LandOrder> landOrders = new ArrayList<LandOrder>();

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public float getLandSize() {
        return landSize;
    }

    public void setLandSize(float landSize) {
        this.landSize = landSize;
    }

    public double getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(double landPrice) {
        this.landPrice = landPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getCrops() {
        return crop;
    }

    public void setCrops(ArrayList<String> crops) {
        this.crop = crops;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ArrayList<LandOrder> getLandOrders() {
        return landOrders;
    }

    public void setLandOrders(ArrayList<LandOrder> landOrders) {
        this.landOrders = landOrders;
    }

    public Land() {
    }

    public Land(Long id, String farmerId, float landSize, double landPrice, String location, ArrayList<String> crops, String image, boolean available, ArrayList<LandOrder> landOrders) {
        this.id = id;
        this.farmerId = farmerId;
        this.landSize = landSize;
        this.landPrice = landPrice;
        this.location = location;
        this.crop = crops;
        this.image = image;
        this.available = available;
        this.landOrders = landOrders;
    }

    @Override
    public String toString() {
        return "Land{" +
                "id=" + id +
                ", farmerId='" + farmerId + '\'' +
                ", landSize=" + landSize +
                ", landPrice=" + landPrice +
                ", location='" + location + '\'' +
                ", crops=" + crop +
                ", image='" + image + '\'' +
                ", available=" + available +
                ", landOrders=" + landOrders +
                '}';
    }
}
