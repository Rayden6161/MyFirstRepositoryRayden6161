package com.example.gui.events;

import com.example.Global;
import com.example.db.Author;
import net.sourceforge.jdatepicker.AbstractDateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;

public class AuthorComponentLoader {
    public static void load(Author author) {
        JLabel imgAuthorPhoto = (JLabel) Global.components.get("imgAuthorPhoto");
        JTextField txtFirstName = (JTextField) Global.components.get("txtFirstName");
        JTextField txtLastName = (JTextField) Global.components.get("txtLastName");
        JDatePickerImpl dateBornPicker = (JDatePickerImpl) Global.components.get("dateBornPicker");
        JDatePickerImpl dateDiedPicker = (JDatePickerImpl) Global.components.get("dateDiedPicker");
        JTextArea txtBiography = (JTextArea) Global.components.get("txtBiography");

        if(author.getPhoto() != null) {
            // TODO: Dodati sliku
        }
        if(author.getFirstName() != null) {
            txtFirstName.setText(author.getFirstName());
        } else {
            txtFirstName.setText("");
        }

        if(author.getLastName() != null) {
            txtLastName.setText(author.getLastName());
        } else {
            txtFirstName.setText("");
        }

        if(author.getBorn() != null) {
            dateBornPicker.getModel().setDate(author.getBorn().getYear(),
                    author.getBorn().getMonthValue(),
                    author.getBorn().getDayOfMonth());
            dateBornPicker.getModel().setSelected(true);
        } else {
            dateBornPicker.getModel().setDate(1900,0,0);
            dateBornPicker.getModel().setSelected(true);
        }
        if(author.getDied() != null) {
            dateDiedPicker.getModel().setDate(author.getDied().getYear(),
                    author.getDied().getMonthValue(),
                    author.getDied().getDayOfMonth());
            dateDiedPicker.getModel().setSelected(true);
        } else {
            dateDiedPicker.getModel().setDate(1900,0,0);
            dateDiedPicker.getModel().setSelected(true);
        }
        if(author.getBiography() != null) {
            txtBiography.setText(author.getBiography());
        } else {
            txtBiography.setText("");
        }
    }
}
