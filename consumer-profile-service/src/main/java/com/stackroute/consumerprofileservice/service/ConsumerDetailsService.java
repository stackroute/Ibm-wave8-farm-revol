package com.stackroute.consumerprofileservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.stackroute.consumerprofileservice.exception.UserNotFoundException;
import com.stackroute.consumerprofileservice.model.*;

import com.stackroute.consumerprofileservice.repository.ConsumerRepository;
import com.stackroute.consumerprofileservice.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConsumerDetailsService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private KafkaTemplate<String, ConsumerDTORecommendation> kafkaTemplate1;

    private static String TOPIC1="ConsumerRecommend";

    public Consumer findConsumerByEmail(String email) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Consumer consumer= (Consumer) mongoTemplate.findOne(query,Consumer.class);

        return consumer;
    }
    public void saveConsumer(Consumer consumer) {
        consumer.setPassword((bCryptPasswordEncoder.encode(consumer.getPassword())));
        consumer.setEnabled(true);
       /* Role userRole = roleRepository.findByRole("consumer");
        consumer.setRoles(new HashSet<>(Arrays.asList(userRole)));*/

        //consumer.setId(sequenceGenerator.getNextSequence(Consumer.SEQUENCE_NAME));
        for(int i=0;i<consumer.getOrders().size(); i++) {
            consumer.getOrders().get(i).setOrderId(sequenceGenerator.getNextSequence((Order.SEQUENCE_NAME)));
        }
        consumerRepository.save(consumer);
    }

    public Consumer deleteConsumer(String email) {
        Consumer consumer = getConsumerByEmail(email);
        consumerRepository.deleteById(email);
        return consumer;
    }

    public Consumer getConsumerByEmail(String email) throws UserNotFoundException {
        System.out.println(email);
        if(!consumerRepository.findById(email).isPresent()) {
            throw new UserNotFoundException();
        }
        return consumerRepository.findById(email).get();
    }

    public Consumer updateConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public String recommend(Consumer consumer) throws JsonProcessingException {
        ConsumerDTORecommendation consumerDTORecommendation=new ConsumerDTORecommendation();
        consumerDTORecommendation.setFullname(consumer.getFullname());
        consumerDTORecommendation.setEmail(consumer.getEmail());
        System.out.println(consumerDTORecommendation);
        kafkaTemplate1.send(TOPIC1, consumerDTORecommendation);
        return "published to recommend";
    }


    @KafkaListener(topics = "RecommendedFarmers", groupId = "recommendations", containerFactory = "kafkaListenerContainerFactory")
    public void bookingJson(Farmers farmers) {
        System.out.println("Consumed Farmers who recommended: "+farmers );
    }
}
