package com.stackroute.authenticationservice.repository;


import com.stackroute.authenticationservice.models.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {

    @Query("SELECT l FROM Login l WHERE email = :email")
    Login findByEmail(@Param("email") String email);

}
