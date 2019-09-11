package com.stackroute.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Land {

//    @Transient
//    public static final String SEQUENCE_NAME="users_sequence";

    private String id;
    Long farmerId;
    float landSize;
    double landPrice;
    //Location of the Land
    String location;
    ArrayList<String> crops;
    String image;

    //All the orders of the corresponding land
    List<Order> orders;


    public Land() {
    }

    public Land(String id, float landSize, double landPrice, String location, ArrayList<String> crops, String image, List<Order> orders) {
        this.id = id;
        this.landSize = landSize;
        this.landPrice = landPrice;
        this.location = location;
        this.crops = crops;
        this.image = image;
        this.orders = orders;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
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
        return crops;
    }

    public void setCrops(ArrayList<String> crops) {
        this.crops = crops;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Land{" +
                "id=" + id +
                ", landSize=" + landSize +
                ", landPrice=" + landPrice +
                ", location='" + location + '\'' +
                ", crops=" + crops +
                ", image='" + image + '\'' +
                ", orders=" + orders +
                '}';
    }
}
