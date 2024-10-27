package com.se300.ledger.model.parametrized;

import com.se300.ledger.model.*;
import com.se300.ledger.service.Ledger;
import com.se300.ledger.service.LedgerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParametrizedTest {

    static Ledger ledger = null;
    static Account mary = null;
    static Account sergey = null;

    @BeforeAll
    static void setUpClass() throws LedgerException {
        ledger = Ledger.getInstance("test", "test ledger 2023","chapman");
        ledger = Ledger.getInstance("test", "test ledger 2023","chapman");
        ledger.createAccount("mary");
        ledger.createAccount("sergey");

        Account master = ledger.getUncommittedBlock().getAccount("master");
        mary = ledger.getUncommittedBlock().getAccount("mary");
        sergey = ledger.getUncommittedBlock().getAccount("sergey");

        Transaction firstTransaction =
                new Transaction("1",60,10,"simple test", master, mary);
        Transaction secondTransaction =
                new Transaction("2",60,10,"simple test", master, mary);
        Transaction thirdTransaction =
                new Transaction("3",60,10,"simple test", master, mary);
        Transaction forthTransaction =
                new Transaction("4",60,10,"simple test", master, mary);
        Transaction fifthTransaction =
                new Transaction("5",60,10,"simple test", master, mary);
        Transaction sixTransaction =
                new Transaction("6",60,10,"simple test", master, mary);
        Transaction seventhTransaction =
                new Transaction("7",60,10,"simple test", master, mary);
        Transaction eightsTransaction =
                new Transaction("8",60,10,"simple test", master, mary);
        Transaction ninthTransaction =
                new Transaction("9",60,10,"simple test", master, mary);
        Transaction tenthTransaction =
                new Transaction("10",60,10,"simple test", master, mary);

        ledger.processTransaction(firstTransaction);
        ledger.processTransaction(secondTransaction);
        ledger.processTransaction(thirdTransaction);
        ledger.processTransaction(forthTransaction);
        ledger.processTransaction(fifthTransaction);
        ledger.processTransaction(sixTransaction);
        ledger.processTransaction(seventhTransaction);
        ledger.processTransaction(eightsTransaction);
        ledger.processTransaction(ninthTransaction);
        ledger.processTransaction(tenthTransaction);
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaron", "rebekah"})
    void testDuplicateAccountCreation(String name) throws LedgerException {

        ledger.createAccount(name);
        assertThrows(LedgerException.class, () -> ledger.createAccount(name));
    }

    @ParameterizedTest
    @CsvSource({"11,60,10,simple test, master, mary", "12,60,10,simple test, master, mary"})
    void testWordsInSentence(String transactionId, int value, int fee, String description, String payer, String payee) throws LedgerException {
        Account actualPayer = ledger.getUncommittedBlock().getAccount(payer);
        Account actualPayee = ledger.getUncommittedBlock().getAccount(payee);

        Transaction nextTransaction =
                new Transaction(transactionId, value, fee, description, actualPayer, actualPayee );

        ledger.processTransaction(nextTransaction);

        assertNotNull(ledger.getTransaction(transactionId));
    }
}
