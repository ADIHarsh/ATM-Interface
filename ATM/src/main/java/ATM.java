

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private double balance;
    private Scanner in;
    private Map<String, String> userdetails;

    public ATM() {
        balance = 0.0;
        in = new Scanner(System.in);
        userdetails = new HashMap<>();
        userdetails.put("X3400", "123");  
        userdetails.put("X7371", "1234");  
    }

    public void run() {
        boolean authenticated = false;
        String acc = "";

        while (!authenticated) {
            System.out.print("Enter 4 digits of your Account no as 'X____': ");
            acc = in.nextLine();

            System.out.print("Enter your password: ");
            String pin = in.nextLine();

            if (authenticateUser(acc, pin)) {
                authenticated = true;
            } else {
                System.out.println("Invalid username or password. Please try again.\n");
            }
        }

        System.out.println("Welcome, User of " + acc + "!");

        boolean exit = false;

        while (!exit) {
            displayMenu();
            int ch = getChoice();

            switch (ch) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawFunds();
                    break;
                case 3:
                    depositFunds();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the ATM. Goodbye!");
    }

    private boolean authenticateUser(String username, String password) {
        String storedPassword = userdetails.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    private void displayMenu() {
        System.out.println("\n==== ATM Interface ====");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Funds");
        System.out.println("3. Deposit Funds");
        System.out.println("4. Exit");
    }

    private int getChoice() {
        System.out.print("Enter your choice: ");
        return in.nextInt();
    }

    private void checkBalance() {
        System.out.println("Current Balance: Rs" + balance);
    }

    private void withdrawFunds() {
        System.out.print("Enter the amount to withdraw: Rs");
        double amount = in.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal of Rs" + amount + " successful.");
        }
    }

    private void depositFunds() {
        System.out.print("Enter the amount to deposit: Rs");
        double amount = in.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            balance += amount;
            System.out.println("Deposit of Rs" + amount + " successful.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}
