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

    @Autowired
    public BookingListener(FarmerDetailsService farmerDetailsService){
        this.farmerDetailsService = farmerDetailsService;
    }

    Land land;

    @KafkaListener(topics = "landId", groupId = "group_strings", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJsonLandId(String landId) {
        System.out.println("Consumed landid: " + landId);
        this.land.setId(landId);
    }

    @KafkaListener(topics = "landOrder", groupId = "group_landOrders", containerFactory = "kafkaListenerContainerFactoryLandOrder")
    public void consumerJsonConsumer(LandOrder landOrder) {
        System.out.println(landOrder);
        this.land.getLandOrders().add(landOrder);
    }
}
