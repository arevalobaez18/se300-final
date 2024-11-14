package com.se300.ledger.repository;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.model.Store;
import com.se300.ledger.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestSmartStoreApplication.class})
public class StoreRepositoryIntegrationTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void testStoreRepository(){

        storeRepository.save(new Store (Long.parseLong("1"), "75 Forbes", "My First Store"));
        Store store = storeRepository.findById(Long.parseLong("1")).get();

        assertAll("Verify Account properties",
                () -> assertEquals(1, store.getId()),
                () -> assertEquals("75 Forbes", store.getAddress()),
                () -> assertEquals("My First Store", store.getDescription())
        );
    }
}
