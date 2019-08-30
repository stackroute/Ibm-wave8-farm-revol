package com.djamware.springangularauth.controllers;

import com.djamware.springangularauth.configs.JwtTokenProvider;
import com.djamware.springangularauth.exception.UserNotFoundException;
import com.djamware.springangularauth.models.*;
import com.djamware.springangularauth.repositories.ConsumerRepository;
import com.djamware.springangularauth.repositories.FarmerRepository;
import com.djamware.springangularauth.services.ConsumerDetailsService;
import com.djamware.springangularauth.services.FarmerDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    ConsumerRepository consumers;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static String TOPIC = "kafka";
    @Autowired
    private ConsumerDetailsService consumerService;



//    @SuppressWarnings("rawtypes")
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody Login data) {
//        try {
//            String username = data.getEmail();
//            System.out.println(data.getPassword());
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
//            String token = jwtTokenProvider.createToken(username);
//            Map<Object, Object> model = new HashMap<>();
//            model.put("username", username);
//            model.put("token", token);
//            return ok(model);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid email/password supplied");
//        }
//    }

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
    public ResponseEntity<?> getConsumerById(@RequestParam("id") long id) throws UserNotFoundException {
        System.out.println(id);
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<>(consumerService.getConsumerById(id), HttpStatus.CREATED);
        return responseEntity;
    }

    @DeleteMapping("register")
    public ResponseEntity<?> deleteConsumer(@RequestParam("id") long id) {
        consumerService.deleteConsumer(id);
        return new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateConsumer(@RequestBody Consumer consumer) {
        consumerService.updateConsumer(consumer);
        return new ResponseEntity<Consumer>(consumer, HttpStatus.OK);
    }



}
