package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.BookRepository;
import com.bukrieiev.bookstore.dao.BookingRepository;
import com.bukrieiev.bookstore.entity.Booking;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;
    BookRepository bookRepository;

    public Booking persist(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Page<Booking> findByUserId(Pageable page, Long userId) {
        return bookingRepository.findByUserId(page, userId);
    }

}
