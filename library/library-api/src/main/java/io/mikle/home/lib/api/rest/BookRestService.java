package io.mikle.home.lib.api.rest;

import io.mikle.home.lib.api.model.Book;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/v1/books")
public interface BookRestService {

    @GetMapping
    @ResponseStatus(OK)
    Collection<Book> findAll();

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    Book findById(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(CREATED)
    Book create(@RequestBody Book book);

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    Book update(@PathVariable Long id, @RequestBody Book book);

    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    Book partialUpdate(@PathVariable Long id, @RequestBody Book book);

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable Long id);

}
