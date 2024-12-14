package com.se300.ledger.service;

import com.se300.ledger.TestSmartStoreApplication;
import com.se300.ledger.model.Account;
import com.se300.ledger.repository.AccountRepository;

import jakarta.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = { TestSmartStoreApplication.class })
public class LedgerMockTest {

    @MockBean
    @Qualifier("AccountRepository")
    private AccountRepository accountRepository;

    @Autowired
    private Ledger ledger;

    @PostConstruct
    public void prepare() {
        this.ledger.accountRepository = accountRepository;
    }

    @Test
    public void testAccountRepository() throws LedgerException {
        // Create a mock Account object
        Account account = new Account("7", 500);

        // Define the behavior of the mock repository
        when(accountRepository.save(any())).thenReturn(account);

        // Call the method under test
        Account savedAccount = ledger.createAccount("7", 500);

        // Verify that the repository's save method was called once
        verify(accountRepository, times(1)).save(any(Account.class));

        // Assert that the returned account has the expected properties
        assertAll("Verify Account properties",
                () -> assertEquals(savedAccount.getAddress(), account.getAddress()),
                () -> assertEquals(savedAccount.getBalance(), account.getBalance()));
    }
}