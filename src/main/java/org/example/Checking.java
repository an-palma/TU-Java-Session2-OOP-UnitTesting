package org.example;

public class Checking extends Account {

    public Checking() {

    }

    public Checking(String accountNumber, String name, double balance) {
        super(accountNumber, name, balance, "Checking", 0.02);
    }

}
