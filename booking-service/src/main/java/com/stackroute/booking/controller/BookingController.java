package com.stackroute.booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.booking.model.BookingDTORecommendation;
import com.stackroute.booking.service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/booking")
public class BookingController {


    @Autowired
    BookingDetailsService bookingDetailsService;
    @PostMapping("/recommend")
    public String recommend(@RequestBody BookingDTORecommendation bookingDTORecommendation) throws JsonProcessingException {

        bookingDetailsService.recommend(bookingDTORecommendation);
        return "Published";
    }

}
