package com.stackroute.farmerprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class FarmerProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmerProfileServiceApplication.class, args);
	}

}
