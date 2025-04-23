import java.util.Scanner;

public class Loops {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Assignment 1
        System.out.println("Numbers from 1 to 10:");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
        System.out.println("Sum of the first 10 positive integers: " + sum);


        System.out.print("Enter a number (1 to 10): ");
        int number = scanner.nextInt();
        System.out.println("Multiplication table for " + number + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }

        // Assignment 2
        System.out.print("Enter a positive integer: ");
        number = scanner.nextInt();

        while (number < 0) {
            System.out.print("Please enter a positive integer: ");
            number = scanner.nextInt();
        }

        int originalNumber = number;
        sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        System.out.println("The sum of the digits of " + originalNumber + " is: " + sum);

        // Assignment 3
        int choice;
        do {
            System.out.println("\n--- Calculator Menu ---");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit Calculator");
            System.out.print("Choose an operation: ");
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter the first number: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter the second number: ");
                double num2 = scanner.nextDouble();

                switch (choice) {
                    case 1:
                        System.out.println("Result: " + num1 + " + " + num2 + " = " + (num1 + num2));
                        break;
                    case 2:
                        System.out.println("Result: " + num1 + " - " + num2 + " = " + (num1 - num2));
                        break;
                    case 3:
                        System.out.println("Result: " + num1 + " * " + num2 + " = " + (num1 * num2));
                        break;
                    case 4:
                        if (num2 != 0) {
                            System.out.println("Result: " + num1 + " / " + num2 + " = " + (num1 / num2));
                        } else {
                            System.out.println("Cannot divide by zero!");
                        }
                        break;
                }
            } else if (choice != 5) {
                System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);

    }
}