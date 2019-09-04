package com.stackroute.consumerprofileservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.consumerprofileservice.exception.UserNotFoundException;
import com.stackroute.consumerprofileservice.model.Consumer;
import com.stackroute.consumerprofileservice.model.ConsumerDTO;
import com.stackroute.consumerprofileservice.repository.ConsumerRepository;
import com.stackroute.consumerprofileservice.service.ConsumerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository consumers;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static String TOPIC = "kafka";
    @Autowired
    private ConsumerDetailsService consumerService;



    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Consumer consumer) throws JsonProcessingException {
        Consumer consumerExists = consumerService.findConsumerByEmail(consumer.getEmail());
        if (consumerExists != null) {
            throw new BadCredentialsException("Consumer with username: " + consumer.getEmail() + " already exists");
        }
        consumerService.saveConsumer(consumer);
        ConsumerDTO consumerDTO=new ConsumerDTO();
        consumerDTO.setEmail(consumer.getEmail());
        consumerDTO.setPassword(consumer.getPassword());
        consumerDTO.setRole("consumer");
        System.out.println("Consumer DTO=" + consumerDTO);
        kafkaTemplate.send(TOPIC, new ObjectMapper().writeValueAsString(consumerDTO));
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "Consumer registered successfully");
        return ok(model);
    }



    @GetMapping("/register")
    public ResponseEntity<?> getConsumerById(@RequestParam("email") String email) throws UserNotFoundException {
        System.out.println(email);
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<>(consumerService.getConsumerByEmail(email), HttpStatus.CREATED);
        return responseEntity;
    }

    @DeleteMapping("register")
    public ResponseEntity<?> deleteConsumer(@RequestParam("email") String email) {
        consumerService.deleteConsumer(email);
        return new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateConsumer(@RequestBody Consumer consumer) {
        consumerService.updateConsumer(consumer);
        return new ResponseEntity<Consumer>(consumer, HttpStatus.OK);
    }



}
