package com.stackroute.consumerprofileservice.service;

import com.stackroute.consumerprofileservice.exception.UserNotFoundException;
import com.stackroute.consumerprofileservice.model.Consumer;
import com.stackroute.consumerprofileservice.model.Land;
import com.stackroute.consumerprofileservice.model.Order;
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

import java.util.ArrayList;

@Service
public class ConsumerDetailsService {
    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private KafkaTemplate<String, Consumer> kafkaTemplateConsumer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Land> kafkaTemplateLand;



    private static String TOPIC2 = "consumertopic";

    private static String TOPIC3 = "land";

    private static String TOPIC4 = "crop";

    public Consumer findConsumerByEmail(String email) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Consumer consumer= (Consumer) mongoTemplate.findOne(query,Consumer.class);
        return consumer;
    }
    public void saveConsumer(Consumer consumer) {

        consumer.setPassword(consumer.getPassword());

        consumer.setPassword((consumer.getPassword()));
        consumer.setEnabled(true);
        //consumer.setConsumerOrders(new ArrayList<>());
       /* Role userRole = roleRepository.findByRole("consumer");
        consumer.setRoles(new HashSet<>(Arrays.asList(userRole)));*/

        //consumer.setId(sequenceGenerator.getNextSequence(Consumer.SEQUENCE_NAME));
//        for(int i=0;i<consumer.getOrders().size(); i++) {
//            consumer.getOrders().get(i).setOrderId(sequenceGenerator.getNextSequence((Order.SEQUENCE_NAME)));
//        }

        //Consumer oldConsumer = consumerRepository.findById(consumer.getEmail());
       // consumerRepository.delete(consumer);
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

    public String bookLand(String email, Land land, String cropName){
        Consumer consumer = getConsumerByEmail(email);

        kafkaTemplateConsumer.send(TOPIC2, consumer);
        kafkaTemplateLand.send(TOPIC3,land );
        kafkaTemplate.send(TOPIC4, cropName);


        return "published";
    }

}
