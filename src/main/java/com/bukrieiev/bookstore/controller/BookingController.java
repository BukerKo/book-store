package com.bukrieiev.bookstore.controller;

import com.bukrieiev.bookstore.dao.BookRepository;
import com.bukrieiev.bookstore.dto.BookIdToCount;
import com.bukrieiev.bookstore.dto.BookTitleToCount;
import com.bukrieiev.bookstore.entity.*;
import com.bukrieiev.bookstore.payload.BookingRequest;
import com.bukrieiev.bookstore.payload.BookingResponse;
import com.bukrieiev.bookstore.service.BookingService;
import com.bukrieiev.bookstore.util.ApiUtil;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    BookingService bookingService;
    BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest) {

        Map<Long, Long> bookIdToCount = bookingRequest.getBooks().stream()
                .collect(Collectors.toMap(BookIdToCount::getId, BookIdToCount::getCount));
        Set<Book> booksFromDb = Sets.newHashSet(bookRepository.findAllById(bookIdToCount.keySet()));
        booksFromDb.forEach(book -> book.setQuantity(book.getQuantity() - bookIdToCount.get(book.getId())));
        bookRepository.saveAll(booksFromDb);

        Booking booking = new Booking(ApiUtil.getCurrentUser(), bookingRequest.getTotalPrice());

        List<BookingBook> bookingBooks = bookIdToCount.entrySet().stream().map(entry -> new BookingBook(
                booksFromDb.stream().filter(book -> book.getId().equals(entry.getKey())).findAny().get(),
                booking,
                entry.getValue())).collect(Collectors.toList());

        bookingBooks.forEach(booking.getBookingBooks()::add);

        Booking savedBooking = bookingService.persist(booking);

        savedBooking.setBookingBooks(new HashSet<>());
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping
    public List<BookingResponse> getBookings(@NotNull final Pageable page) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        Long userId = ApiUtil.getCurrentUser().getId();
        List<Booking> content = bookingService.findByUserId(page, userId).getContent();
        List<BookingResponse> responses = content.stream().map(item -> {
            BookingResponse response = new BookingResponse();
            response.setDate(dateFormatter.format(item.getCreatedAt()));
            response.setPrice(item.getTotalPrice());
            response.setBookTitleToCounts(item.getBookingBooks().stream().map(entry ->
                    new BookTitleToCount(entry.getBook().getTitle(), entry.getCount())).collect(Collectors.toList()));
            return response;
        }).collect(Collectors.toList());
        return responses;
    }


}
