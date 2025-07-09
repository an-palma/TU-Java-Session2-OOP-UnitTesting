package org.example;

public abstract class Account {
    private String accountNumber;
    private String accountName;
    private double balance;
    private String accountType;
    private double interestRate;

    public Account() {
    }

    public Account(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = name;
        this.balance = balance;
    }

    public Account(String accountNumber, String name, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountName = name;
        this.balance = balance;
        this.accountType = accountType;
    }

    public Account(String accountNumber, String name, double balance, String accountType, double interestRate) {
        this.accountNumber = accountNumber;
        this.accountName = name;
        this.balance = balance;
        this.accountType = accountType;
        this.interestRate = interestRate;
    }

    public double computeInterest(double balance) {
        System.out.printf("Interest rate for %s account type is %f...\n", this.accountType, this.interestRate);
        double interestEarned = balance * this.interestRate;
        System.out.printf("Interest Earned: %f\n", interestEarned);
        double newBalance = balance + interestEarned;
        System.out.printf("New Balance: %f\n", newBalance);
        return newBalance;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
