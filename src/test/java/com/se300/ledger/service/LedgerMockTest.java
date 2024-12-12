package com.se300.ledger.service;

import com.se300.ledger.model.Account;
import com.se300.ledger.model.StoreModelException;
import com.se300.ledger.repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LedgerMockTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private Ledger ledger;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAccountRepository() throws LedgerException, StoreModelException {
        // TODO: Implement Account Repository Mock Testing

        String address = "123 Main St";
        Account mockAccount = new Account(address, 100);

        when(accountRepository.findByAddress(address)).thenReturn(mockAccount);

        Account retrievedAccount = ledger.createAccount(address);

        assertEquals(mockAccount.getAddress(), retrievedAccount.getAddress());
        assertEquals(mockAccount.getBalance(), retrievedAccount.getBalance());

        // Verify that the repository's findByAddress method was called once
        verify(accountRepository, times(1)).findByAddress(address);
    }
}
