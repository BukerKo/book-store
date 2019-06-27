package com.bukrieiev.bookstore.payload;


import com.bukrieiev.bookstore.dto.BookFromBookingRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class BookingRequest {

    @Getter
    @Setter
    @NotBlank
    private List<BookFromBookingRequest> books;



}
