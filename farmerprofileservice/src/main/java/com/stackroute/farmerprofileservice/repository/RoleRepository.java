package com.stackroute.farmerprofileservice.repository;

import com.stackroute.farmerprofileservice.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}