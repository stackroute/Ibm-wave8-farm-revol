package com.stackroute.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Land {

    public static final String SEQUENCE_NAME="users_sequence";

    private Long id;
    String farmerId;
    float landSize;
    double landPrice;
    //Location of the Land
    String location;
    ArrayList<String> crops;
    String image;

    //All the orders of the corresponding land
    ArrayList<LandOrder> landOrders = new ArrayList<LandOrder>();
}

