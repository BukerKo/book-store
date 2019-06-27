package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.dto.BookTitleToCount;
import com.bukrieiev.bookstore.entity.Booking;
import com.bukrieiev.bookstore.payload.BookingRequest;
import com.bukrieiev.bookstore.payload.BookingResponse;
import com.bukrieiev.bookstore.service.BookService;
import com.bukrieiev.bookstore.service.BookingService;
import com.bukrieiev.bookstore.util.ApiUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private BookService bookService;
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.saveBookingFromRequest(bookingRequest));
    }

    @GetMapping
    public List<BookingResponse> getBookings(@NotNull final Pageable page) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        Long userId = ApiUtil.getCurrentUser().getId();
        List<Booking> bookings = bookingService.findByUserId(page, userId).getContent();
        return bookings.stream().map(item -> {
            BookingResponse response = new BookingResponse();
            response.setDate(dateFormatter.format(item.getCreatedAt()));
            response.setPrice(item.getTotalPrice());
            response.setBookTitleToCounts(item.getBookingBooks().stream().map(entry ->
                    new BookTitleToCount(entry.getBook().getTitle(), entry.getCount())).collect(Collectors.toList()));
            return response;
        }).collect(Collectors.toList());
    }


}
