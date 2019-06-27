package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.dao.RoleRepository;
import com.bukrieiev.bookstore.entity.*;
import com.bukrieiev.bookstore.exception.AppException;
import com.bukrieiev.bookstore.payload.ApiResponse;
import com.bukrieiev.bookstore.payload.JwtAuthenticationResponse;
import com.bukrieiev.bookstore.payload.LoginRequest;
import com.bukrieiev.bookstore.payload.SignUpRequest;
import com.bukrieiev.bookstore.security.JwtTokenProvider;
import com.bukrieiev.bookstore.service.UserService;
import com.bukrieiev.bookstore.util.ApiUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    UserService userService;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, ApiUtil.getRoleFromAuthorities(authentication.getAuthorities())));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Email Address already in use!"));
        }

        // Creating user's account
        UserInformation userInformation = new UserInformation(signUpRequest.getBirthday(), Gender.valueOf(signUpRequest.getGender()));
        User user = new User(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getUsername(), userInformation);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));


        user.setRoles(Collections.singleton(userRole));

        User result = userService.persist(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
