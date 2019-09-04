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



    public Farmer findFarmerByEmail(String email) {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Farmer farmer= (Farmer) mongoTemplate.findOne(query,Farmer.class);

        return farmer;
    }

    public void saveFarmer(Farmer farmer) {
        farmer.setPassword(farmer.getPassword());
        farmer.setEnabled(true);

        for (int i = 0; i < farmer.getLand().size(); i++) {
            farmer.getLand().get(i).setId(sequenceGenerator.getNextSequence((Land.SEQUENCE_NAME)));

            System.out.println("hello" + farmer.getLand().get(i).getId());
        }

        farmerRepository.save(farmer);
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
