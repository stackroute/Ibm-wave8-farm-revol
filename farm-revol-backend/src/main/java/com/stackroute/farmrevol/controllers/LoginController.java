package com.stackroute.farmrevol.controllers;

import com.stackroute.farmrevol.configs.JwtTokenProvider;
import com.stackroute.farmrevol.models.Login;
import com.stackroute.farmrevol.repositories.LoginRepository;
import com.stackroute.farmrevol.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    LoginRepository loginRepository;



    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Login data) {
        try {
            System.out.println(data);

            String username = data.getEmail();
           /* String role=loginRepository.fingRoleByEmail(username);
            System.out.println(role);*/
            System.out.println(data.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username);
            System.out.println(token);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            System.out.println(model);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }
}
