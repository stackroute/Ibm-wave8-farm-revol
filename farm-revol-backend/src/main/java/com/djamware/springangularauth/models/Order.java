package com.djamware.springangularauth.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    @Id
    Long orderId;
    LocalDateTime time;

    public Order() {
    }

    public Order(Long orderId, LocalDateTime time) {
        this.orderId = orderId;
        this.time = time;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", time=" + time +
                '}';
    }
}
