package com.example.chatapp.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String user;
    @Column
    private String message;

    public Message(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public Message(Long id, String user, String message) {
        this.id = id;
        this.user = user;
        this.message = message;
    }

    public Message() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
