package models;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty())
            this.title = title;
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty())
            this.author = author;
    }

    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.isEmpty())
            this.isbn = isbn;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String toString() {
        return "Book: " + title + " by " + author + " (ISBN: " + isbn + ") - " + (isAvailable ? "Available" : "Not Available");
    }
}
