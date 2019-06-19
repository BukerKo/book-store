package com.bukrieiev.bookstore.dao;


import com.bukrieiev.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestResource
@CrossOrigin
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    Long countBooksByVisibleTrue();

    @RequestMapping(path = "/visible", method = RequestMethod.GET)
    Page<Book> findBooksByVisibleTrue(Pageable pageable);

}
