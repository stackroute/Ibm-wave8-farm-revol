package com.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.time.LocalDateTime;


public class Order {
//    @Transient
//    public static final String SEQUENCE_NAME="users_sequence";


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
