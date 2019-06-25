package com.bukrieiev.bookstore.dao;

import com.bukrieiev.bookstore.entity.Booking;
import com.bukrieiev.bookstore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {

    Page<Booking> findByUserId(Pageable page, Long id);
}
