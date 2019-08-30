package com.djamware.springangularauth.services;

import com.djamware.springangularauth.exception.UserNotFoundException;
import com.djamware.springangularauth.models.*;
import com.djamware.springangularauth.repositories.FarmerRepository;
import com.djamware.springangularauth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
   /* @Autowired
    private KafkaTemplate<String,Farmer> kafkaTemplate;*/


    public Farmer findFarmerByEmail(String email) {
        return farmerRepository.findByEmail(email);
    }

    public void saveFarmer(Farmer farmer) {
        farmer.setPassword(bCryptPasswordEncoder.encode(farmer.getPassword()));
        farmer.setEnabled(true);
        System.out.println(sequenceGenerator.getNextSequence(Farmer.SEQUENCE_NAME));
        farmer.setId(sequenceGenerator.getNextSequence(Farmer.SEQUENCE_NAME));
        for (int i = 0; i < farmer.getLand().size(); i++) {
            farmer.getLand().get(i).setId(sequenceGenerator.getNextSequence((Land.SEQUENCE_NAME)));
            farmer.getLand().get(i).setFarmerId(farmer.getId());
            System.out.println("hello" + farmer.getLand().get(i).getFarmerId());
        }
     /*   farmer.setId(sequenceGenerator.generateSequence(Farmer.SEQUENCE_NAME));
        System.out.println(farmer.getId());*/
        System.out.println(farmer.getId());
//        Role userRole = roleRepository.findByRole("farmer");
//        farmer.setRoles(new HashSet<>(Arrays.asList(userRole)));
//        ArrayList<Land> lands=new ArrayList<>();
//        farmer.setLand(lands);
        farmerRepository.save(farmer);
        //this.kafkaTemplate.send(TOPIC,farmer);
    }

    public Farmer deleteFarmer(Long Id) {
        Farmer farmer = getFarmerById(Id);
        farmerRepository.deleteById(Id);
        return farmer;
    }


    public Farmer getFarmerById(Long Id) throws UserNotFoundException {
        System.out.println(Id);
        if (!farmerRepository.findById(Id).isPresent()) {
            throw new UserNotFoundException();
        }
        return farmerRepository.findById(Id).get();
    }


    public Farmer updateFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    //
    public Farmer updateLandDetails(Land land, Long Id) {
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
        Optional optional = farmerRepository.findById(Id);
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

    public List<Land> getLandById(Long Id) {
        List<Land> lands = new ArrayList<>();
        System.out.println(farmerRepository.existsById(Id));
        System.out.println(farmerRepository.findById(Id));
        Optional optional = farmerRepository.findById(Id);
        if (optional.isPresent()) {
            Farmer farmer = (Farmer) optional.get();
            lands = farmer.getLand();
        }
        return lands;
    }

    public Land getLandByIdOfLand(Long id, Long lid) {
        Optional optional = farmerRepository.findById(id);
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

    public Farmer deleteLandById(Long id, Long Lid) {
        Optional optional = farmerRepository.findById(id);
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

    public List<Land> getAllLandDetails() {
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

    public Farmer updateLandDetailsById(Land land, Long fid, Long lid) {
        Optional optional = farmerRepository.findById(fid);
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
