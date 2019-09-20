package com.stackroute.recommendation.service;


import com.stackroute.recommendation.model.*;
import com.stackroute.recommendation.repository.ConsumerRepository;
import com.stackroute.recommendation.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    FarmerRepository farmerRepository;


    @Autowired
    private KafkaTemplate<String, Farmers> kafkaTemplateRecommendedFarmers;

    private static String TOPIC111= "RecommendedFarmers";

        @KafkaListener(topics = "ConsumerRecommend", groupId = "group_consumer", containerFactory = "kafkaListenerContainerFactory")
        public void consumerJson(ConsumerDTORecommendation consumerDTORecommendation) {
           Consumer consumer1=new Consumer();
           consumer1.setEmail(consumerDTORecommendation.getEmail());
           consumer1.setFullname(consumerDTORecommendation.getFullname());
          consumerRepository.save(consumer1);
            System.out.println("Consumed JSON Message: " +consumer1);
        }

    @KafkaListener(topics = "FarmerRecommend", groupId = "group_farmer", containerFactory = "kafkaListenerContainerFactoryFarmer")
    public void farmerJson(FarmerDTORecommendation farmerDTORecommendation) {
        Farmer farmer1=new Farmer();
        farmer1.setEmail(farmerDTORecommendation.getEmail());
        farmer1.setFullname(farmerDTORecommendation.getFullname());
        farmerRepository.save(farmer1);
        System.out.println("Consumed JSON Message: " +farmer1);
    }

    @KafkaListener(topics = "BookingRecommend", groupId = "booking", containerFactory = "kafkaListenerContainerFactoryBooking")
    public void bookingJson(BookingDTORecommendation bookingDTORecommendation) {

         farmerRepository.createRelation(bookingDTORecommendation.getConsumerEmail(),bookingDTORecommendation.getFarmerEmail());
        System.out.println("Consumed JSON Message from booking: " +bookingDTORecommendation);
    }



    }




