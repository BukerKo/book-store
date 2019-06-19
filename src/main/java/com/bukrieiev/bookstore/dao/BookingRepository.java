package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.Booking;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
}
