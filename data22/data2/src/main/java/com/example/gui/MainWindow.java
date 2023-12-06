package com.example.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        this.setTitle("Bookstore authors");
        this.setSize(new Dimension(750, 800));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Author List Panel
        AuthorListPanel alp = new AuthorListPanel();
        this.add(alp, BorderLayout.WEST);
        //JFrame-u dodamo alp zapadno u borderLayout-u u glavnom prozoru

        // Author CRUD Panel
        AuthorCRUDPanel acp = new AuthorCRUDPanel();
        this.add(acp, BorderLayout.CENTER);
        //isto samo sto dodajemo u centar
        //onda ovu klasu instanciramo u Main...
    }
}
