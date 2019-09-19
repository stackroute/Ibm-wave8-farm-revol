package com.stackroute.recommendation.model;


import java.time.LocalDateTime;

public class LandOrder {

    private long orderId;
    private String consumerId;
    private String crop;
    private double price;
    private LocalDateTime time;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    public LandOrder(long orderId, String consumerId, String crop, double price, LocalDateTime time) {
        this.orderId = orderId;
        this.consumerId = consumerId;
        this.crop = crop;
        this.price = price;
        this.time = time;
    }

    public LandOrder() {
    }

    @Override
    public String toString() {
        return "LandOrder{" +
                "orderId=" + orderId +
                ", consumerId='" + consumerId + '\'' +
                ", crop='" + crop + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
