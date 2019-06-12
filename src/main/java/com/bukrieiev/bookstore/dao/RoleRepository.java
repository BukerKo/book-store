package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.Role;
import com.bukrieiev.bookstore.entity.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
