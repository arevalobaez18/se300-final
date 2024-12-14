package com.se300.ledger.service;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Store;
import com.se300.ledger.model.StoreModelException;
import com.se300.ledger.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = { TestSmartStoreApplication.class })
public class StoreIntegrationTest {

    @Autowired
    private StoreModelAPI storeService;

    @Test
    public void testSaveStore() throws LedgerException, StoreModelException {

        Store store = storeService.provisionStore(Long.parseLong("2"), "75 Forbes", "My First Store", null);

        assertAll("Verify Account properties",
                () -> assertEquals(2, store.getId()),
                () -> assertEquals("75 Forbes", store.getAddress()),
                () -> assertEquals("My First Store", store.getDescription()));
    }
}