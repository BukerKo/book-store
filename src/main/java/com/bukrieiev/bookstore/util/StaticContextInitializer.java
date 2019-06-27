package com.bukrieiev.bookstore.util;

import com.bukrieiev.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class StaticContextInitializer {

    private UserService userService;

    @PostConstruct
    public void init() {
        ApiUtil.setUserService(userService);
    }
}
