package com.djamware.springangularauth.listener;

import com.djamware.springangularauth.models.Login;
import com.djamware.springangularauth.repositories.LoginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    private LoginRepository loginRepository;

    public KafkaConsumer(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @KafkaListener(topics = "kafka", groupId = "group_json", containerFactory = "kafkaListenerContainerFactory")
    public void consumeJson(String user) throws IOException {
        System.out.println("Hello Hello");
        System.out.println("Consumed JSON Message: " + user);
        Login userLogin = new ObjectMapper().readValue(user, Login.class);
        loginRepository.save(userLogin);
    }
}

