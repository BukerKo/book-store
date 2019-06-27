package com.bukrieiev.bookstore.payload;


import com.bukrieiev.bookstore.dto.BookTitleToCount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BookingResponse {

    @Getter
    @Setter
    private List<BookTitleToCount> bookTitleToCounts;

    @Getter
    @Setter
    private Long price;

    @Getter
    @Setter
    private String date;

}
