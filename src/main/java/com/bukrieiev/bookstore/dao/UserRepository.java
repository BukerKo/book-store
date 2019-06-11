package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.User;
import org.springframework.data.repository.Repository;

import java.rmi.server.UID;

public interface UserRepository extends Repository<User, UID> {

}
