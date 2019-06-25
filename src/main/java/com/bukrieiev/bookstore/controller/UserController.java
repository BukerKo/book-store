package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.entity.RoleName;
import com.bukrieiev.bookstore.entity.User;
import com.bukrieiev.bookstore.payload.UserSummary;
import com.bukrieiev.bookstore.security.CurrentUser;
import com.bukrieiev.bookstore.security.UserPrincipal;
import com.bukrieiev.bookstore.service.UserService;
import com.bukrieiev.bookstore.util.ApiUtil;
import javafx.application.Application;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {

        return new UserSummary(currentUser.getId(),
                currentUser.getUsername(),
                ApiUtil.getRoleFromAuthorities(currentUser.getAuthorities()));
    }

    @PostMapping(value = "/user/update", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
