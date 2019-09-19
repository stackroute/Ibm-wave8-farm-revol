package com.stackroute.farmerprofileservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LandOrder {

    private long orderId;
    private String consumerId;
    private String crop;
    private double price;
    private LocalDateTime time;
}
