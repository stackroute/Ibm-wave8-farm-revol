package com.stackroute.loginservice.dao;

import com.stackroute.loginservice.model.DAOUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<DAOUser, String> {

    @Query("SELECT l FROM DAOUser l WHERE l.email = :email")
    DAOUser findByUsername(@Param("email") String email);

}
