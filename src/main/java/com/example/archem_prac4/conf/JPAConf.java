package com.example.archem_prac4.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "com.example.archem_prac4.repos")
public class JPAConf {
}
