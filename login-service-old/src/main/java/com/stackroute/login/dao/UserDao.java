package com.stackroute.login.dao;
import com.stackroute.login.model.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.login.model.DAOUser;
@Repository
public interface UserDao extends CrudRepository<DAOUser, String> {

    @Query("SELECT l FROM daouser l WHERE l.username = :username")
    DAOUser findByUsername(@Param("username") String username);

}
