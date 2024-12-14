package com.se300.ledger.repository;

import org.springframework.stereotype.Repository;
import com.se300.ledger.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class FakeAccountRepository implements CrudRepository<Account, String> {

    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public <S extends Account> S save(S entity) {
        accounts.put(entity.getAddress(), entity);
        return entity;
    }

    @Override
    public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return accounts.containsKey(id);
    }

    @Override
    public Iterable<Account> findAll() {
        return accounts.values();
    }

    @Override
    public Iterable<Account> findAllById(Iterable<String> ids) {
        Map<String, Account> result = new HashMap<>();
        for (String id : ids) {
            if (accounts.containsKey(id)) {
                result.put(id, accounts.get(id));
            }
        }
        return result.values();
    }

    @Override
    public long count() {
        return accounts.size();
    }

    @Override
    public void deleteById(String id) {
        accounts.remove(id);
    }

    @Override
    public void delete(Account entity) {
        accounts.remove(entity.getAddress());
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        for (String id : ids) {
            accounts.remove(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {
        for (Account entity : entities) {
            accounts.remove(entity.getAddress());
        }
    }

    @Override
    public void deleteAll() {
        accounts.clear();
    }
}
