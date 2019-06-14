package com.bukrieiev.bookstore.dao;


import com.bukrieiev.bookstore.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
