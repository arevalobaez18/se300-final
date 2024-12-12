package com.se300.ledger.controller;

import com.se300.ledger.model.Account;
import com.se300.ledger.service.Ledger;
import com.se300.ledger.service.LedgerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LedgerRestController {

    private final Ledger ledger;

    @Autowired
    public LedgerRestController(Ledger ledger) {
        this.ledger = ledger;
    }

    public Account createAccount(@RequestBody Account account) throws LedgerException {

        // Use the Ledger service to create the account
        return ledger.createAccount(account.getAddress());
    }

    public Account getAccount(@PathVariable String address) throws LedgerException {

        // Retrieve the account balance using the Ledger service
        Integer balance = ledger.getAccountBalance(address);

        // Create and return an Account object with the retrieved balance
        return new Account(address, balance);
    }
}
