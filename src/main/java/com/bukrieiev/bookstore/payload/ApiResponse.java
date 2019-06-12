package com.bukrieiev.bookstore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ApiResponse {

    @Getter
    @Setter
    private Boolean success;

    @Getter
    @Setter
    private String message;

}
