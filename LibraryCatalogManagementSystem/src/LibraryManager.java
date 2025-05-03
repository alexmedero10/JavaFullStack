import models.Book;
import models.LibraryCatalog;

import java.util.Scanner;

public class LibraryManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryCatalog catalog = new LibraryCatalog();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("""
            \n--- Library Catalog Management ---
            1. Add New Book
            2. View All Books
            3. Check Book Availability
            4. Toggle Book Availability
            5. Exit
            Choose an option: """);

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addBook();
                case "2" -> catalog.listBooks();
                case "3" -> checkAvailability();
                case "4" -> toggleAvailability();
                case "5" -> exit = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();

        if (!bookId.matches("^B\\d{3}$")) {
            System.out.println("Invalid Book ID format. Use B followed by 3 digits (e.g., B001).");
            return;
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine().trim();

        Book book = new Book(bookId, title, author, genre);
        if (catalog.addBook(book)) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("A book with this ID already exists.");
        }
    }

    private static void checkAvailability() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine().trim();
        catalog.checkAvailability(id);
    }

    private static void toggleAvailability() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine().trim();
        catalog.toggleAvailability(id);
    }
}
