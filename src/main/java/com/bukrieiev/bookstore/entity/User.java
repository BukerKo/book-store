package com.bukrieiev.bookstore.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.rmi.server.UID;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private UID id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;
}
