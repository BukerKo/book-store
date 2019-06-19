package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.BookRepository;
import com.bukrieiev.bookstore.dao.BookingRepository;
import com.bukrieiev.bookstore.entity.Book;
import com.bukrieiev.bookstore.entity.Booking;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;
    BookRepository bookRepository;

    public Booking persist(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Page<Booking> findAll(Pageable page) {
        return bookingRepository.findAll(page);
    }

}
