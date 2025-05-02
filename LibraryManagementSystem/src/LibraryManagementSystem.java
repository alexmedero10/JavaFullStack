import models.*;

public class LibraryManagementSystem {
    public static void main(String[] args) {

        Library library = new Library();

        Book b1 = new Book("Java Basics", "Alice", "111");
        Book b2 = new Book("Data Structures", "Bob", "112");
        EBook eb1 = new EBook("OOP in Java", "Charlie", "113", "PDF", 2.5);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(eb1);

        Member m1 = new Member("John", "M001");
        PremiumMember pm1 = new PremiumMember("Emma", "PM001");

        library.registerMember(m1);
        library.registerMember(pm1);

        library.borrowBook("M001", "111");
        library.borrowBook("PM001", "112");
        library.borrowBook("PM001", "113");

        System.out.println();
        library.displayAvailableBooks();
        System.out.println();
        library.displayMembers();

    }
}