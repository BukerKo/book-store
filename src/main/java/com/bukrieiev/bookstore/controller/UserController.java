package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.dao.RoleRepository;
import com.bukrieiev.bookstore.dao.UserInformationRepository;
import com.bukrieiev.bookstore.dto.MainUserInformation;
import com.bukrieiev.bookstore.entity.Role;
import com.bukrieiev.bookstore.entity.RoleName;
import com.bukrieiev.bookstore.entity.User;
import com.bukrieiev.bookstore.entity.UserInformation;
import com.bukrieiev.bookstore.payload.AllUserResponse;
import com.bukrieiev.bookstore.payload.UserSummary;
import com.bukrieiev.bookstore.security.CurrentUser;
import com.bukrieiev.bookstore.security.UserPrincipal;
import com.bukrieiev.bookstore.service.UserService;
import com.bukrieiev.bookstore.util.ApiUtil;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private RoleRepository roleRepository;
    private UserInformationRepository userInformationRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(@NotNull final Pageable page) {
        List<User> users = userService.findAll(page);
        List<AllUserResponse> response = users.stream()
                .map(user -> new AllUserResponse(
                        user.getId(),
                        user.getUsername(),
                        ((Role) user.getRoles().toArray()[0]).getName(),
                        user.getUserInformation().getGender(),
                        user.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateMainUserInformation(@PathVariable("id") Long id, @RequestBody MainUserInformation mainUserInformation) {
        User userFromDb = userService.getUserWithLoadedUserInf(id);
        UserInformation userInformationFromDb = userFromDb.getUserInformation();
        userInformationFromDb.setGender(mainUserInformation.getGender());

        userFromDb.setEmail(mainUserInformation.getEmail());
        userFromDb.setUsername(mainUserInformation.getUsername());
        userFromDb.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName(mainUserInformation.getRole()).get())));

        return ResponseEntity.ok(userService.persist(userFromDb));

    }

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

    @DeleteMapping(value="/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
