package com.example.archem_prac4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Модель пользователя.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    @Transient
    private String passwordConfirm;

    private String emails;//add more emails? later

    private String e_secret;

}
