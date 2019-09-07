package com.stackroute.loginservice.listener;

import com.stackroute.loginservice.dao.UserDao;
import com.stackroute.loginservice.model.DAOUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    @Autowired
    UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @KafkaListener(topics="kafka",groupId = "group_id")
    public void consume(String daoUser) throws IOException {
        System.out.println("Inside ");
        System.out.println("consumed JSON Message" +daoUser);
        DAOUser obj=new ObjectMapper().readValue(daoUser, DAOUser.class);
        System.out.println(passwordEncoder.encode(obj.getPassword()));
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        obj.setRole(obj.getRole());
        System.out.println(obj.getRole());;
        System.out.println(obj);
        userDao.save(obj);
    }
}
