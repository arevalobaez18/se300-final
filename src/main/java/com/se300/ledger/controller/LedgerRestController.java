package com.se300.ledger.controller;

import com.se300.ledger.model.Account;
import com.se300.ledger.service.LedgerException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class LedgerRestController {

    public Account createAccount(@RequestBody Account account) throws LedgerException {

        //TODO: Implement Create Account

        return null;
    }

    public Account getAccount(@PathVariable String address) throws LedgerException {
        //TODO: Implement Get Account

        return null;
    }
}
