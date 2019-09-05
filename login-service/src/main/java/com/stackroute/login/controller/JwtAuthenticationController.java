package com.stackroute.login.controller;

import com.stackroute.login.config.JwtTokenProvider;
import com.stackroute.login.model.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.login.model.UserDTO;
import com.stackroute.login.service.JwtUserDetailsService;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userDTO) throws Exception {
        authenticate(userDTO.getUsername(), userDTO.getPassword());
        System.out.println("hello");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
        DAOUser daoUser = userDetailsService.getUserData(userDTO.getUsername());
        final String token = jwtTokenUtil.createToken(userDTO.getUsername());
        System.out.println(token);
        Map<Object,Object> model=new HashMap<>();
        model.put("role",daoUser.getRole());
        model.put("token",token);
        return ok(null);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            System.out.println(new UsernamePasswordAuthenticationToken(username, password));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(authenticationManager);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}


