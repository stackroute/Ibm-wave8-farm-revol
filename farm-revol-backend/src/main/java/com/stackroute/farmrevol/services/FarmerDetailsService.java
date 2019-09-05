package com.stackroute.farmrevol.services;

import com.stackroute.farmrevol.exception.UserNotFoundException;
import com.stackroute.farmrevol.models.*;
import com.stackroute.farmrevol.repositories.FarmerRepository;
import com.stackroute.farmrevol.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    private MongoTemplate mongoTemplate;
   /* @Autowired
    private KafkaTemplate<String,Farmer> kafkaTemplate;*/


    public Farmer findFarmerByEmail(String email) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Farmer farmer= (Farmer) mongoTemplate.findOne(query,Farmer.class);

        return farmer;
    }

    public void saveFarmer(Farmer farmer) {
        farmer.setPassword(bCryptPasswordEncoder.encode(farmer.getPassword()));
        farmer.setEnabled(true);
        farmer.setLand(new ArrayList<>());
       // System.out.println(sequenceGenerator.getNextSequence(Farmer.SEQUENCE_NAME));
       // farmer.setId(sequenceGenerator.getNextSequence(Farmer.SEQUENCE_NAME));
        for (int i = 0; i < farmer.getLand().size(); i++) {
            farmer.getLand().get(i).setId(sequenceGenerator.getNextSequence((Land.SEQUENCE_NAME)));
            System.out.println("hello" + farmer.getLand().get(i).getId());
        }
     /*   farmer.setId(sequenceGenerator.generateSequence(Farmer.SEQUENCE_NAME));
        System.out.println(farmer.getId());*//*
        System.out.println(farmer.getId());*/
//        Role userRole = roleRepository.findByRole("farmer");
//        farmer.setRoles(new HashSet<>(Arrays.asList(userRole)));
//        ArrayList<Land> lands=new ArrayList<>();
//        farmer.setLand(lands);
        farmerRepository.save(farmer);
        //this.kafkaTemplate.send(TOPIC,farmer);
    }

    public Farmer deleteFarmer(String email) {
        Farmer farmer = findFarmerByEmail(email);
        farmerRepository.deleteById(email);
        return farmer;
    }

    public Farmer getFarmerByEmail(String email) throws UserNotFoundException {
        System.out.println(email);
        if (!farmerRepository.findById(email).isPresent()) {
            throw new UserNotFoundException();
        }
        return farmerRepository.findById(email).get();
    }


    public Farmer updateFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    //
    public Farmer uploadLandDetails(Land land, String email) {
       /* System.out.println(Id);
        System.out.println(land);
        Optional optional = farmerRepository.findById(Id);
        Farmer farmer = (Farmer) optional.get();
        ArrayList<Land> lands = farmer.getLand();
        for (int i = 0; i < lands.size(); i++) {
            farmer.getLand().get(i).setId(sequenceGenerator.getNextSequence((Land.SEQUENCE_NAME)));
            System.out.println(farmer.getLand().get(i).getId());
            if (lands.get(i).getId() == land.getId()) {

                System.out.println(lands);
                lands.set(i, land);
                break;
            }*/
        Optional optional = farmerRepository.findById(email);
        Farmer farmer = (Farmer) optional.get();
        List<Land> landList = farmer.getLand();
        landList.add(land);
        for(int i=0;i<landList.size();i++) {
            farmer.getLand().get(i).setId(sequenceGenerator.getNextSequence(Land.SEQUENCE_NAME));
        }
        farmerRepository.save(farmer);
        return farmer;
        }
//        farmer.setLand(lands);
//        System.out.println(farmer);
//        return farmerRepository.save(farmer);
//    }

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
        int i;
        for (i = 0; i < lands.size(); i++) {
            if (lands.get(i).getId() == lid) {
                System.out.println(lands.get(i));
                break;
            }
        }
        return lands.get(i);
    }


//    public List<Land> deleteLandById(Long id, Long Lid) {
//        Optional optional = farmerRepository.findById(id);
//        Farmer farmer = (Farmer) optional.get();
//        ArrayList<Land> lands = farmer.getLand();
//        int i;
//        for (i = 0; i < lands.size(); i++) {
//            if (lands.get(i).getId() == Lid) {
//                lands.remove(i);
//            }
//        }
//       // farmer.setLand(lands);
//
//        return lands;
//
//    }

    public Farmer deleteSpecificLandByEmail(String email, Long Lid) {
        Optional optional = farmerRepository.findById(email);
        Farmer farmer = (Farmer) optional.get();
        ArrayList<Land> lands = farmer.getLand();
        int i;
        for (i = 0; i < lands.size(); i++) {
            if (lands.get(i).getId() == Lid) {
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
        for (i = 0; i < lands.size(); i++) {
            if (lands.get(i).getId() == lid) {
                lands.set(i, land);
                System.out.println(lands.get(i).getId());
                break;
            }
        }
        farmer.setLand(lands);
        System.out.println(farmer);
        return farmerRepository.save(farmer);
    }

}
