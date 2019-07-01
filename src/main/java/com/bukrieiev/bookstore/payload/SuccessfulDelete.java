package com.bukrieiev.bookstore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SuccessfulDelete {

    @Getter
    @Setter
    private String response;

    public SuccessfulDelete() {
        this.response = "Successfully deleted.";
    }
}
