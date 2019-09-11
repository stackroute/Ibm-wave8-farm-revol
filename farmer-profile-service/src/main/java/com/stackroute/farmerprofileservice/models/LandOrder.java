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
    private int orderId;
    private Long landId;
    private String consumerId;
    private String crop;
    private int price;
    private LocalDateTime time;
}
