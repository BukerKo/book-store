package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.UserInformationRepository;
import com.bukrieiev.bookstore.dao.UserRepository;
import com.bukrieiev.bookstore.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    UserInformationRepository userInformationRepository;
    UserRepository userRepository;

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
