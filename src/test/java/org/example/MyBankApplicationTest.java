package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyBankApplicationTest {

    @BeforeAll
    static void setup() {
        Account savingsAcc = new Savings("001", "Alexandra", 1000);
        Account checkingAcc = new Checking("002", "Nichole", 500);
        MyBankApplication.accountsList.add(savingsAcc);
        MyBankApplication.accountsList.add(checkingAcc);
    }

    @Test
    @DisplayName("Deposit amount for valid account should reflect accordingly")
    void depositValidAccount(){
        double deposit = 500;
        MyBankApplication.initiateDeposit(MyBankApplication.accountsList.getFirst(), deposit);
        assertEquals(1000+500, MyBankApplication.accountsList.getFirst().getBalance());
    }

    @Test
    @DisplayName("No deposit for deposit amount zero")
    void depositInvalidAmountZero() {
        double deposit = 0;
        String status = MyBankApplication.initiateDeposit(MyBankApplication.accountsList.getFirst(), deposit);
        assertEquals("Error. Invalid deposit amount. Please try another amount.", status);
    }

    @Test
    @DisplayName("No deposit should be initiated for negative value")
    void depositInvalidAmountNegative() {
        double deposit = -100;
        String status = MyBankApplication.initiateDeposit(MyBankApplication.accountsList.getFirst(), deposit);
        assertEquals("Error. Invalid deposit amount. Please try another amount.", status);
    }

    @Test
    @DisplayName("No withdrawal should be initiated for amount greater than balance")
    void withdrawGreaterThanBalance() {
        String status = MyBankApplication.initiateWithdrawal(MyBankApplication.accountsList.getFirst(), 5000);
        assertEquals("Error. Insufficient Funds. Nothing was withdrawn.", status);
    }

    @Test
    @DisplayName("No withdrawal should be initiated for negative value")
    void withdrawInvalidAmount() {
        String status = MyBankApplication.initiateWithdrawal(MyBankApplication.accountsList.getFirst(), -100);
        assertEquals("Error. Invalid amount for withdrawal. Nothing was withdrawn.", status);
    }

    @Test
    @DisplayName("Withdrawal amount for valid account should reflect accordingly")
    void withdrawValidAmount() {
        Account acc = MyBankApplication.accountsList.getFirst();
        double withdraw = 100;
        double initialBalance = acc.getBalance();
        MyBankApplication.initiateWithdrawal(acc, withdraw);

        assertEquals(initialBalance - withdraw, acc.getBalance());
    }

    @Test
    @DisplayName("Check Interest Computation for Savings Account")
    void validateInterestComputationSavings() {
        Account acc = MyBankApplication.accountsList.getFirst();
        double initialBalance = acc.getBalance();
        double newBalance = acc.computeInterest(acc.getBalance());
        acc.setBalance(newBalance);

        double interest = 0.05;

        assertEquals(initialBalance + (initialBalance * interest), acc.getBalance());
    }

    @Test
    @DisplayName("Check Interest Computation for Checking Account")
    void validateInterestComputationChecking() {
        Account acc = MyBankApplication.accountsList.getLast();
        double initialBalance = acc.getBalance();
        double newBalance = acc.computeInterest(acc.getBalance());
        acc.setBalance(newBalance);

        double interest = 0.02;

        assertEquals(initialBalance + (initialBalance * interest), acc.getBalance());
    }

    @Test
    @DisplayName("Checking for initial deposit where initial is zero")
    void validationForInitialDepositZero() {
        assertFalse(MyBankApplication.validateInitialDeposit(0));
    }

    @Test
    @DisplayName("Checking for initial deposit where initial is negative")
    void validationForInitialDepositNegative() {
        assertFalse(MyBankApplication.validateInitialDeposit(-10));
    }
}