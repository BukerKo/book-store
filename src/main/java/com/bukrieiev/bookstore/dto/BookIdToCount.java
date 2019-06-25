package com.bukrieiev.bookstore.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class BookIdToCount {

    @Getter
    @Setter
    @NonNull
    private Long id;

    @Getter
    @Setter
    @NonNull
    private Long count;
}
