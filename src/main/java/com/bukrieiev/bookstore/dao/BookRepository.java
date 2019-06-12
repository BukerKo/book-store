package com.bukrieiev.bookstore.dao;


import com.bukrieiev.bookstore.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
