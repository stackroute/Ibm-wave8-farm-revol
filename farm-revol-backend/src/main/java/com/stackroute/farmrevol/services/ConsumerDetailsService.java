package com.stackroute.farmrevol.services;

import com.stackroute.farmrevol.exception.UserNotFoundException;
import com.stackroute.farmrevol.models.*;
import com.stackroute.farmrevol.repositories.ConsumerRepository;
import com.stackroute.farmrevol.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsumerDetailsService {
    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Consumer findConsumerByEmail(String email) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Consumer consumer= (Consumer) mongoTemplate.findOne(query,Consumer.class);

        return consumer;
    }
    public void saveConsumer(Consumer consumer) {
        consumer.setPassword(bCryptPasswordEncoder.encode(consumer.getPassword()));
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
}
