package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.BookRepository;
import com.bukrieiev.bookstore.entity.Book;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public Set<Book> updateBooksQuantity(Map<Long, Long> bookIdToQuantityToDelete) {
        Set<Book> booksFromDb = findBooksById(bookIdToQuantityToDelete.keySet());
        booksFromDb.forEach(book -> book.setQuantity(book.getQuantity() - bookIdToQuantityToDelete.get(book.getId())));
        return Sets.newHashSet(bookRepository.saveAll(booksFromDb));
    }

    public Long countTotalPriceByIds(Set<Long> bookIds) {
        return bookRepository.countTotalPriceByIds(bookIds);
    }

    public Set<Book> findBooksById(Iterable<Long> ids) {
        return Sets.newHashSet(bookRepository.findAllById(ids));
    }
}

