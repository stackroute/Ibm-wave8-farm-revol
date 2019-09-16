package com.stackroute.farmerprofileservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.farmerprofileservice.exception.UserNotFoundException;
import com.stackroute.farmerprofileservice.models.*;
import com.stackroute.farmerprofileservice.repository.FarmerRepository;
import com.stackroute.farmerprofileservice.service.FarmerDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/farmer")
public class FarmerController {

//    @Autowired
//    AuthenticationManager authenticationManager;


    @Autowired
    FarmerRepository farmers;

    private final Logger logger = LoggerFactory.getLogger(FarmerController.class);

    @Autowired
    private FarmerDetailsService farmerService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String , CropDTO> kafkaTemplateCropDTO;

    private static String TOPIC = "kafka";


    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Farmer farmer) throws JsonProcessingException {

        farmerService.saveFarmer(farmer);
        FarmerDTO farmerDTO=new FarmerDTO();
        farmerDTO.setEmail(farmer.getEmail());
        farmerDTO.setPassword(farmer.getPassword());
        farmerDTO.setRole("farmer");
        System.out.println("Farmer DTO=" + farmerDTO);
        kafkaTemplate.send(TOPIC, new ObjectMapper().writeValueAsString(farmerDTO));
        farmerService.recommend(farmer);
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
    public ResponseEntity<Farmer> uploadLandDetails(@RequestBody Land land, @PathVariable String email){
      //  return new ResponseEntity<Farmer>(farmerService.uploadLandDetails(land,email),HttpStatus.OK);

	ResponseEntity responseEntity =  new ResponseEntity<Farmer>(farmerService.uploadLandDetails(land,email),HttpStatus.OK);
       List<String> listOfCrops = land.getCrops();
       int i;
       for(i = 0; i < listOfCrops.size(); i++) {
           System.out.println("start");
           System.out.println(listOfCrops.get(i));
           CropDTO cropDTO = new CropDTO();
           cropDTO.setCropName(listOfCrops.get(i));
           cropDTO.setFarms(land);
           System.out.println(cropDTO );
           System.out.println("Trying to send");
           kafkaTemplateCropDTO.send(TOPIC, cropDTO);
           System.out.println("sent");
       }
       return responseEntity;
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
    public  ResponseEntity<?>  getLandByIdOfLand(@PathVariable String email,@PathVariable long lid){
        return new ResponseEntity<>(farmerService.getSpecificLandOfFarmerByEmail(email,lid),HttpStatus.OK);
    }
    @GetMapping("/land/orders/{email}/{lid}")
    public  ResponseEntity<?> getAllLandOrdersOfAFarmer(@PathVariable String email,@PathVariable long lid){
        return new ResponseEntity<>(farmerService.getAllLandOrdersOfFarmerByEmail(email,lid),HttpStatus.OK);
    }

    @PutMapping("land/update/{email}/{lid}")
    public ResponseEntity<Farmer> updateLandById(@RequestBody Land land, @PathVariable String email, @PathVariable long lid){
        return new ResponseEntity<Farmer>(farmerService.updateLandDetailsByFarmerId(land,email,lid),HttpStatus.OK);
    }

    @GetMapping("/search/{email}/{landId}/{crop}")
    public String bookLand(@PathVariable("email") String email, @PathVariable Long landId ,@PathVariable("crop") String crop) {
        CropDTO cropDTO = new CropDTO();
        cropDTO.setCropName(crop);
        cropDTO.setFarms(farmerService.getSpecificLandOfFarmerByEmail(email, landId));
        kafkaTemplateCropDTO.send(TOPIC, cropDTO);

        return ("Uncles");
    }

}
