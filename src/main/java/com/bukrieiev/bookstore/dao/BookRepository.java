package com.bukrieiev.bookstore.dao;


import com.bukrieiev.bookstore.entity.Book;
import com.bukrieiev.bookstore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Set;

@RepositoryRestResource
@CrossOrigin
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    Long countBooksByVisibleTrue();

    @RequestMapping(path = "/visible", method = RequestMethod.GET)
    Page<Book> findBooksByVisibleTrue(Pageable pageable);

    //TODO add multiplication on quantity
    @Query("SELECT SUM(b.price+0) FROM Book b WHERE b.id IN ?1")
    Long countTotalPriceByIds(Collection<Long> ids);
}
