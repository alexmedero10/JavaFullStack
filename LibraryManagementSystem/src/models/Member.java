package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String memberId;
    private final List<Book> borrowedBooks;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty())
            this.name = name;
    }

    public void setMemberId(String id) {
        if (id != null && !id.isEmpty())
            this.memberId = id;
    }

    public boolean borrowBook(Book book) {
        if (book != null && book.isAvailable() && borrowedBooks.size() < 3) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Member: ").append(name).append(" (ID: ").append(memberId).append(")");
        stringBuilder.append("Borrowed Books: [");

        for (Book book : borrowedBooks) {
            stringBuilder.append(book).append(". ");
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
