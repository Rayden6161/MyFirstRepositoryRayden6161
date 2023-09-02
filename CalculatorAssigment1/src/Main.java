import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Calculator {

    public static void main(String[] args) {
        Frame f = new Frame("Calculator");
        f.setLayout(new FlowLayout());
        f.setSize(400, 330);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Label naslov = new Label("Mini AWT Calculator");
        Label l1 = new Label("Unesite prvi broj");
        Label l2 = new Label("Unesite drugi broj");
        Label l3 = new Label("Rezultat:");
        Label l4 = new Label("Izbor operacije");

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();

        naslov.setBounds(150, 50, 200, 20);
        l1.setBounds(50, 100, 120, 25);
        l2.setBounds(50, 140, 120, 25);
        l3.setBounds(50, 240, 120, 25);
        l4.setBounds(50, 180, 120, 25);

        t1.setBounds(200, 100, 120, 20);
        t2.setBounds(200, 140, 120, 20);
        t3.setBounds(200, 240, 120, 30);

        Choice mychoice = new Choice();
        mychoice.add("+");
        mychoice.add("-");
        mychoice.add("*");
        mychoice.add("/");

        mychoice.setBounds(200, 180, 120, 20);

        Button b1 = new Button("Izračunaj");
        b1.setBounds(200, 208, 120, 25);

        t1.setBackground(Color.white);
        t2.setBackground(Color.white);
        t3.setBackground(Color.pink);
        f.setBackground(Color.cyan);

        f.setLayout(null);
        f.add(mychoice);
        f.add(naslov);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(b1);

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int n1 = Integer.parseInt((t1.getText()));
                int n2 = Integer.parseInt((t2.getText()));
                //int n3  = Integer.parseInt((t3.getText()));

                String s = mychoice.getSelectedItem();

                if (s.equals("+")) {
                    t3.setText(String.valueOf(n1 + n2));
                }

                if (s.equals("-")){
                    t3.setText(String.valueOf(n1 - n2));
                }

                if (s.equals("*")){
                    t3.setText(String.valueOf(n1 * n2));
                }

                if (s.equals("/")){
                    t3.setText(String.valueOf(n1 / n2));


    }}});

        }}