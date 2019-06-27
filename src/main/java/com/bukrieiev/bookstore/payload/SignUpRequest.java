package com.bukrieiev.bookstore.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpRequest {

    @NotBlank
    @Getter
    @Setter
    private String username;

    @NotBlank
    @Getter
    @Setter
    @Email
    private String email;

    @NotBlank
    @Getter
    @Setter
    private String password;

    @NotBlank
    @Getter
    @Setter
    private String birthday;

    @NotBlank
    @Getter
    @Setter
    private String gender;
}
