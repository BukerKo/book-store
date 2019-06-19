package com.bukrieiev.bookstore.payload;


import com.bukrieiev.bookstore.entity.Book;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class BookingRequest {

    @Getter
    @Setter
    @NotBlank
    private String totalPrice;

    @Getter
    @Setter
    @NotBlank
    private Set<Book> books;
}
