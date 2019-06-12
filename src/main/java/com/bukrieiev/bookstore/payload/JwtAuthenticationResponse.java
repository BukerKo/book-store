package com.bukrieiev.bookstore.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class JwtAuthenticationResponse {

    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
