package com.stackroute.consumerprofileservice.listener;

import com.stackroute.consumerprofileservice.model.Crop;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SearchListener {

    @KafkaListener(topics = "fr-search", groupId = "group_crop", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJson(Crop crop) {
        System.out.println("Consumed JSON Message: " + crop);
    }




}
