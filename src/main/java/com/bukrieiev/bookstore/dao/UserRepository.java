package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.rmi.server.UID;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
