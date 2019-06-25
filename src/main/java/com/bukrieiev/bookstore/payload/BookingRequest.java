package com.bukrieiev.bookstore.payload;


import com.bukrieiev.bookstore.dto.BookIdToCount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class BookingRequest {

    @Getter
    @Setter
    @NotBlank
    private String totalPrice;

    @Getter
    @Setter
    @NotBlank
    private List<BookIdToCount> books;



}
