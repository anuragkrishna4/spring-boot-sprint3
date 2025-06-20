package com.nisum.library.service;

import com.nisum.library.exception.BookNotFoundException;
import com.nisum.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public List<Book> getBooks(String author, Integer year, int page, int size) {
        List<Book> filtered = new ArrayList<>(books);

        if (author != null && !author.trim().isEmpty()) {
            filtered = filtered.stream()
                    .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                    .collect(Collectors.toList());
        }

        if (year != null) {
            filtered = filtered.stream()
                    .filter(book -> book.getPublishedYear() == year)
                    .collect(Collectors.toList());
        }

        int start = page * size;
        int end = Math.min(start + size, filtered.size());

        if (start >= filtered.size()) return Collections.emptyList();
        return filtered.subList(start, end);
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    public void addBook(Book book) {
        validateBook(book);
        books.add(book);
    }

    public void updateBook(int id, Book updatedBook) {
        validateBook(updatedBook);
        Book existing = getBookById(id);
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setPublishedYear(updatedBook.getPublishedYear());
    }

    public void deleteBook(int id) {
        Book book = getBookById(id);
        books.remove(book);
    }

    private void validateBook(Book book) {
        if (book.getId() == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be blank");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Book author cannot be blank");
        }
        if (book.getPublishedYear() < 1000 || book.getPublishedYear() > 2100) {
            throw new IllegalArgumentException("Published year must be between 1000 and 2100");
        }
    }
}
