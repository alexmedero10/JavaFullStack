import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import models.BankAccount;

import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount("123456", "Ale Doce", 500.00);

        System.out.println("Account created:");
        System.out.println(account);

        try {
            System.out.println("\nAttempting to deposit -50...");
            account.deposit(-50);
        } catch (InvalidAmountException e) {
            System.out.println("Deposit Error: " + e.getMessage());
        }

        try {
            System.out.println("\nAttempting to withdraw 600...");
            account.withdraw(600);
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Withdrawal Error: " + e.getMessage());
        }

        try {
            System.out.println("\nDepositing 200...");
            account.deposit(200);
            System.out.println("New Balance: $" + account.getBalance());

            System.out.println("\nWithdrawing 100...");
            account.withdraw(100);
            System.out.println("New Balance: $" + account.getBalance());
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Transaction Error: " + e.getMessage());
        } finally {
            System.out.println("\nFinal Account Details:");
            System.out.println(account);
            scanner.close();  // Resource management
        }
    }
}
