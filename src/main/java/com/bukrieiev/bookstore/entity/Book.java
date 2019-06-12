package com.bukrieiev.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "book")
public class Book {

    private static final String SAMPLE_BOOK_PHOTO_URL = "http://clipart-library.com/images/8TGbezyAc.jpg";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Getter
    @Setter
    @Column(name = "quantity", nullable = false)
    private String quantity;

    @NonNull
    @Getter
    @Setter
    @Column(name = "username", nullable = false)
    private Boolean enabled = true;

    @NonNull
    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private String price;

    @NonNull
    @Getter
    @Setter
    @Column(name = "photo", nullable = false)
    private String photo = SAMPLE_BOOK_PHOTO_URL;
}
