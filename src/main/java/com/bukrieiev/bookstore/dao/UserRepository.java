package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT p FROM User p JOIN FETCH p.userInformation WHERE p.id = (:id)")
    public User findByIdAndFetchUserInfEagerly(@Param("id") Long id);
}
