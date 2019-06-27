package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.dao.RoleRepository;
import com.bukrieiev.bookstore.dto.MainUserInformation;
import com.bukrieiev.bookstore.entity.Role;
import com.bukrieiev.bookstore.entity.User;
import com.bukrieiev.bookstore.entity.UserInformation;
import com.bukrieiev.bookstore.payload.AllUsersResponse;
import com.bukrieiev.bookstore.payload.UserSummary;
import com.bukrieiev.bookstore.security.CurrentUser;
import com.bukrieiev.bookstore.security.UserPrincipal;
import com.bukrieiev.bookstore.service.UserService;
import com.bukrieiev.bookstore.util.ApiUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(@NotNull final Pageable page) {
        Page<User> userPage = userService.findAll(page);
        List<User> users = userPage.getContent();
        Long totalUsers = userPage.getTotalElements();
        int totalPages = userPage.getTotalPages();
        List<MainUserInformation> response = users.stream()
                .map(user -> new MainUserInformation(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getUserInformation().getGender(),
                        ((Role) user.getRoles().toArray()[0]).getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new AllUsersResponse(response, totalUsers, totalPages));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateMainUserInformation(@PathVariable("id") Long id, @RequestBody MainUserInformation mainUserInformation) {
        User userFromDb = userService.getUserWithLoadedUserInf(id);
        UserInformation userInformationFromDb = userFromDb.getUserInformation();
        userInformationFromDb.setGender(mainUserInformation.getGender());

        userFromDb.setEmail(mainUserInformation.getEmail());
        userFromDb.setUsername(mainUserInformation.getUsername());
        userFromDb.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName(mainUserInformation.getRole()).orElse(null))));

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

    @PostMapping(value = "/user/delete")
    public ResponseEntity<?> deleteUser(@RequestBody List<Long> ids) {
        userService.deleteByIdIn(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
