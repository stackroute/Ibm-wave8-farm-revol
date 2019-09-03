package com.stackroute.booking.model;

import java.util.ArrayList;
import java.util.Set;


public class Consumer {

    private String id;
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private Set<Role> roles;
    ArrayList<ConsumerOrder> consumerOrders;

    public Consumer() {
    }

    public Consumer(String id, String email, String password, String fullname, boolean enabled, Set<Role> roles, ArrayList<ConsumerOrder> consumerOrders) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.enabled = enabled;
        this.roles = roles;
        this.consumerOrders = consumerOrders;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public ArrayList<ConsumerOrder> getConsumerOrders() {
        return consumerOrders;
    }

    public void setConsumerOrders(ArrayList<ConsumerOrder> consumerOrders) {
        this.consumerOrders = consumerOrders;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", orders=" + consumerOrders +
                '}';
    }
}
