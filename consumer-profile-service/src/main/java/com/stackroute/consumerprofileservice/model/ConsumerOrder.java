package com.stackroute.consumerprofileservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConsumerOrder
{
    private Long orderId;
    private Long landId;
    private String farmerId;
    private String crop;
    private double price;
    private LocalDateTime time;
}

