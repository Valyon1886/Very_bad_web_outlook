package com.example.archem_prac4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.archem_prac4.models.User;
import com.example.archem_prac4.repos.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setE_secret(bCryptPasswordEncoder.encode(user.getE_secret()));
        log.info("Save new user - " + user.getUsername());
        userRepo.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        log.info("Find user by username - " + username);
        return userRepo.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long Id){
        return userRepo.findById(Id);
    }

    @Transactional(readOnly = true)
    public List<User> getUserList(){
        return userRepo.findAll();
    }



    @Transactional(readOnly = true)
    public User getUser() {
        String username = "";
        try {
            var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        } catch (NullPointerException e) {
            username = "test";
        }
        log.info("Get user by username - " + username);
        return userRepo.findByUsername(username);
    }

}
