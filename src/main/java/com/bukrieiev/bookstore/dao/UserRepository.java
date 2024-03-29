package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT p FROM User p JOIN FETCH p.userInformation WHERE p.id = (:id)")
    User findByIdAndFetchUserInfEagerly(@Param("id") Long id);

    void deleteByIdIn(List<Long> ids);

}
