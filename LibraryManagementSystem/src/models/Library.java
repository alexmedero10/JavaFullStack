package models;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;
    private final List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void borrowBook(String memberId, String isbn) {
        Member member = null;
        Book book = null;

        for (Member memberValues : members) {
            if (memberValues.getMemberId().equals(memberId)) {
                member = memberValues;
                break;
            }
        }

        for (Book bookValues : books) {
            if (bookValues.getIsbn().equals(isbn)) {
                book = bookValues;
                break;
            }
        }


        if (member != null && book != null) {
            if (member.borrowBook(book)) {
                System.out.println(member.getName() + " borrowed " + book.getTitle());
            } else {
                System.out.println("Cannot borrow book. Limit reached or book unavailable.");
            }
        } else {
            System.out.println("Member or Book not found.");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");

        for (Book book : books) {
            if (book.isAvailable())
                System.out.println(book);
        }
    }

    public void displayMembers() {
        System.out.println("Registered Members:");
        for (Member member : members) {
            System.out.println(member);
        }
    }
}
