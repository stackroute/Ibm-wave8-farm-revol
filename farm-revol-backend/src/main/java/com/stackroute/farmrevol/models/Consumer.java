package com.stackroute.farmrevol.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Set;

@Document(collection = "consumers")
public class Consumer {
  /*  public static final String SEQUENCE_NAME = "database_sequence";
    @Id
    private Long id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)*/
  @Id
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private Long phoneNumber;
    private String aadhar;
    @DBRef
    private Set<Role> roles;
    ArrayList<Order> orders;

    public Consumer() {
    }

    public Consumer(String email, String password, String fullname, boolean enabled, Long phoneNumber, String aadhar, Set<Role> roles, ArrayList<Order> orders) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.aadhar = aadhar;
        this.roles = roles;
        this.orders = orders;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", enabled=" + enabled +
                ", phoneNumber=" + phoneNumber +
                ", aadhar='" + aadhar + '\'' +
                ", roles=" + roles +
                ", orders=" + orders +
                '}';
    }
}
