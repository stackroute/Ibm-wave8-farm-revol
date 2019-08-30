package com.stackroute.farmrevol.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.stackroute.farmrevol.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}