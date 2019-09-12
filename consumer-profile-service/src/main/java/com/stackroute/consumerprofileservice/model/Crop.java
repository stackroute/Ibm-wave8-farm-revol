package com.stackroute.consumerprofileservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Crop {

    @Id
    String id;
    String cropName;
    //Land farms;
    ArrayList<Land> lands = new ArrayList<Land>();

}

