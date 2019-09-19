package com.stackroute.consumerprofileservice.listener;

import com.stackroute.consumerprofileservice.model.Consumer;
import com.stackroute.consumerprofileservice.model.Crop;
import com.stackroute.consumerprofileservice.repository.ConsumerRepository;
import com.stackroute.consumerprofileservice.service.ConsumerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerListener
{

    @Autowired
    ConsumerRepository consumers;

    ConsumerDetailsService consumerDetailsService;


    @Autowired
    public ConsumerListener(ConsumerDetailsService consumerDetailsService) {

        this.consumerDetailsService = consumerDetailsService;
    }

    @KafkaListener(topics = "fr-ookedconsumer", groupId = "group_consumer_booking", containerFactory = "kafkaListenerContainerFactoryConsumer")
    public void consumerJsonConsumer(Consumer consumer) {
        System.out.println("Consumed JSON Message from booking:  " + consumer);
      //  consumers.delete(consumer);
        consumerDetailsService.saveConsumer(consumer);
        System.out.println("Consumer saved");
    }
}
