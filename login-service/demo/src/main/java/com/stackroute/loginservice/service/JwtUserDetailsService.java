package com.stackroute.loginservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stackroute.loginservice.dao.UserDao;
import com.stackroute.loginservice.model.DAOUser;
import com.stackroute.loginservice.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class JwtUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    private UserDTO userDTO;

    /*  public JwtUserDetailsService(UserDao userDao) {this.userDao=userDao;}*/

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("mrudula".equals(username)) {
//            System.out.println("Hi inside loaduserbyusername");
//            return new User("mrudula", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      /*  System.out.println("inside loadUserByUsername ");
        DAOUser user = userDao.findByUsername(email);
        System.out.println("user data is "+user);*/
       // if(user != null) {
            // get email from the loaded user
           // return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
            // get password from the loaded user
            // return them
           /*List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
            System.out.println("authorities "+authorities);
            return buildUserForAuthentication(user, authorities);*/


        DAOUser user = userDao.findByUsername(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }

        } /*else {
            throw new UsernameNotFoundException("username not found");
        }*/


    private UserDetails buildUserForAuthentication(DAOUser user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }


    private List<GrantedAuthority> getUserAuthority(String userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles));

        return new ArrayList<>(roles);
    }

    public DAOUser getUserData(String email) {
        DAOUser daoUser = userDao.findByUsername(email);
        return daoUser;
    }

    public DAOUser save(DAOUser userdao) {
        DAOUser newUser = new DAOUser();
        newUser.setEmail(userdao.getEmail());
        newUser.setPassword(bcryptEncoder.encode(userdao.getPassword()));
        newUser.setRole(userdao.getRole());
        System.out.println(newUser);
        return userDao.save(newUser);
    }
    public String forgotPassword(String username) throws MessagingException {
        String status = "Failed";
        System.out.println(username);
        System.out.println(userDao.findByUsername(username));
        System.out.println("abcd");
        if (userDao.findByUsername(username) != null) {
            System.out.println(username);
            System.out.println("efgh");
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(username);
            helper.setSubject("Link for Reset your Password");
            helper.setText("http://172.23.238.164:4200/resetPassword");
            javaMailSender.send(message);
            System.out.println("hello");
            status = "Sent";
        }
        else {

        }
        return status;
    }
    public DAOUser update(UserDTO userDTO) throws Exception {
        DAOUser user = userDao.findByUsername(userDTO.getEmail());
        if (user != null) {
            user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        }
        System.out.println(user);
        return userDao.save(user);
    }

}
