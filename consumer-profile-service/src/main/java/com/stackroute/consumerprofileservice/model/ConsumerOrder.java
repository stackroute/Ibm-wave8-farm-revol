package com.stackroute.consumerprofileservice.model;

import java.time.LocalDateTime;

public class ConsumerOrder
{
    private int orderId;
    private String landId;
    private String farmerId;
    private String crop;
    private int price;
    private LocalDateTime time;

    @Override
    public String toString() {
        return "ConsumerOrder{" +
                "orderId=" + orderId +
                ", landId=" + landId +
                ", farmerId='" + farmerId + '\'' +
                ", crop='" + crop + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getLandId() {
        return landId;
    }

    public void setLandId(String landId) {
        this.landId = landId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ConsumerOrder(int orderId, String landId, String farmerId, String crop, int price, LocalDateTime time) {
        this.orderId = orderId;
        this.landId = landId;
        this.farmerId = farmerId;
        this.crop = crop;
        this.price = price;
        this.time = time;
    }

    public ConsumerOrder() {
    }
}

