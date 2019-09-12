package com.stackroute.farmerprofileservice.service;

import com.stackroute.farmerprofileservice.exception.UserNotFoundException;
import com.stackroute.farmerprofileservice.models.Farmer;
import com.stackroute.farmerprofileservice.models.Land;
import com.stackroute.farmerprofileservice.repository.FarmerRepository;
import com.stackroute.farmerprofileservice.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FarmerDetailsService {

    // private static final String TOPIC = "users";
    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private com.stackroute.farmerprofileservice.service.SequenceGeneratorService sequenceGenerator;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private KafkaTemplate<String, Farmer> kafkaTemplateFarmer;

    private static String TOPIC2 = "testing";



    public Farmer findFarmerByEmail(String email) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Farmer farmer= (Farmer) mongoTemplate.findOne(query,Farmer.class);

        return farmer;
    }

    public void saveFarmer(Farmer farmer) {
        farmer.setPassword(farmer.getPassword());
        farmer.setEnabled(true);
        farmer.setLand(new ArrayList<>());

//        for (int i = 0; i < farmer.getLand().size(); i++) {
//            farmer.getLand().get(i).setId(sequenceGenerator.getNextSequence((Land.SEQUENCE_NAME)));
//
//            System.out.println("hello" + farmer.getLand().get(i).getId());
//        }

        farmerRepository.save(farmer);
    }

    public Farmer deleteFarmer(String email) {
        Farmer farmer = findFarmerByEmail(email);
        farmerRepository.deleteById(email);
        return farmer;
    }


    public Farmer getFarmerByEmail(String email) throws UserNotFoundException {
        if (!farmerRepository.findById(email).isPresent()) {
            throw new UserNotFoundException();
        }
        return farmerRepository.findById(email).get();
    }

    //
    public Farmer uploadLandDetails(Land land, String email) {
        System.out.println(email);
        System.out.println("land"+land);
        Optional optional = farmerRepository.findById(email);
        Farmer farmer = (Farmer) optional.get();
        System.out.println(farmer);
        ArrayList<Land> landList = farmer.getLand();
        try {
            land.setId(sequenceGenerator.getNextSequence(Land.SEQUENCE_NAME));
            land.setFarmerId(email);
            landList.add(land);
            farmer.setLand(landList);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        farmerRepository.save(farmer);
        return farmer;
    }

    public List<Land> getAllLandsOfFarmerByEmail(String email) {
        List<Land> lands = new ArrayList<>();
        System.out.println(farmerRepository.existsById(email));
        System.out.println(farmerRepository.findById(email));
        Optional optional = farmerRepository.findById(email);
        if (optional.isPresent()) {
            Farmer farmer = (Farmer) optional.get();
            lands = farmer.getLand();
        }
        return lands;
    }

    public Land getSpecificLandOfFarmerByEmail(String email, Long lid) {
        Optional optional = farmerRepository.findById(email);
        Farmer farmer = (Farmer) optional.get();
        ArrayList<Land> lands = farmer.getLand();
        final Land[] requiredLand = new Land[1];
        int i=0;

        lands.forEach(land ->
        {
            System.out.println("Land is " + land.getId());
            System.out.println("Lid is " + lid);
            if (land.getId().equals(lid)) {
                System.out.println("Lid is " + lid);
                // System.out.println("Land is " + lands.get(i));
                requiredLand[0] = land;
                // return;
            }

        });


        return requiredLand[0];

    }



    public Farmer deleteSpecificLandByEmail(String email, Long Lid) {
        Optional optional = farmerRepository.findById(email);
        Farmer farmer = (Farmer) optional.get();
        ArrayList<Land> lands = farmer.getLand();
        int i;
        for (i = 0; i < lands.size(); i++) {
            if (lands.get(i).getId().equals(Lid)) {
                lands.remove(i);
            }
        }
        farmer.setLand(lands);

        farmerRepository.save(farmer);
        return farmer;

    }

    public List<Land> getAllLandDetailsOfAllFarmers() {
        List<Land> lands = new ArrayList<>();
        List<Farmer> optional = farmerRepository.findAll();
        for (int i = 0; i < optional.size(); i++) {
            Farmer farmer = (Farmer) optional.get(i);
            lands = farmer.getLand();
        }
        return lands;
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public Farmer updateLandDetailsByFarmerId(Land land, String email, Long lid) {
        Optional optional = farmerRepository.findById(email);
        Farmer farmer = (Farmer) optional.get();
        ArrayList<Land> lands = farmer.getLand();
        int i;
        System.out.println(lands);
        for (i = 0; i < lands.size(); i++) {
            if (lands.get(i).getId().equals(lid)) {
                land.setId(lands.get(i).getId());
                land.setFarmerId(email);
                lands.set(i, land);
                break;
            }
        }
        farmer.setLand(lands);
        System.out.println(farmer);
        return farmerRepository.save(farmer);
    }




    public Farmer updateFarmer(Farmer farmer) {

        return farmerRepository.save(farmer);
    }
}
