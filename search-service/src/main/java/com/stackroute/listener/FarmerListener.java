package com.stackroute.listener;

import com.stackroute.domain.Crop;
import com.stackroute.domain.Farmer;
import com.stackroute.domain.Land;
import com.stackroute.exception.CropNotFoundException;
import com.stackroute.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class FarmerListener {


    CropService cropService;

    @Autowired
    public FarmerListener(CropService cropService) {

        this.cropService = cropService;
    }


    @KafkaListener(topics = "test-101", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJson(Crop crop) {
        System.out.println("Consumed JSON Message: " + crop);
        crop.getLands().add(crop.getFarms());
        System.out.println();
        String cropName = crop.getCropName();
        Land farms = crop.getFarms();
        System.out.println("this is land info"+farms);

        try {
            Crop updatedCrop = cropService.saveLandByCropName(cropName, farms);
            System.out.println("After kafka, crop is " + updatedCrop);
        } catch (CropNotFoundException ex) {
            System.out.println("Some error occured");

        }
    }


}



