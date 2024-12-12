package com.se300.ledger.service;

import com.se300.ledger.model.Account;
import com.se300.ledger.model.Block;
import com.se300.ledger.model.Transaction;

import java.util.Map;

public interface LedgerAPI {

    /**
     * Create an account in the blockchain.
     * 
     * @param address The address of the account.
     * @return The created Account object.
     * @throws LedgerException if the account already exists.
     */
    Account createAccount(String address) throws LedgerException;

    /**
     * Process a transaction in the blockchain.
     * 
     * @param transaction The transaction to be processed.
     * @return The transaction ID.
     * @throws LedgerException if the transaction cannot be processed.
     */
    String processTransaction(Transaction transaction) throws LedgerException;

    /**
     * Get the balance of an account by its address.
     * 
     * @param address The address of the account.
     * @return The balance of the account.
     * @throws LedgerException if the account does not exist.
     */
    Integer getAccountBalance(String address) throws LedgerException;

    /**
     * Get all account balances in the blockchain.
     * 
     * @return A map of account addresses to their balances.
     */
    Map<String, Integer> getAccountBalances();

    /**
     * Get a block by its block number.
     * 
     * @param blockNumber The block number.
     * @return The Block object or null if not found.
     * @throws LedgerException if the block does not exist.
     */
    Block getBlock(Integer blockNumber) throws LedgerException;

    /**
     * Get a transaction by its transaction ID.
     * 
     * @param transactionId The transaction ID.
     * @return The Transaction object or null if not found.
     */
    Transaction getTransaction(String transactionId);

    /**
     * Get the number of blocks in the blockchain.
     * 
     * @return The number of committed blocks.
     */
    int getNumberOfBlocks();

    /**
     * Validate the blockchain for consistency.
     * 
     * @throws LedgerException if the blockchain is inconsistent.
     */
    void validate() throws LedgerException;

    /**
     * Get the current uncommitted block.
     * 
     * @return The current uncommitted Block object.
     */
    Block getUncommittedBlock();

    /**
     * Reset the state of the ledger.
     */
    void reset();
}