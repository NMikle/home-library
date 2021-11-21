package io.mikle.home.lib.api.model;

public record Author(Long id, String name, Integer age) implements Entity {
    public Author(String name, Integer age) {
        this(null, name, age);
    }
}
