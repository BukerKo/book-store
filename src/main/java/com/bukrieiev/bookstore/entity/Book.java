package com.bukrieiev.bookstore.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Getter
    @Setter
    @Column(name = "title", nullable = false)
    private String title;

    @NonNull
    @Getter
    @Setter
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @NonNull
    @Getter
    @Setter
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @NonNull
    @Getter
    @Setter
    @Column(name = "price", nullable = false)
    private String price;

    @NonNull
    @Getter
    @Setter
    @Column(name = "photo", nullable = false)
    private String photo;
}
