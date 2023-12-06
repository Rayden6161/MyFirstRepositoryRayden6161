package com.example.dao;

import com.example.models.Author;

import java.util.ArrayList;

public class AuthorDAO {

    public static ArrayList<Author> all() {
        return (ArrayList<Author>)JdbiConnection.get().withHandle( handle -> {
           return handle.createQuery("SELECT * FROM authors;").mapToBean(Author.class).list();
        });
    }
    public static Author one(int author_id) {
        return JdbiConnection.get().withHandle( handle -> {
            return handle.createQuery("SELECT * FROM authors WHERE author_id = :author_id").
                    bind("author_id", author_id).mapToBean(Author.class).first();
        });
    }

    public static boolean save(Author author) {
        int rows_affected = JdbiConnection.get().withHandle( handle -> {
           return handle.createUpdate("INSERT INTO authors VALUES (NULL, :first_name, :middle_name, :last_name, :born, :died, :photo, :biography)")
                   .bindBean(author).execute();
        });
        return rows_affected > 0;
    }

    public static boolean update(Author author) {
        int rows_affected = JdbiConnection.get().withHandle( handle -> {
            return handle.createUpdate("""
                    UPDATE authors SET
                    first_name = :first_name, middle_name = :middle_name,
                    last_name = :last_name, born = :born, died = :died,
                    photo = :photo, biography = :biography
                    WHERE author_id = :author_id""")
                    .bindBean(author).execute();
        });
        return rows_affected > 0;
    }

    public static boolean delete(int author_id) {
        int rows_affected = JdbiConnection.get().withHandle( handle -> {
           return handle.createUpdate("DELETE FROM authors WHERE author_id = :author_id")
                   .bind("author_id", author_id).execute();
        });
        return rows_affected > 0;
    }

    public static ArrayList<Author> livingAuthors() {
        return (ArrayList<Author>)JdbiConnection.get().withHandle( handle -> {
           return handle.createQuery("SELECT * FROM authors WHERE died IS NULL").
           mapToBean(Author.class).list();
        });
    }
}
