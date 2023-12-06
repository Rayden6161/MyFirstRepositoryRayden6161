package com.example.dao;


import org.jdbi.v3.core.Jdbi;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Book;

public class BookDAO {
    private static final Jdbi jdbi = JdbiConnection.get();

    public static ArrayList<Book> all() {
        return (ArrayList<Book>)jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books")
                        .mapToBean(Book.class)
                        .list());
    }

    public static Book one(int bookId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books WHERE book_id = :book_id")
                        .bind("book_id", bookId)
                        .mapToBean(Book.class)
                        .findOne()
                        .orElse(null));
    }

    public static void save(Book book) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO books (title, cover, release_date) VALUES (:title, :cover, :release_date)")
                    .bindBean(book)
                    .execute();
        });
    }

    public static void update(Book book) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE books SET title = :title, cover = :cover, release_date = :release_date WHERE book_id = :book_id")
                    .bindBean(book)
                    .execute();
        });
    }

    public static void delete(int bookId) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM books WHERE book_id = :book_id")
                    .bind("book_id", bookId)
                    .execute();
        });
    }

    public static ArrayList<Book> getBooksByReleaseDateRange(LocalDate startDate, LocalDate endDate) {
        return (ArrayList<Book>)jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books WHERE release_date BETWEEN :startDate AND :endDate")
                        .bind("startDate", startDate)
                        .bind("endDate", endDate)
                        .mapToBean(Book.class)
                        .list());
    }
}

