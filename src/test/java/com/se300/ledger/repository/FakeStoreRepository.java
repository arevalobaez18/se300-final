package com.se300.ledger.repository;

import com.se300.ledger.model.Store;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class FakeStoreRepository implements StoreRepository {

    private final Map<Long, Store> db = new HashMap<>();

    @Override
    public <S extends Store> S save(S entity) {
        this.db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Store> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Store> findById(Long l)  {
        return Optional.ofNullable(this.db.get(l));
    }

    @Override
    public boolean existsById(Long l) {
        return false;
    }

    @Override
    public Iterable<Store> findAll() {
        return null;
    }

    @Override
    public Iterable<Store> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long l) {

    }

    @Override
    public void delete(Store entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Store> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
