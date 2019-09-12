package com.stackroute.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LandOrder {
    @Transient
    public static final String SEQUENCE_NAME="users_sequence";

    @Id
    private Long orderId;
    private String consumerId;
    private String crop;
    private double price;
    private LocalDateTime time;
}
