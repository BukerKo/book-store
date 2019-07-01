package com.bukrieiev.bookstore.service;

import com.bukrieiev.bookstore.dao.BookRepository;
import com.bukrieiev.bookstore.entity.Book;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public Set<Book> updateBooksQuantity(Map<Long, Long> bookIdToQuantityToDelete) {
        Set<Book> booksFromDb = findBooksById(bookIdToQuantityToDelete.keySet());
        booksFromDb.forEach(book -> book.setQuantity(book.getQuantity() - bookIdToQuantityToDelete.get(book.getId())));
        return Sets.newHashSet(bookRepository.saveAll(booksFromDb));
    }

    public Double countTotalPriceByIds(Map<Long, Long> bookIdToCount) {
        List<Book> books = bookRepository.findByIdIn(bookIdToCount.keySet());
        return books.stream()
                .map(book -> book.getPrice()*bookIdToCount.get(book.getId()))
                .reduce(0d, Double::sum);
    }

    public Set<Book> findBooksById(Iterable<Long> ids) {
        return Sets.newHashSet(bookRepository.findAllById(ids));
    }
}

