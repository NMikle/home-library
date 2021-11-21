package io.mikle.home.lib.core.service;

import io.mikle.home.lib.api.model.Entity;
import io.mikle.home.lib.api.service.EntityService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class CollectionEntityService<T extends Entity> implements EntityService<T> {

    protected final Map<Long, T> data = new ConcurrentHashMap<>();
    protected final AtomicLong nextId = new AtomicLong(1);

    @Override
    public T save(T entity) {
        final T entityWithId = addIdToEntity(nextId.getAndIncrement(), entity);
        data.put(entityWithId.id(), entityWithId);
        return entityWithId;
    }

    @Override
    public Optional<T> find(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Collection<T> findAll() {
        return data.values();
    }

    @Override
    public T update(T entity) {
        data.put(entity.id(), entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        data.remove(id);
    }

    protected abstract T addIdToEntity(Long id, T entity);

}
