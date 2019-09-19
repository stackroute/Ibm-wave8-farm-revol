package com.stackroute.recommendation.service;

import com.stackroute.recommendation.model.Farmer;
import com.stackroute.recommendation.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    @Autowired
    FarmerRepository farmerRepository;

    public List<Farmer> recommend(String email) {
       List<Farmer> farmers = farmerRepository.recommend(email);
       return  farmers;
    }
}
