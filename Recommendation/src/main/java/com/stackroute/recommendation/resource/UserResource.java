package com.stackroute.recommendation.resource;

import com.stackroute.recommendation.model.Farmers;
import com.stackroute.recommendation.model.Land;
import com.stackroute.recommendation.model.RecommendedLandsFromConsumer;
import com.stackroute.recommendation.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/neo4j")
public class UserResource {

    @Autowired
    FarmerService farmerService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateLandsOfFarmer;

    private static String TOPIC5="fr-LandsOfFarmer";

    List<Land> lands=new ArrayList<>();

    @GetMapping("/recommend/{email}")
    public List<Land> bookLand(@PathVariable("email") String email) {
        System.out.println(email);
        Farmers farmer=new Farmers();
        System.out.println(farmerService.recommend(email));
        farmer.setFarmers(farmerService.recommend(email));
        System.out.println(farmer);
        int size=(farmer.getFarmers().size());
       System.out.println(size);
       lands.clear();
      for(int i=0;i<size;i++) {
         kafkaTemplateLandsOfFarmer.send(TOPIC5,((farmer.getFarmers()).get(i)).getEmail());
      }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lands);
        return  lands;
    }



    @KafkaListener(topics = "fr-RcommendedLandsToConsumer", groupId = "recommended_lands", containerFactory = "kafkaListenerContainerFactoryRecommendedLandsToConsumer")
    public void bookingJson(RecommendedLandsFromConsumer recommendedLandsFromConsumer) {

        RecommendedLandsFromConsumer recommendedLandsFromConsumer1=new RecommendedLandsFromConsumer();
        recommendedLandsFromConsumer1=recommendedLandsFromConsumer;
        int size=(recommendedLandsFromConsumer.getLand().size());
       for(int i=0;i<size;i++){
           if(recommendedLandsFromConsumer1.getLand().get(i).isAvailable()==true) {
               lands.add(recommendedLandsFromConsumer1.getLand().get(i));
           }
      }
    }
}
