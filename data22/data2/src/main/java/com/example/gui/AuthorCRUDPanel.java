package com.example.gui;

import com.example.Global;
import com.example.gui.events.ClearFormFieldsAction;
import com.example.gui.events.SaveAuthorAction;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AuthorCRUDPanel extends JPanel {

    public AuthorCRUDPanel() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.setBorder(new EmptyBorder(5,10,5,10));

        JButton btnNew = new JButton("New Author");
        btnNew.addActionListener(new ClearFormFieldsAction());
        this.add(btnNew);
        layout.putConstraint(SpringLayout.NORTH, btnNew, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, btnNew, -10, SpringLayout.EAST, this);
        //ovo su ogranicivaci Params:
        //e1 – the edge of the dependent c1 – the component of the dependent pad – the fixed distance between dependent and anchor e2 – the edge of the anchor c2 – the component of the anchor

        JLabel imgAuthorPhoto = new JLabel("Text");
        Global.components.put("imgAuthorPhoto", imgAuthorPhoto);
        //u hasmapu ubacimo ovo iznad
        imgAuthorPhoto.setMinimumSize(new Dimension(200, 200));
        imgAuthorPhoto.setPreferredSize(new Dimension(200, 200));
        imgAuthorPhoto.setMaximumSize(new Dimension(200, 200));
        imgAuthorPhoto.setOpaque(true);
        imgAuthorPhoto.setBackground(Color.CYAN);
        this.add(imgAuthorPhoto);
        //dodamo imgAuthorPhoto posle silnih podesavanja
        layout.putConstraint(SpringLayout.NORTH, imgAuthorPhoto, 10, SpringLayout.SOUTH, btnNew);
        layout.putConstraint(SpringLayout.WEST, imgAuthorPhoto, 10, SpringLayout.WEST, this);
//opet ogranicivaci
        JButton btnBrowsePhoto = new JButton("New photo");
        this.add(btnBrowsePhoto); //dodamo dugme  JPanelu kojeg klasa nasledjuje
        layout.putConstraint(SpringLayout.NORTH, btnBrowsePhoto, 10, SpringLayout.SOUTH, imgAuthorPhoto);
        layout.putConstraint(SpringLayout.WEST, btnBrowsePhoto, 10, SpringLayout.WEST, this);
//ogranicivaci opet
        JLabel lblFirstName = new JLabel("First name");
        this.add(lblFirstName);
        layout.putConstraint(SpringLayout.NORTH, lblFirstName, 20, SpringLayout.SOUTH, btnNew);
        layout.putConstraint(SpringLayout.WEST, lblFirstName, 10, SpringLayout.EAST, imgAuthorPhoto);
        JTextField txtFirstName = new JTextField();
        Global.components.put("txtFirstName", txtFirstName);
        txtFirstName.setMinimumSize(new Dimension(250, 20));
        txtFirstName.setPreferredSize(new Dimension(250, 20));
        txtFirstName.setMaximumSize(new Dimension(600, 20));
        this.add(txtFirstName);
        layout.putConstraint(SpringLayout.NORTH, txtFirstName, 10, SpringLayout.SOUTH, lblFirstName);
        layout.putConstraint(SpringLayout.WEST, txtFirstName, 10, SpringLayout.EAST, imgAuthorPhoto);

        JLabel lblLastName = new JLabel("Last name");
        this.add(lblLastName);
        layout.putConstraint(SpringLayout.NORTH, lblLastName, 10, SpringLayout.SOUTH, txtFirstName);
        layout.putConstraint(SpringLayout.WEST, lblLastName, 10, SpringLayout.EAST, imgAuthorPhoto);
        JTextField txtLastName = new JTextField();
        Global.components.put("txtLastName", txtLastName);
        txtLastName.setMinimumSize(new Dimension(250, 20));
        txtLastName.setPreferredSize(new Dimension(250, 20));
        txtLastName.setMaximumSize(new Dimension(600, 20));
        this.add(txtLastName);
        layout.putConstraint(SpringLayout.NORTH, txtLastName, 10, SpringLayout.SOUTH, lblLastName);
        layout.putConstraint(SpringLayout.WEST, txtLastName, 10, SpringLayout.EAST, imgAuthorPhoto);

        JLabel lblBorn = new JLabel("Born");
        this.add(lblBorn);
        layout.putConstraint(SpringLayout.NORTH, lblBorn, 10, SpringLayout.SOUTH, btnBrowsePhoto);
        layout.putConstraint(SpringLayout.WEST, lblBorn, 10, SpringLayout.WEST, this);
        UtilDateModel modelBorn = new UtilDateModel();
        JDatePanelImpl dateBornPanel = new JDatePanelImpl(modelBorn);
        JDatePickerImpl dateBornPicker = new JDatePickerImpl(dateBornPanel);
        Global.components.put("dateBornPicker", dateBornPicker);
        this.add(dateBornPicker);
        layout.putConstraint(SpringLayout.NORTH, dateBornPicker, 10, SpringLayout.SOUTH, lblBorn);
        layout.putConstraint(SpringLayout.WEST, dateBornPicker, 10, SpringLayout.WEST, this);

        JLabel lblDied = new JLabel("Died");
        this.add(lblDied);
        layout.putConstraint(SpringLayout.NORTH, lblDied, 10, SpringLayout.SOUTH, dateBornPicker);
        layout.putConstraint(SpringLayout.WEST, lblDied, 10, SpringLayout.WEST, this);
        UtilDateModel modelDied = new UtilDateModel();
        JDatePanelImpl dateDiedPanel = new JDatePanelImpl(modelDied);
        JDatePickerImpl dateDiedPicker = new JDatePickerImpl(dateDiedPanel);
        Global.components.put("dateDiedPicker", dateDiedPicker);
        this.add(dateDiedPicker);
        layout.putConstraint(SpringLayout.NORTH, dateDiedPicker, 10, SpringLayout.SOUTH, lblDied);
        layout.putConstraint(SpringLayout.WEST, dateDiedPicker, 10, SpringLayout.WEST, this);

        JLabel lblBiography = new JLabel("Biography");
        layout.putConstraint(SpringLayout.NORTH, lblBiography, 10, SpringLayout.SOUTH, dateDiedPicker);
        layout.putConstraint(SpringLayout.WEST, lblBiography, 10, SpringLayout.WEST, this);
        this.add(lblBiography);
        JTextArea txtBiography = new JTextArea();
        Global.components.put("txtBiography", txtBiography);
        txtBiography.setLineWrap(true);
        txtBiography.setWrapStyleWord(true);
        JScrollPane scrollBiography = new JScrollPane(txtBiography);
        scrollBiography.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBiography.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBiography.setMinimumSize(new Dimension(400, 100));
        scrollBiography.setPreferredSize(new Dimension(400, 200));
        scrollBiography.setMinimumSize(new Dimension(800, 450));
        this.add(scrollBiography);
        layout.putConstraint(SpringLayout.NORTH, scrollBiography, 10, SpringLayout.SOUTH, lblBiography);
        layout.putConstraint(SpringLayout.WEST, scrollBiography, 10, SpringLayout.WEST, this);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new SaveAuthorAction());
        this.add(btnSave);
        layout.putConstraint(SpringLayout.NORTH, btnSave, 10, SpringLayout.SOUTH, scrollBiography);
        layout.putConstraint(SpringLayout.WEST, btnSave, 10, SpringLayout.WEST, this);
    }
}
/*znaci ove kokmponente prvo definisemo pa onda ide to pozicioniranje u te putConstraints
* JER NAS JPANEL KORISTI SPRING LAYOUT ...*/