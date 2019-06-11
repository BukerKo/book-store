package com.bukrieiev.bookstore.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public interface AuthenticationController {

    @GetMapping(value = "/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getRole(@RequestParam("email") String email, @RequestParam("password") String password);

}
