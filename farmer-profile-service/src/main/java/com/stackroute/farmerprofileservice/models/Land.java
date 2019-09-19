package com.stackroute.farmerprofileservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;

@Document

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Land {

    @Transient
    public static final String SEQUENCE_NAME="users_sequence";

    @Id
    private Long id;
    String farmerId;
    float landSize;
    double landPrice;
    //Location of the Land
    String location;
    ArrayList<String> crops;
    String image;
    boolean available = true;

    //All the orders of the corresponding land
    ArrayList<LandOrder> landOrders = new ArrayList<LandOrder>();

}
