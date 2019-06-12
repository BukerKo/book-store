package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.UserInformation;
import org.springframework.data.repository.CrudRepository;

public interface UserInformationRepository extends CrudRepository<UserInformation, Long> {
}
