import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main  {
            public static void main(String[] args)  {
Frame f = new Frame("Calculator");

f.setSize(400,300);
f.setLayout(null);
      Label title = new Label("Kalkulator");
                Label l1 = new Label("Unesite prvi broj :");
                Label l2 = new Label("Unesite drugi broj broj :");
                Label l3= new Label("Rezultat je :");
                Label l4 = new Label("Operacije za racunanje:");

                TextField txt1 = new TextField();
                TextField txt2 = new TextField();
                TextField txt3 = new TextField();

                title.setBounds(150, 50, 200, 20);
                l1.setBounds(60, 100, 123, 26);
                l2.setBounds(60, 140, 123, 26);
                l3.setBounds(60, 240, 123, 26);
                l4.setBounds(60, 180, 123, 26);
                txt1.setBounds(200, 100, 123, 20);
                txt2.setBounds(200, 140, 123, 20);
                txt3.setBounds(200, 240, 123, 30);

Choice izborOperacija = new Choice();
                izborOperacija.add("+");
                izborOperacija.add("-");
                izborOperacija.add("*");
                izborOperacija.add("/");
                izborOperacija.setBounds(200, 170, 126, 33);
                Button button = new Button("Izracunaj :");
                button.setBounds(200,207,122,30);


                txt1.setBackground(Color.white);
                txt2.setBackground(Color.white);
                txt3.setBackground(Color.green);
                f.setBackground(Color.cyan);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
int prviBroj =Integer.parseInt((txt1.getText()));
int drugiBroj = Integer.parseInt(txt2.getText());

String choiceString = izborOperacija.getSelectedItem();

if(choiceString.equals("+")){
  txt3.setText(String.valueOf(prviBroj + drugiBroj));

}
   if(choiceString.equals("-")){
 txt3.setText(String.valueOf(prviBroj - drugiBroj));

                        }
    if(choiceString.equals("*")){
 txt3.setText(String.valueOf(prviBroj * drugiBroj));

                        }
  if(choiceString.equals("/")){
     txt3.setText(String.valueOf(prviBroj / drugiBroj));

                        }
          }
                });
                f.add(izborOperacija);
                f.add(button);
                f.add(l1);
                f.add(l2);
                f.add(l3);
                f.add(l4);
                f.add(txt1);
                f.add(txt2);
                f.add(txt3);
                f.add(title);
                f.setVisible(true);

                }}
