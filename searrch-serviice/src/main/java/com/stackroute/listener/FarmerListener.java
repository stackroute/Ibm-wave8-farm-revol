package com.stackroute.listener;

import com.stackroute.domain.Crop;
import com.stackroute.domain.Farmer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FarmerListener {
    @KafkaListener(topics = "kafka", groupId = "group_crop", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJson(Crop crop) {
        System.out.println("Consumed JSON Message: " + crop);
        crop.getLands().add(crop.getFarms());
        System.out.println(crop);
    }
}
