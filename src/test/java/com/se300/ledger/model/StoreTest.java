package com.se300.ledger.model;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.service.LedgerException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestSmartStoreApplication.class})
public class StoreTest {
    @Test
    void testStoreInstantiation() throws LedgerException {

        Store store = new Store(Long.parseLong("1"), "75 Forbes", "My First Store");

        assertAll("Verify Account properties",
                () -> assertEquals(1, store.getId()),
                () -> assertEquals("75 Forbes", store.getAddress()),
                () -> assertEquals("My First Store", store.getDescription())
        );

    }
}
