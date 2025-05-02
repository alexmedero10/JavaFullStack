package models;

public class PremiumMember extends Member {
    public PremiumMember(String name, String memberId) {
        super(name, memberId);
    }

    @Override
    public boolean borrowBook(Book book) {
        if (book != null && book.isAvailable() && getBorrowedBooks().size() < 5) {
            getBorrowedBooks().add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Premium Member: ").append(getName()).append(" (ID: ").append(getMemberId()).append(")");
        stringBuilder.append("Borrowed Books: [");

        for (Book book : getBorrowedBooks()) {
            stringBuilder.append(book).append(". ");
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
