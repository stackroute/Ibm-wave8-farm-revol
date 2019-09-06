package com.stackroute.farmrevol.controllers;

import com.stackroute.farmrevol.configs.JwtTokenProvider;
import com.stackroute.farmrevol.exception.UserNotFoundException;
import com.stackroute.farmrevol.models.*;
import com.stackroute.farmrevol.repositories.FarmerRepository;
import com.stackroute.farmrevol.services.FarmerDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    private static String TOPIC = "kafkalogin";


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
    @GetMapping("/register/{email}")
    public ResponseEntity<?> getFarmerById(@PathVariable String email) throws UserNotFoundException {
        System.out.println(email);
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<>(farmerService.getFarmerByEmail(email), HttpStatus.CREATED);
        return responseEntity;
    }

    @DeleteMapping("register")
    public ResponseEntity<?> deleteFarmer(@RequestParam("email") String email) {
        farmerService.deleteFarmer(email);
        return new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateFarmer(@RequestBody Farmer farmer) {
        farmerService.updateFarmer(farmer);
        return new ResponseEntity<Farmer>(farmer, HttpStatus.OK);
    }

    @GetMapping("/register/land")
    public ResponseEntity<?> getLandById(@RequestParam("email") String email){
            System.out.println(farmerService.getAllLandsOfFarmerByEmail(email));
        return new ResponseEntity<>(farmerService.getAllLandsOfFarmerByEmail(email),HttpStatus.OK);
    }

    @PutMapping("land-details/{email}")
    public ResponseEntity<Farmer> uploadLandDetails(@RequestBody Land land,@PathVariable String email){
        System.out.println(land);
        return new ResponseEntity<Farmer>(farmerService.uploadLandDetails(land,email),HttpStatus.OK);
    }

   @DeleteMapping("/land/delete/{email}/{lid}")
    public ResponseEntity<?> deleteLandDetails(@PathVariable String email, @PathVariable long lid) {
        return new ResponseEntity<>(farmerService.deleteSpecificLandByEmail(email,lid),HttpStatus.OK);
    }
    @GetMapping("/land")
    public ResponseEntity<?> getAllLandsOfAllFarmers(){
        return new ResponseEntity(farmerService.getAllLandDetailsOfAllFarmers(),HttpStatus.OK);
    }
    @GetMapping("/farmers")
    public ResponseEntity<?> getAllFarmers(){
        return new ResponseEntity<>(farmerService.getAllFarmers(),HttpStatus.OK);
    }
    @GetMapping("/land/farmer/{email}/{lid}")
    public  ResponseEntity<?>  getLandByIdOfland(@PathVariable String email,@PathVariable long lid){
        return new ResponseEntity<>(farmerService.getSpecificLandOfFarmerByEmail(email,lid),HttpStatus.OK);
    }

    @PutMapping("land/update/{email}/{lid}")
public ResponseEntity<Farmer> updateLandById(@RequestBody Land land,@PathVariable String email,@PathVariable long lid){
        return new ResponseEntity<Farmer>(farmerService.updateLandDetailsByFarmerId(land,email,lid),HttpStatus.OK);
    }

}
