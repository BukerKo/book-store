package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.UserInformationRepository;
import com.bukrieiev.bookstore.dao.UserRepository;
import com.bukrieiev.bookstore.entity.User;
import com.bukrieiev.bookstore.entity.UserInformation;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    UserInformationRepository userInformationRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }


    public User updateUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).orElse(null);
        Hibernate.initialize(Objects.requireNonNull(userFromDb).getUserInformation());
        UserInformation userInformationFromDb = userFromDb.getUserInformation();

        UserInformation userInformation = user.getUserInformation();
        userInformationFromDb.setBirthday(userInformation.getBirthday());
        userInformationFromDb.setGender(userInformation.getGender());
        UserInformation userInformationResult =  userInformationRepository.save(userInformationFromDb);

        userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userFromDb.setEmail(user.getEmail());
        userFromDb.setUserInformation(userInformationFromDb);

        User userResult = userRepository.save(userFromDb);
        userResult.setUserInformation(userInformationResult);

        return userRepository.save(userResult);
    }

    public User getUserWithLoadedUserInf(Long id) {
        return userRepository.findByIdAndFetchUserInfEagerly(id);
    }

    public User persist(User user) {
        userInformationRepository.save(user.getUserInformation());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteByIdIn(List<Long> ids) {
        userRepository.deleteByIdIn(ids);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
