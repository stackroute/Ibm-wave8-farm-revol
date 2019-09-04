package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "crops")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Crop {

    @Id
    int id;
    String cropName;
    List<Land> farms;

}

