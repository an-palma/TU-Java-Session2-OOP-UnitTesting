package org.example;

public class Savings extends Account {

    public Savings() {

    }

    public Savings(String accountNumber, String name, double balance) {
        super(accountNumber, name, balance, "Savings", 0.05);
    }

}
