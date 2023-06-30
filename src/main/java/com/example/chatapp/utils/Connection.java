package com.example.chatapp.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
    private static Connection instance;
    private EntityManagerFactory emf;

    public Connection() {emf = Persistence.createEntityManagerFactory("appchat");}

    public static Connection getInstance() {
        if(null==instance) instance = new Connection();

        return instance;
    }

    public EntityManagerFactory getEmf() {return emf;}
}
