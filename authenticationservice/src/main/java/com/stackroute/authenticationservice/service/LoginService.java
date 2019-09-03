package com.stackroute.authenticationservice.service;
import com.stackroute.authenticationservice.models.Login;
import com.stackroute.authenticationservice.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = loginRepository.findByEmail(email);
        if(login != null) {
            List<GrantedAuthority> authorities = getUserAuthority(login.getRole());
            return buildUserForAuthentication(login, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }


    private UserDetails buildUserForAuthentication(Login login, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(login.getEmail(), login.getPassword(), authorities);
    }


    private List<GrantedAuthority> getUserAuthority(String userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles));

        return new ArrayList<>(roles);
    }

}
