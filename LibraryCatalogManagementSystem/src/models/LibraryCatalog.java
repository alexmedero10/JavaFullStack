package models;

import java.util.*;

public class LibraryCatalog {
    private Map<String, Book> books;

    public LibraryCatalog() {
        books = new HashMap<>();
    }

    public boolean addBook(Book book) {
        if (books.containsKey(book.getBookId())) return false;
        books.put(book.getBookId(), book);
        return true;
    }

    public Book getBookById(String bookId) {
        return books.get(bookId);
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.values().forEach(System.out::println);
        }
    }

    public void checkAvailability(String bookId) {
        Book book = books.get(bookId);
        if (book != null) {
            System.out.println("Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
        } else {
            System.out.println("Book not found.");
        }
    }

    public void toggleAvailability(String bookId) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setAvailable(!book.isAvailable());
            System.out.println("Updated availability.");
        } else {
            System.out.println("Book not found.");
        }
    }
}

