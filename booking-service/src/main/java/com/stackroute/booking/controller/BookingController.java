package com.stackroute.booking.controller;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.booking.model.BookingDTORecommendation;
import com.stackroute.booking.service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/booking")
=======
import com.stackroute.booking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
>>>>>>> 1f72c3604b0f1dba68d5d94b00b4830c03a51fc9
public class BookingController {
    String cropName;
    Long landId;

    @Autowired
    private KafkaTemplate<String, Consumer> kafkaTemplateConsumer;
    private static String TOPIC = "consumer";

    @Autowired
    private KafkaTemplate<String, LandOrder> kafkaTemplateLandOrder;

    @Autowired
    private KafkaTemplate<String, String > kafkaTemplate;


    private static String TOPIC1 = "landId";
    private static String TOPIC2 = "landOrder";


    // @KafkaListener(topics = "land", groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    // public void consumerJsonLandId(String landId) {
    //     System.out.println("Consumed landid: " + landId);
    //     this.landId = landId;
    // }

<<<<<<< HEAD

    @Autowired
    BookingDetailsService bookingDetailsService;
    @PostMapping("/recommend")
    public String recommend(@RequestBody BookingDTORecommendation bookingDTORecommendation) throws JsonProcessingException {

        bookingDetailsService.recommend(bookingDTORecommendation);
        return "Published";
    }

=======
    @KafkaListener(topics = "crop" , groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJsonCrop(String cropName) {
        System.out.println("Consumed crop: " + cropName);
        this.cropName = cropName;
    }


    @KafkaListener(topics = "consumertopic", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactoryConsumer")
    public void consumerJsonConsumer(Consumer consumer) {
        System.out.println("Consumed JSON Message of conmsumer: " + consumer);
        ConsumerOrder consumerOrder = new ConsumerOrder(1,landId,"2",cropName,2500, null);
        consumer.getConsumerOrders().add(consumerOrder);
        System.out.println(consumer);
        kafkaTemplateConsumer.send(TOPIC, consumer);

        LandOrder landOrder = new LandOrder(1, consumer.getEmail(),cropName,2500,null);
        System.out.println(landOrder);
        // kafkaTemplate.send(TOPIC1,landId);
        kafkaTemplateLandOrder.send(TOPIC2,landOrder);
    }


    // @KafkaListener(topics = "consumertopic", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactoryConsumer")
    // public void consumerJsonConsumer(Consumer consumer) {
    //     System.out.println("Consumed JSON Message of conmsumer: " + consumer);
    //     ConsumerOrder consumerOrder = new ConsumerOrder(1,landId,"2",cropName,2500, null);
    //     consumer.getConsumerOrders().add(consumerOrder);
    //     System.out.println(consumer);
    //     kafkaTemplateConsumerLong.send(TOPIC,consumer);

    // }


>>>>>>> 1f72c3604b0f1dba68d5d94b00b4830c03a51fc9
}
