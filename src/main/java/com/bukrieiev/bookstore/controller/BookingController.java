package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.entity.Booking;
import com.bukrieiev.bookstore.payload.BookingRequest;
import com.bukrieiev.bookstore.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.persist(booking);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping
    public Page<Booking> getBookings(@NotNull final Pageable page) {
        return bookingService.findAll(page);
    }
}
