package com.se300.ledger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Account class implementation representing account in the Blockchain
 *
 * @author Sergey L. Sundukovskiy
 * @version 1.0
 * @since 2024-11-14
 */
@Entity
public class Account {

    @Id
    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    /**
     * Account Constructor
     * 
     * @param address
     * @param balance
     */
    public Account(String address, Integer balance) {
        this.address = address;
        this.balance = balance;
    }

    public Account() {

    }

    /**
     * Getter Method for account address
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter Method for account address
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for account balance
     * 
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Setter method for account balance
     * 
     * @param balance
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * Method for creating an account copy
     * 
     * @return
     */
    @Override
    public Object clone() {
        return new Account(this.getAddress(), this.balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Account account))
            return false;
        return address.equals(account.address) && balance.equals(account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, balance);
    }
}
