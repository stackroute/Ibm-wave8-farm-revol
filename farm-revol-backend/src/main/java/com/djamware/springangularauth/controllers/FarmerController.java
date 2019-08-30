package com.djamware.springangularauth.controllers;

import com.djamware.springangularauth.configs.JwtTokenProvider;
import com.djamware.springangularauth.exception.UserNotFoundException;
import com.djamware.springangularauth.models.*;
import com.djamware.springangularauth.repositories.FarmerRepository;
import com.djamware.springangularauth.services.FarmerDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/farmer")
public class FarmerController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    FarmerRepository farmers;

    private final Logger logger = LoggerFactory.getLogger(FarmerController.class);

    @Autowired
    private FarmerDetailsService farmerService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static String TOPIC = "kafka";


   /* @PostMapping("/publish")
    public void getUserId(@RequestBody String farmer) {

        kafkaTemplate.send(TOPIC, farmer);
    }*/

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

//    @KafkaListener(topics = "kafka-producer", groupId="group_id")
//    public void consume(String message) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Farmer farmer = objectMapper.readValue(message, Farmer.class);
//        logger.info(String.format("$$ -> Consumed Message -> %s",farmer));
//    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Farmer farmer) throws JsonProcessingException {
//        Farmer farmerExists = farmerService.findFarmerByEmail(farmer.getEmail());
//        if (farmerExists != null) {
//            throw new BadCredentialsException("Farmer with username: " + farmer.getEmail() + " already exists");
//        }
        farmerService.saveFarmer(farmer);
        FarmerDTO farmerDTO=new FarmerDTO();
        farmerDTO.setEmail(farmer.getEmail());
        farmerDTO.setPassword(farmer.getPassword());
        farmerDTO.setRole("farmer");
        System.out.println("Farmer DTO=" + farmerDTO);
        kafkaTemplate.send(TOPIC, new ObjectMapper().writeValueAsString(farmerDTO));
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "Farmer registered successfully");
        return ok(model);
    }

//    @SuppressWarnings("rawtypes")
    @GetMapping("/register/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) throws UserNotFoundException {
        System.out.println(id);
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<>(farmerService.getFarmerById(id), HttpStatus.CREATED);
        return responseEntity;
    }

    @DeleteMapping("register")
    public ResponseEntity<?> deleteUser(@RequestParam("id") long id) {
        farmerService.deleteFarmer(id);
        return new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateUser(@RequestBody Farmer farmer) {
        farmerService.updateFarmer(farmer);
        return new ResponseEntity<Farmer>(farmer, HttpStatus.OK);
    }

    @GetMapping("/register/land")
    public ResponseEntity<?> getLandById(@RequestParam("id") long id){
            System.out.println(farmerService.getLandById(id));
        return new ResponseEntity<>(farmerService.getLandById(id),HttpStatus.OK);
    }

    @PutMapping("land-details/{id}")
    public ResponseEntity<Farmer> updateLandDetails(@RequestBody Land land,@PathVariable long id){
        System.out.println(land);
        return new ResponseEntity<Farmer>(farmerService.updateLandDetails(land,id),HttpStatus.OK);
    }

   @DeleteMapping("/land/delete/{id}/{lid}")
    public ResponseEntity<?> deleteLandDetails(@PathVariable long id, @PathVariable long lid) {
        return new ResponseEntity<>(farmerService.deleteLandById(id, lid),HttpStatus.OK);
    }
    @GetMapping("/land")
    public ResponseEntity<?> getAllLands(){
        return new ResponseEntity(farmerService.getAllLandDetails(),HttpStatus.OK);
    }
    @GetMapping("/farmers")
    public ResponseEntity<?> getAllFarmers(){
        return new ResponseEntity<>(farmerService.getAllFarmers(),HttpStatus.OK);
    }
    @GetMapping("/land/farmer/{id}/{lid}")
    public  ResponseEntity<?>  getLandByIdOfland(@PathVariable long id,@PathVariable long lid){
        return new ResponseEntity<>(farmerService.getLandByIdOfLand(id,lid),HttpStatus.OK);
    }

    @PutMapping("land/update/{id}/{lid}")
public ResponseEntity<Farmer> updateLandById(@RequestBody Land land,@PathVariable long id,@PathVariable long lid){
        return new ResponseEntity<Farmer>(farmerService.updateLandDetailsById(land,id,lid),HttpStatus.OK);
    }

}
