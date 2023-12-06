package com.example;

import com.example.db.Author;
import com.example.db.AuthorDAO;
import com.example.gui.MainWindow;

public class Main {
    public static void main(String[] args) {
        try {
            MainWindow window = new MainWindow(); //instanciramo klasu
            //ona je sama po sebi JFrame jer je nasledjuje
            //postavimo vidljivost na true.
            window.setVisible(true);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
