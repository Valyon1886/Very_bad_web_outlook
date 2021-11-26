package com.example.archem_prac4.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // private User user;

    private String data;

    private String subject;

    private String from;

    private String content;

    public Message(long id, String data, String subject, String from, String content) {
        this.id = id;
        this.data = data;
        this.subject = subject;
        this.from = from;
        this.content = content;
    }

    public Message() {}


    //private String content; file??????
}
