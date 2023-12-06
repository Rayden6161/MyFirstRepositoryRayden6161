package com.example.gui.events;

import com.example.Global;
import com.example.db.Author;
import com.example.db.AuthorDAO;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SaveAuthorAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel imgAuthorPhoto = (JLabel) Global.components.get("imgAuthorPhoto");
        JTextField txtFirstName = (JTextField) Global.components.get("txtFirstName");
        JTextField txtLastName = (JTextField) Global.components.get("txtLastName");
        JDatePickerImpl dateBornPicker = (JDatePickerImpl) Global.components.get("dateBornPicker");
        JDatePickerImpl dateDiedPicker = (JDatePickerImpl) Global.components.get("dateDiedPicker");
        JTextArea txtBiography = (JTextArea) Global.components.get("txtBiography");

        Author author = new Author();
        author.setFirstName(txtFirstName.getText());
        author.setLastName(txtLastName.getText());
        Date bornDate = (Date) dateBornPicker.getModel().getValue();
        author.setBorn(LocalDate.ofInstant(bornDate.toInstant(), ZoneId.systemDefault()));
        Date diedDate = (Date) dateDiedPicker.getModel().getValue();
        author.setDied(LocalDate.ofInstant(diedDate.toInstant(), ZoneId.systemDefault()));
        author.setBiography(txtBiography.getText());

        boolean inserted = AuthorDAO.insert(author);
        if(inserted) {
            JOptionPane.showConfirmDialog(null, "Author saved!");
        } else {
            JOptionPane.showConfirmDialog(null, "Error saving author!");
        }
    }
}
