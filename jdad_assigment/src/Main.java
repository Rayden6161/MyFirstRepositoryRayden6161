
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

        class Calculator {
            public static void main(String[] args) {
                Frame frame = new Frame("Calculator");
                frame.setSize( 400 ,350);
                frame.setVisible(true);
                Label title = new Label("Calculator");
                Label lbl1 = new Label("Unesite prvi broj:" );
                Label lbl2 = new Label("Unesite drugi broj :");
                Label lbl3 = new Label("Rezultat :");
                Label lbl4 = new Label("Izbor operacije :");

                TextField txt1 = new TextField();
                TextField txt2 = new TextField();
                TextField txt3 = new TextField();

                title.setBounds(153, 52, 200, 30);
                lbl1.setBounds(54, 102, 114, 26);
                lbl2.setBounds(54, 142, 114, 26);
                lbl3.setBounds(54, 242, 114, 26);
                lbl4.setBounds(54, 182, 114, 26);

                txt1.setBounds(200, 100, 114, 26);
                txt2.setBounds(200, 150, 114, 26);
                txt3.setBounds(200, 240, 114, 36);

                Choice izborOperacije = new Choice();
                izborOperacije.add("+");
                izborOperacije.add("-");
                izborOperacije.add("*");
                izborOperacije.add("/");

                izborOperacije.setBounds(200, 180, 120, 20);

                Button button = new Button("Izračunaj");

                button.setBounds(200, 208, 120, 25);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double a = Double.parseDouble(txt1.getText());
                        double b = Double.parseDouble(txt2.getText());
                        String rezultat = izborOperacije.getSelectedItem()  ;

                        if (rezultat.equals("+")) {
                            txt3.setText(String.valueOf(a + b));
                        }

                        if (rezultat.equals("-")){
                            txt3.setText(String.valueOf(a - b));
                        }
                        if (rezultat.equals("*")){
                            txt3.setText(String.valueOf(a * b));
                        }
                        if (rezultat.equals("/")) {
                            txt3.setText(String.valueOf(a / b));
                        }}
                });

                txt1.setBackground(Color.white);
                txt2.setBackground(Color.white);
                txt3.setBackground(Color.yellow);
                frame.setBackground(Color.cyan
                );
                frame.setLayout(null);

frame.add(title);
frame.add(lbl1);
frame.add(lbl2);
frame.add(lbl3);
frame.add(lbl4);
frame.add(txt1);
frame.add(txt2);
frame.add(txt3);
frame.add(izborOperacije);
frame.add(button);

            }
    }
