package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.BookRepository;
import com.bukrieiev.bookstore.dao.BookingRepository;
import com.bukrieiev.bookstore.dto.BookFromBookingRequest;
import com.bukrieiev.bookstore.entity.Book;
import com.bukrieiev.bookstore.entity.Booking;
import com.bukrieiev.bookstore.entity.BookingBook;
import com.bukrieiev.bookstore.payload.BookingRequest;
import com.bukrieiev.bookstore.util.ApiUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {

    BookingRepository bookingRepository;
    BookService bookService;

    public Booking persist(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Page<Booking> findByUserId(Pageable page, Long userId) {
        return bookingRepository.findByUserId(page, userId);
    }

    @Transactional
    public Booking saveBookingFromRequest(BookingRequest bookingRequest) {

        Map<Long, Long> bookIdToCount = bookingRequest.getBooks().stream()
                .collect(Collectors.toMap(BookFromBookingRequest::getId, BookFromBookingRequest::getCount));
        Set<Book> updatedBooks = bookService.updateBooksQuantity(bookIdToCount);

        Long totalPrice = bookService.countTotalPriceByIds(bookIdToCount.keySet());
        Booking booking = new Booking(ApiUtil.getCurrentUser(), totalPrice);

        List<BookingBook> bookingBooks = bookIdToCount.entrySet().stream().map(entry -> new BookingBook(
                updatedBooks.stream().filter(book -> book.getId().equals(entry.getKey())).findAny().orElse(null),
                booking,
                entry.getValue())).collect(Collectors.toList());

        bookingBooks.forEach(booking.getBookingBooks()::add);

        Booking savedBooking = persist(booking);

        savedBooking.setBookingBooks(new HashSet<>());
        return savedBooking;
    }



}
