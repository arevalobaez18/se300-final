package com.se300.ledger.model.mocks;

import com.se300.ledger.model.*;
import com.se300.ledger.service.Ledger;
import com.se300.ledger.service.LedgerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MockTest {

    @Test
    void testPayerBalanceCheck() throws LedgerException {

        Ledger ledger = Ledger.getInstance("test", "test ledger 2023","chapman");

        Account mary = mock(Account.class);
        Account sergey = mock(Account.class);

        Transaction sampleTransaction =
                new Transaction("1",1000,10,"simple test", mary, sergey);

        assertThrows(LedgerException.class, () -> ledger.processTransaction(sampleTransaction));

        verify(mary, times(1)).getBalance();

    }
}
