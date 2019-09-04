package com.stackroute.listener;

import com.stackroute.domain.Farmer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FarmerListener {
    @KafkaListener(topics = "testing", groupId = "group_farmer", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJson(Farmer farmer) {
        System.out.println("Consumed JSON Message: " + farmer);
        System.out.println(farmer.getEmail());
    }
}
