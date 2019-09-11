package com.stackroute.booking.controller;

import com.stackroute.booking.model.Consumer;
import com.stackroute.booking.model.ConsumerOrder;
import com.stackroute.booking.model.Land;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingController {
    String cropName;
    String landId;
    @Autowired
    private KafkaTemplate<String, Consumer> kafkaTemplateConsumer;
    private static String TOPIC = "consumer";

    @KafkaListener(topics = "land", groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJsonLand(String landId) {
        System.out.println("Consumed JSON Message of landid: " + landId);
        this.landId= landId;
    }

    @KafkaListener(topics = "crop", groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJsonCrop(String cropName) {
        System.out.println("Consumed JSON Message: of cropid " + cropName);
        this.cropName = cropName;
    }


    @KafkaListener(topics = "consumertopic", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactoryConsumer")
    public void consumerJsonConsumer(Consumer consumer) {
        System.out.println("Consumed JSON Message of conmsumer: " + consumer);
        ConsumerOrder consumerOrder = new ConsumerOrder(1,landId,"2",cropName,2500, null);
        consumer.getConsumerOrders().add(consumerOrder);
        System.out.println(consumer);
        kafkaTemplateConsumer.send(TOPIC,consumer);

    }


}
