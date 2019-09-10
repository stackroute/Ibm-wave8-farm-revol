package com.stackroute.booking.controller;

import com.stackroute.booking.model.Consumer;
import com.stackroute.booking.model.Land;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingController {

    @KafkaListener(topics = "testing", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactoryConsumer")
    public void consumerJsonConsumer(Consumer consumer) {
        System.out.println("Consumed JSON Message of conmsumer: " + consumer);
    }

    @KafkaListener(topics = "land", groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJsonLand(String landId) {
        System.out.println("Consumed JSON Message of landid: " + landId);
    }

    @KafkaListener(topics = "crop", groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJsonCrop(String cropName) {
        System.out.println("Consumed JSON Message: of cropid " + cropName);
    }
}
