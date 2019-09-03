package com.stackroute.booking.model;

import java.util.ArrayList;

public class ConsumerBookingDTO {
    String id;
    String email;
    String password;
    String fullname;
    ArrayList<ConsumerOrder> consumerOrders;

    @Override
    public String toString() {
        return "ConsumerBookingDTO{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", consumerOrders=" + consumerOrders +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public ArrayList<ConsumerOrder> getConsumerOrders() {
        return consumerOrders;
    }

    public void setConsumerOrders(ArrayList<ConsumerOrder> consumerOrders) {
        this.consumerOrders = consumerOrders;
    }

    public ConsumerBookingDTO(String id, String email, String password, String fullname, ArrayList<ConsumerOrder> consumerOrders) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.consumerOrders = consumerOrders;
    }

    public ConsumerBookingDTO() {
    }
}
