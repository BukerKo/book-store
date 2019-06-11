package com.bukrieiev.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.rmi.server.UID;

@Entity
@Table(name="user_information")
public class UserInformation {

    @Id
    @Column(name = "id", nullable = false)
    private UID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    private String gender;

}
