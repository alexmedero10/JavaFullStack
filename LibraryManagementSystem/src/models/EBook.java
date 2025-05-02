package models;

public class EBook extends Book {
    private String fileFormat;
    private double fileSize;

    public EBook(String title, String author, String isbn, String fileFormat, double fileSize) {
        super(title, author, isbn);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileFormat(String format) {
        if (format != null && !format.isEmpty())
            this.fileFormat = format;
    }

    public void setFileSize(double size) {
        if (size > 0)
            this.fileSize = size;
    }

    @Override
    public String toString() {
        return super.toString() + "[EBook Format: " + fileFormat + ", Size: " + fileSize + "MB]";
    }
}
