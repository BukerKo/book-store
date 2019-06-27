package com.bukrieiev.bookstore.payload;

import com.bukrieiev.bookstore.entity.RoleName;
import lombok.Getter;
import lombok.Setter;

public class JwtAuthenticationResponse {

    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    private RoleName role;

    @Getter
    @Setter
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken, RoleName role) {
        this.accessToken = accessToken;
        this.role = role;
    }

}
