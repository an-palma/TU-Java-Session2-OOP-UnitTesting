package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class MyBankApplication {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Account> accountsList = new ArrayList<>();

    static String accountType;
    static boolean isValidDeposit;
    static double balance;

    public static void main(String[] args) {
        int choice;

        System.out.println("=== Welcome to MyBank ===");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Compute Interest");
        System.out.println("5. Display Account Information");
        System.out.println("6. Exit");

        do {
            System.out.print("\nEnter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    computeInterest();
                    break;
                case 5:
                    displayAccountInformation();
                    break;
                case 6:
                    break;
            }
        } while (choice != 6);
    }

    public static void createAccount() {
        System.out.print("Enter Account Type (Savings/Checking): ");
        accountType = sc.next().toLowerCase();

        if (accountType.equalsIgnoreCase("savings")) {
            System.out.print("Enter Account Number: ");
            String accountNumber = sc.next();
            System.out.print("Enter Account Name: ");
            String accountName = sc.next();

            do {
                System.out.print("Initial Deposit: ");
                balance = sc.nextDouble();
                isValidDeposit =  validateInitialDeposit(balance);
            } while (!isValidDeposit);
            
            Savings savingsAccount = new Savings(accountNumber, accountName, balance);
            accountsList.add(savingsAccount);

            System.out.println("Account created successfully.");

        } else if (accountType.equalsIgnoreCase("checking")) {
            System.out.print("Enter Account Number: ");
            String accountNumber = sc.next();
            System.out.print("Enter Account Name: ");
            String accountName = sc.next();

            do {
                System.out.print("Initial Deposit: ");
                balance = sc.nextDouble();
                isValidDeposit =  validateInitialDeposit(balance);
            } while (!isValidDeposit);

            Checking checkingAccount = new Checking(accountNumber, accountName, balance);
            accountsList.add(checkingAccount);

            System.out.println("Account created successfully.");

        } else {
            System.out.println("Invalid account type. Please choose only Savings or Checking.");
            createAccount();
        }
    }

    public static void deposit() {
        System.out.println("Enter account number to use for deposit: ");
        String depositAccount = sc.next();

        for (Account account : accountsList) {
            if (account.getAccountNumber().equalsIgnoreCase(depositAccount)) {
                System.out.print("Enter amount to deposit into account:\n>> ");
                double depositAmount = sc.nextDouble();

                System.out.println(initiateDeposit(account, depositAmount));
            }
        }

    }

    protected static String initiateDeposit(Account account, double depositAmount) {
        if (depositAmount <= 0) {
            return "Error. Invalid deposit amount. Please try another amount.";
        } else {
            double newBalance = account.getBalance() + depositAmount;
            account.setBalance(newBalance);

            System.out.printf("Deposit to Account %s successful.\nNew balance is [%f].\n", account.getAccountNumber(), account.getBalance());

        }
        return "";
    }

    public static void withdraw() {
        System.out.println("Enter account number to use for withdrawal: ");
        String withdrawalAccount = sc.next();

        for (Account account : accountsList) {
            if (account.getAccountNumber().equalsIgnoreCase(withdrawalAccount)) {
                System.out.print("Enter amount to withdraw from account:\n>> ");
                double withdrawAmount = sc.nextDouble();

                System.out.println(initiateWithdrawal(account, withdrawAmount));
            }
        }
    }

    protected static String initiateWithdrawal(Account account, double withdrawAmount) {
        if (withdrawAmount > account.getBalance()) {
            return "Error. Insufficient Funds. Nothing was withdrawn.";
        } else if (withdrawAmount <= 0) {
            return "Error. Invalid amount for withdrawal. Nothing was withdrawn.";
        }

        double newBalance = account.getBalance() - withdrawAmount;
        account.setBalance(newBalance);
        System.out.printf("Withdraw successful.\nAccount %s's new balance is [%f].\n", account.getAccountNumber(), account.getBalance());

        return "";
    }

    public static void computeInterest() {
        System.out.println("Enter account number: ");
        String accountSearch = sc.next();

        for (Account account : accountsList) {
            if (account.getAccountNumber().equalsIgnoreCase(accountSearch)) {
                System.out.printf("Computing interest for Account %s...\n", account.getAccountNumber());
                account.computeInterest(account.getBalance());
            }
        }
    }

    public static void displayAccountInformation() {
        System.out.println("--- MyBank's Accounts ---");
        for (Account account : accountsList) {
            System.out.printf("Account Type: %s\n", account.getAccountType());
            System.out.printf("Account Number: %s\n", account.getAccountNumber());
            System.out.printf("Account Name: %s\n", account.getAccountName());
            System.out.printf("Account Balance: %s\n", account.getBalance());
            System.out.printf("Interest Rate: %f\n", account.getInterestRate());
            System.out.println();
        }
    }

    public static boolean validateInitialDeposit(double balance) {
        if (balance <= 0) {
            System.out.println("Invalid initial deposit. Please enter a valid positive value.");
            return false;
        }
        return true;
    }
}
