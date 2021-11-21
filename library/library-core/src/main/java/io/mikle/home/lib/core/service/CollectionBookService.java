package io.mikle.home.lib.core.service;

import io.mikle.home.lib.api.model.Book;
import io.mikle.home.lib.api.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class CollectionBookService extends CollectionEntityService<Book> implements BookService {
    @Override
    protected Book addIdToEntity(Long id, Book entity) {
        return new Book(id, entity.title(), entity.authors());
    }
}
