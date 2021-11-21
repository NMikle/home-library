package io.mikle.home.lib.api.model;

import java.util.List;

public record Book(Long id, String title, List<Author> authors) implements Entity {
    public Book(String title, List<Author> authors) {
        this(null, title, authors);
    }
}
