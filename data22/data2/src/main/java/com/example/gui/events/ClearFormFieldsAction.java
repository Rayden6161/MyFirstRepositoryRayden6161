package com.example.gui.events;

import com.example.db.Author;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearFormFieldsAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Author author = new Author();
        AuthorComponentLoader.load(author);
    }
}
