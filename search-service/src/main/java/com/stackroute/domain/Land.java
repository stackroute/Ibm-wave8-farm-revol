package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Land {

//    @Transient
//    public static final String SEQUENCE_NAME="users_sequence";

    private Long id;
    String farmerId;
    float landSize;
    double landPrice;
    //Location of the Land
    String location;
    ArrayList<String> crops;
    String image;

    //All the orders of the corresponding land
    List<Order> orders;

}
