package com.djamware.springangularauth.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Document(collection = "farmers")
public class Farmer {

    public static final String SEQUENCE_NAME ="database_sequence" ;

    @Id
    private Long id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private Long phoneNumber;
    private String aadhar;
    private ArrayList<Land> land;
    @DBRef
    private Set<Role> roles;

    public Farmer() {
    }

    public Farmer(Long id, String email, String password, String fullname, boolean enabled, Long phoneNumber, String aadhar, ArrayList<Land> land, Set<Role> roles) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public ArrayList<Land> getLand() {
        return land;
    }

    public void setLand(ArrayList<Land> land) {
        this.land = land;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", email='" + email + '\'' +
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

