package com.djamware.springangularauth.services;

import com.djamware.springangularauth.exception.UserNotFoundException;
import com.djamware.springangularauth.models.Consumer;
import com.djamware.springangularauth.models.Farmer;
import com.djamware.springangularauth.models.Land;
import com.djamware.springangularauth.models.Role;
import com.djamware.springangularauth.repositories.ConsumerRepository;
import com.djamware.springangularauth.repositories.RoleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Consumer findConsumerByEmail(String email) {
        return consumerRepository.findByEmail(email);
    }
    public void saveConsumer(Consumer consumer) {
        consumer.setPassword(bCryptPasswordEncoder.encode(consumer.getPassword()));
        consumer.setEnabled(true);
       /* Role userRole = roleRepository.findByRole("consumer");
        consumer.setRoles(new HashSet<>(Arrays.asList(userRole)));*/

        consumer.setId(sequenceGenerator.getNextSequence(Consumer.SEQUENCE_NAME));
        for(int i=0;i<consumer.getOrders().size(); i++) {
            consumer.getOrders().get(i).setOrderId(sequenceGenerator.getNextSequence((Land.SEQUENCE_NAME)));
        }
        consumerRepository.save(consumer);
    }

    public Consumer deleteConsumer(Long Id) {
        Consumer consumer = getConsumerById(Id);
        consumerRepository.deleteById(Id);
        return consumer;
    }


    public Consumer getConsumerById(Long Id) throws UserNotFoundException {
        System.out.println(Id);
        if(!consumerRepository.findById(Id).isPresent()) {
            throw new UserNotFoundException();
        }
        return consumerRepository.findById(Id).get();
    }


    public Consumer updateConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }
}
