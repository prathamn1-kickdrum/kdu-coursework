package org.example.question3;

import org.example.logging.Logger;

/**
 * The `Book` class represents information about a book, including its title,
 * author, publication year, average rating, ratings count, and image URL.
 */
public class Book {

    private String title;
    private String author;
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;

    Logger logger = new Logger();

    /**
     * Gets the title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the publication year of the book.
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year of the book.
     *
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Gets the image URL of the book.
     *
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of the book.
     *
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the author of the book.
     *
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the average rating of the book.
     *
     */
    public void setAverageRating(double rating) {
        this.averageRating = rating;
    }

    /**
     * Gets the average rating of the book.
     *
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Sets the ratings count of the book.
     *
     */
    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     * Gets the ratings count of the book.
     *
     */
    public int getRatingsCount() {
        return ratingsCount;
    }

    /**
     * Prints the details of the book, including title, author name, publication year,
     * average rating, ratings count, and image URL.
     */
    public void printBookDetails() {
        logger.debugLog("Title: " + title);
        logger.debugLog("Author Name: " + author);
        logger.debugLog("Publication Year: " + publicationYear);
        logger.debugLog("Average Rating: " + averageRating);
        logger.debugLog("Ratings Count: " + ratingsCount);
        logger.debugLog("Image URL: " + imageUrl);
    }
}
