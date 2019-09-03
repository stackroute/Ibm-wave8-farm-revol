package com.stackroute.booking.controller;

import com.stackroute.booking.model.Consumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingController {

    @KafkaListener(topics = "testing", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJson(Consumer consumer) {
        System.out.println("Consumed JSON Message: " + consumer);
    }

//    @KafkaListener(topics="kafka", groupId = "group_id")
//    public void consume(String message) {
//        System.out.println("Consumed message: " + message);
//    }
}
