package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FarmsList {

    String location;
    String size;
    float rate;
    String farmer_Name;
   // Date availableFrom;


}
