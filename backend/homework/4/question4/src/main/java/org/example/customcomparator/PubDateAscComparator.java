package org.example.customcomparator;

import org.example.Book;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        int yearComparison = Integer.compare(book1.getPublicationYear(), book2.getPublicationYear());
        if (yearComparison == 0) {
            // If publication years are the same, compare titles in ascending order
            return book1.getTitle().compareTo(book2.getTitle());
        }
        return yearComparison;
    }
}