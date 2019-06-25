package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.UserInformationRepository;
import com.bukrieiev.bookstore.dao.UserRepository;
import com.bukrieiev.bookstore.entity.User;
import com.bukrieiev.bookstore.entity.UserInformation;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    UserInformationRepository userInformationRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    public User updateUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).get();
        Hibernate.initialize(userFromDb.getUserInformation());
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
        User user = userRepository.findByIdAndFetchUserInfEagerly(id);
        return user;
    }

    public User getUser(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.orElse(null);
    }

    public User persist(User user) {
        userInformationRepository.save(user.getUserInformation());
        return userRepository.save(user);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
