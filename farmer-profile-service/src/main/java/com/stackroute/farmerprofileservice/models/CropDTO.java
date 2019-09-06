package com.stackroute.farmerprofileservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "crops")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDTO {

    @Id
    int id;
    String cropName;
    Land land;
}

