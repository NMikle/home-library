package io.mikle.home.lib.api.service;

import io.mikle.home.lib.api.model.Entity;

import java.util.Collection;
import java.util.Optional;

public interface EntityService<T extends Entity> {

    T save(T entity);

    Optional<T> find(Long id);

    Collection<T> findAll();

    T update(T entity);

    void delete(Long id);

}
