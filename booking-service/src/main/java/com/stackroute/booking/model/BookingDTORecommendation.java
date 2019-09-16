package com.stackroute.booking.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingDTORecommendation {

    private String consumerEmail;
    private String farmerEmail;
}
