package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {

    Page<Booking> findByUserId(Pageable page, Long id);
}
