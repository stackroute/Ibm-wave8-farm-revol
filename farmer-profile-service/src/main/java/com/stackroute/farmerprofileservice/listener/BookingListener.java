package com.stackroute.farmerprofileservice.listener;

import com.stackroute.farmerprofileservice.models.Land;
import com.stackroute.farmerprofileservice.models.LandOrder;
import com.stackroute.farmerprofileservice.repository.FarmerRepository;
import com.stackroute.farmerprofileservice.service.FarmerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingListener {

    @Autowired
    FarmerRepository farmerRepository;

    FarmerDetailsService farmerDetailsService;
   // String farmerId;

    @Autowired
    public BookingListener(FarmerDetailsService farmerDetailsService){
        this.farmerDetailsService = farmerDetailsService;
    }

    Land land=new Land();
    Long landid;

    @KafkaListener(topics = "fr-bookedland", groupId = "group_land", containerFactory = "kafkaListenerContainerFactoryLand")
    public void consumerJsonConsumer(Land land) {

      //  System.out.println(land);
        farmerDetailsService.updateLandDetailsByFarmerId(land,land.getFarmerId(),land.getId());
        System.out.println("updated :"+land);


        farmerDetailsService.getAllLandsOfFarmerByEmail(land.getFarmerId());
        System.out.println("done");
    }
}
