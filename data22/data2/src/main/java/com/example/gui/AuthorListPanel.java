package com.example.gui;

import com.example.Global;
import com.example.db.Author;
import com.example.db.AuthorDAO;
import com.example.gui.events.LoadAuthorDetailsAction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AuthorListPanel extends JPanel {

    public AuthorListPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<Author> authors = AuthorDAO.all();
        //tip Athor znaci sadrzi autore
        Author[] authorsArray = new Author[authors.size()];
        authorsArray = authors.toArray(authorsArray);
        //znc 2 reda iznad je za konvertovanje u Array i to prosledimo listi(GLUPOST!)
        JList<Author> listAuthors = new JList<>(authorsArray);
        //tip nam sadrzi autore
        Global.components.put("listAuthors", listAuthors);
        listAuthors.setPreferredSize(new Dimension(250, 200));
        listAuthors.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));
        listAuthors.setAutoscrolls(true);
        listAuthors.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(listAuthors); //dodajemo panelu koji ova klasa nasledjuje listu autora

        JButton btnLoad = new JButton("Load Details");
        btnLoad.addActionListener(new LoadAuthorDetailsAction());
        btnLoad.setMinimumSize(new Dimension(250, 50));
        btnLoad.setPreferredSize(new Dimension(250, 50));
        btnLoad.setMaximumSize(new Dimension(250, 50));
        btnLoad.setAlignmentX(Component.LEFT_ALIGNMENT);//OVAJ DEO JE BITAN imali smo malo
        //shiftovanje u prikazu i kad smo ovo podesili onda se sredio prikaz.
        this.add(btnLoad);
    }
}
