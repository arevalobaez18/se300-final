package com.se300.ledger.repository;

import com.se300.ledger.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = { FakeAccountRepository.class })
public class AccountRepositoryMockTest {

    @Autowired
    private FakeAccountRepository accountRepository;

    @Test
    public void testAccountRepository() {
        // Create and save a new Account
        Account account = new Account("1", 0);
        accountRepository.save(account);

        // Retrieve the account by ID
        Account retrievedAccount = accountRepository.findById("1").get();

        // Verify the properties of the retrieved account
        assertAll("Verify Account properties",
                () -> assertEquals("1", retrievedAccount.getAddress()),
                () -> assertEquals(0, retrievedAccount.getBalance()));

        // Verify the account exists
        assertTrue(accountRepository.existsById("1"));

        // Verify the count of accounts
        assertEquals(1, accountRepository.count());
    }
}
