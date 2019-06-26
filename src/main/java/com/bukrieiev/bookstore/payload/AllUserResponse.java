package com.bukrieiev.bookstore.payload;

import com.bukrieiev.bookstore.entity.Gender;
import com.bukrieiev.bookstore.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public class AllUserResponse {
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
    private RoleName role;

    @Getter
    @Setter
    @NonNull
    private Gender gender;

    @Getter
    @Setter
    @NonNull
    private String email;
}
