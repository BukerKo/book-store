package com.bukrieiev.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="booking_books")
@AllArgsConstructor
@NoArgsConstructor
public class BookingBook implements Serializable {

    @Getter
    @Setter
    @Id
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @Getter
    @Setter
    @Id
    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;


    @Getter
    @Setter
    @Column(name="count")
    private Long count;
}
