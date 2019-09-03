package com.stackroute.consumerprofileservice.repository;

import com.stackroute.consumerprofileservice.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}