package com.stackroute.booking.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.stackroute.booking.model.BookingDTORecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailsService {

    @Autowired
    private KafkaTemplate<String, BookingDTORecommendation> kafkaTemplate1;

    private static String TOPIC1= "BookingRecommend";

    public String recommend(BookingDTORecommendation bookingDTORecommendation) throws JsonProcessingException {
        BookingDTORecommendation bookingDTORecommendation1=new BookingDTORecommendation();
        bookingDTORecommendation1.setFarmerEmail(bookingDTORecommendation.getFarmerEmail());
        bookingDTORecommendation1.setConsumerEmail(bookingDTORecommendation.getConsumerEmail());
        System.out.println(bookingDTORecommendation1);
        kafkaTemplate1.send(TOPIC1, bookingDTORecommendation1);
        return "published to recommend";
    }

}
