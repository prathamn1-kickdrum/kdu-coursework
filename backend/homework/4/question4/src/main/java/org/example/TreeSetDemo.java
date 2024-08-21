package org.example;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {
    public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
        Set<Book> books = new TreeSet<>(comparator);

        Book book1 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("The Last Lecture", "Randy Pausch", 2008);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        return books;
    }
}
