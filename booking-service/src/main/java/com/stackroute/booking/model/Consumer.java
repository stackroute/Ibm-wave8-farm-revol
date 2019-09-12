package com.stackroute.booking.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Consumer {
  /*  public static final String SEQUENCE_NAME = "database_sequence";
    @Id
    private Long id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)*/
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private Long phoneNumber;
    private String aadhar;
    private Set<Role> roles;
//    ArrayList<Order> orders;
    ArrayList<ConsumerOrder> consumerOrders = new ArrayList<ConsumerOrder>();
}
