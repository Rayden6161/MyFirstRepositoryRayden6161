package com.example.gui.events;

import com.example.Global;
import com.example.db.Author;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadAuthorDetailsAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JList<Author> listAuthors = (JList<Author>) Global.components.get("listAuthors");
        Author author = listAuthors.getSelectedValue();
        AuthorComponentLoader.load(author);
    }
}
