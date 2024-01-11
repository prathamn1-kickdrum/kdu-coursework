package org.example;

/**
 * The Book class represents a book with title, author, and publication year.
 * It implements the Comparable interface for natural ordering based on the book title.
 */
public class Book implements Comparable<Object> {

    private String title;
    private String author;
    private int publicationYear;

    /**
     * Constructs a Book with the specified title, author, and publication year.
     */
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    // Getter and Setter methods

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    // Example of overriding toString() method for better representation

    @Override
    public String toString() {
        return "Book[" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ']';
    }

    @Override
    public int compareTo(Object book) {
        return getTitle().compareTo(((Book) book).getTitle());
    }
}
