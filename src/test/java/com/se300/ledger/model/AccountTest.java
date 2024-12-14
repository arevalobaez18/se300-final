package com.se300.ledger.model;

import com.se300.ledger.TestSmartStoreApplication;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { TestSmartStoreApplication.class })
public class AccountTest {
    @Test
    void testAccountInstantiation() {

        Account account = new Account("5", 33);

        assertAll("Verify Account properties",
                () -> assertEquals("5", account.getAddress()),
                () -> assertEquals(33, account.getBalance()));
    }
}
