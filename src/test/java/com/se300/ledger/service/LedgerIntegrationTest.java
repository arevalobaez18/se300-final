package com.se300.ledger.service;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = { TestSmartStoreApplication.class })
public class LedgerIntegrationTest {

    @Autowired
    private LedgerAPI ledgerService;

    @Test
    public void testSaveStore() throws LedgerException {

        Account account = ledgerService.createAccount("3", 0);

        assertAll("Verify Account properties",
                () -> assertEquals("3", account.getAddress()),
                () -> assertEquals(0, account.getBalance()));
    }
}
