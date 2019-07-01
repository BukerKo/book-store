package com.bukrieiev.bookstore.dto;

import com.bukrieiev.bookstore.entity.Gender;
import com.bukrieiev.bookstore.entity.Role;
import com.bukrieiev.bookstore.entity.RoleName;
import com.bukrieiev.bookstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public class MainUserInformation {
    @Getter
    @Setter
    @NonNull
    private Long id;

    @Getter
    @Setter
    @NonNull
    private String username;

    @Getter
    @Setter
    @NonNull
    private String email;

    @Getter
    @Setter
    @NonNull
    private Gender gender;

    @Getter
    @Setter
    @NonNull
    private RoleName role;

    public MainUserInformation(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.gender = user.getUserInformation().getGender();
        this.role = (user.getRoles().stream().findFirst().orElse(new Role())).getName();

    }
}
