package com.se300.ledger.repository;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { TestSmartStoreApplication.class })
public class AccountRepositoryIntegrationTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testAccountRepository() {
        // Create a new account
        Account account = new Account("4", 100);
        accountRepository.save(account);

        // Retrieve the account by ID
        Optional<Account> retrievedAccount = accountRepository.findById("4");
        assertTrue(retrievedAccount.isPresent(), "Account should be present");
        assertEquals("4", retrievedAccount.get().getAddress());
        assertEquals(100, retrievedAccount.get().getBalance());

        // Update the account balance
        account.setBalance(200);
        accountRepository.save(account);

        // Verify the update
        retrievedAccount = accountRepository.findById("4");
        assertTrue(retrievedAccount.isPresent(), "Account should be present after update");
        assertEquals(200, retrievedAccount.get().getBalance());

        // Delete the account
        accountRepository.deleteById("4");

        // Verify the account is deleted
        retrievedAccount = accountRepository.findById("4");
        assertFalse(retrievedAccount.isPresent(), "Account should be deleted");
    }
}
