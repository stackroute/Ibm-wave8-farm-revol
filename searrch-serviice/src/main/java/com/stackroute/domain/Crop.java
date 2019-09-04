package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Crop {

    @Id
    int id;
    String cropName;
    List<FarmsList> farms;

}

