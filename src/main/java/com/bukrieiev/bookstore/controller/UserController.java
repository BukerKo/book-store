package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.entity.RoleName;
import com.bukrieiev.bookstore.payload.UserSummary;
import com.bukrieiev.bookstore.security.CurrentUser;
import com.bukrieiev.bookstore.security.UserPrincipal;
import com.bukrieiev.bookstore.service.UserService;
import com.bukrieiev.bookstore.util.ApiUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        UserSummary userSummary = new UserSummary(currentUser.getId(),
                currentUser.getUsername(),
                ApiUtil.getRoleFromAuthorities(currentUser.getAuthorities()));
        return userSummary;
    }

}
