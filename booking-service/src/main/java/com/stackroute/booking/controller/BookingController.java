package com.stackroute.booking.controller;

import com.stackroute.booking.model.*;
import com.stackroute.booking.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingController {
    String cropName;
    Land land = new Land();

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    BookingDTORecommendation bookingDTORecommendation = new BookingDTORecommendation();

    @Autowired
    private KafkaTemplate<String, Consumer> kafkaTemplateConsumer;
    private static String TOPIC = "fr-bookedconsumer";

    @Autowired
    private KafkaTemplate<String , Land> kafkaTemplateLand;
    private static String TOPIC1 = "fr-bookedland";

    @Autowired
    private KafkaTemplate<String, BookingDTORecommendation> kafkaTemplateBookingDTORecommendation;
    private static String TOPIC3 = "fr-BookingRecommend";



         @KafkaListener(topics = "fr-land", groupId = "group_land", containerFactory = "kafkaListenerContainerFactoryLand")
     public void consumerJsonLandId(Land land) {
         System.out.println("Consumed land is: " + land);
         this.land = land;
     }

    @KafkaListener(topics = "fr-crop" , groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    public void consumerJsonCrop(String cropName) {
        System.out.println("Consumed crop: " + cropName);
        this.cropName = cropName;
    }



    @KafkaListener(topics = "fr-consumertopic", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactoryConsumer")
    public void consumerJsonConsumer(Consumer consumer) {
        System.out.println("Consumed JSON Message of conmsumer: " + consumer);
        ConsumerOrder consumerOrder = new ConsumerOrder(sequenceGenerator.getNextSequence(ConsumerOrder.SEQUENCE_NAME),land.getId(),land.getFarmerId(),cropName,land.getLandPrice(), null);
        consumer.getConsumerOrders().add(consumerOrder);
        System.out.println(consumer);

        kafkaTemplateConsumer.send(TOPIC, consumer);
        bookingDTORecommendation.setConsumerEmail(consumer.getEmail());
        bookingDTORecommendation.setFarmerEmail(land.getFarmerId());
        System.out.println(bookingDTORecommendation);
        kafkaTemplateBookingDTORecommendation.send(TOPIC3, bookingDTORecommendation);

//        LandOrder landOrder1 = new LandOrder();
//        landOrder1.setOrderId(sequenceGenerator.getNextSequence(LandOrder.SEQUENCE_NAME));

        LandOrder landOrder = new LandOrder(sequenceGenerator.getNextSequence(LandOrder.SEQUENCE_NAME), consumer.getEmail(),cropName,land.getLandPrice(),null);
        this.land.getLandOrders().add(landOrder);
        System.out.println("New land"+land);
        kafkaTemplateLand.send(TOPIC1,land);
    }

}
