package com.stackroute.farmrevol.repositories;

import com.stackroute.farmrevol.models.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {

    @Query("SELECT l FROM Login l WHERE email = :email")
    Login findByEmail(@Param("email") String email);

    /*@Query("SELECT l.role FROM Login l WHERE l.email= :email")
    String fingRoleByEmail(@Param("email") String email);*/
}
