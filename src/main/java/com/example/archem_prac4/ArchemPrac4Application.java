package com.example.archem_prac4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ArchemPrac4Application {
    public static void main(String[] args) {
        SpringApplication.run(ArchemPrac4Application.class, args);
    }

//    @Bean
//    public CommandLineRunner demoData(RoleRepo RoleRepo) {
//        if (RoleRepo.findAll().size() == 0) {
//            return args -> RoleRepo.saveAll(List.of(new Role(1L, "ROLE_ADMIN"),
//                    new Role(2L, "ROLE_USER")));
//        }
//        return args -> {
//        };
//    }
}


