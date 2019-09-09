package com.stackroute.booking.controller;

import com.stackroute.booking.model.Consumer;
import com.stackroute.booking.model.Crop;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingController {

    @KafkaListener(topics = "testing", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactoryConsumer")
    public void consumerJsonConsumer(Consumer consumer) {
        System.out.println("Consumed JSON Message: " + consumer);
    }

    @KafkaListener(topics = "testing", groupId = "group_crop", containerFactory = "kafkaListenerContainerFactoryCrop")
    public void consumerJsonFarmer(Crop crop) {
        System.out.println("Consumed JSON Message: " + crop);
    }
}
