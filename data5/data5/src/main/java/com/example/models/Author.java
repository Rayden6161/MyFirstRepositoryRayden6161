package com.example.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Author implements Serializable {
    private int author_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private LocalDate born;
    private LocalDate died;
    private byte[] photo;
    private String biography;

    public Author() {}

    public Author(String first_name, String last_name, LocalDate born, LocalDate died) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.born = born;
        this.died = died;
    }

    public Author(String first_name, String middle_name, String last_name, LocalDate born, LocalDate died) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.born = born;
        this.died = died;
    }

    public Author(String first_name, String last_name, LocalDate born, byte[] photo, String biography) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.born = born;
        this.photo = photo;
        this.biography = biography;
    }

    public Author(String first_name, String middle_name, String last_name, LocalDate born, LocalDate died, byte[] photo, String biography) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.born = born;
        this.died = died;
        this.photo = photo;
        this.biography = biography;
    }

    public Author(int author_id, String first_name, String middle_name, String last_name, LocalDate born, LocalDate died, byte[] photo, String biography) {
        this.author_id = author_id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.born = born;
        this.died = died;
        this.photo = photo;
        this.biography = biography;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public LocalDate getDied() {
        return died;
    }

    public void setDied(LocalDate died) {
        this.died = died;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
