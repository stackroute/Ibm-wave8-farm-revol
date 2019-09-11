package com.stackroute.consumerprofileservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Set;

@Document(collection = "consumers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Consumer {
  /*  public static final String SEQUENCE_NAME = "database_sequence";
    @Id
    private Long id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)*/
  @Id
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private Long phoneNumber;
    private String aadhar;
    @DBRef
    private Set<Role> roles;
    ArrayList<ConsumerOrder> consumerOrders = new ArrayList<ConsumerOrder>();


}
