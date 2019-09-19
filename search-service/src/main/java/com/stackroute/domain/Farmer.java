package com.stackroute.domain;


import java.util.ArrayList;
import java.util.Set;

public class Farmer {
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private Long phoneNumber;
    private String aadhar;
    private ArrayList<Land> land;

    private Set<Role> roles;

    public Farmer() {
    }

    public Farmer(String email, String password, String fullname, boolean enabled, Long phoneNumber, String aadhar, ArrayList<Land> land, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.aadhar = aadhar;
        this.land = land;
        this.roles = roles;
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

    public ArrayList<Land> getLand() {
        return land;
    }

    public void setLand(ArrayList<Land> land) {
        this.land = land;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", enabled=" + enabled +
                ", phoneNumber=" + phoneNumber +
                ", aadhar='" + aadhar + '\'' +
                ", land=" + land +
                ", roles=" + roles +
                '}';
    }
}

