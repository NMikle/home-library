package io.mikle.home.lib.core.rest;

import io.mikle.home.lib.api.exception.RestException;
import io.mikle.home.lib.api.model.Book;
import io.mikle.home.lib.api.rest.BookRest;
import io.mikle.home.lib.api.service.BookService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static io.mikle.home.lib.core.exception.RestErrorCode.BOOK_NOT_FOUND;
import static java.util.Collections.singletonList;

@RestController
public record BookRestService(BookService bookService) implements BookRest {

    @Override
    public Collection<Book> findAll() {
        return bookService.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookService.find(id)
                .orElseThrow(() -> new RestException(BOOK_NOT_FOUND, singletonList(id)));
    }

    @Override
    public Book create(Book book) {
        return bookService.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        return bookService.update(book);
    }

    @Override
    public Book partialUpdate(Long id, Book book) {
        return null; //todo: implement
    }

    @Override
    public void delete(Long id) {
        bookService.delete(id);
    }
}
