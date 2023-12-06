import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Integer> numbers = new ArrayList();
            Scanner s = new Scanner(System.in);
//ispod pravimo beskonacnu petlju dok ne unesemo   q
            while (true) {
                System.out.print("Enter a number  or Q to quit :");
                String userValue = s.nextLine();
                if (userValue.equals("Q")) { //ukoliko pritisnemo Q zavrsavaj program
                    break; //naredbom break;
                }
                try {
                    int number = Integer.parseInt(userValue);//umesto s.nextLine ubacili smo userValue...
                    //korak nakon kreiranja klase
                    if(number < 1){//ukoliko je unos broja manji od jedan odmah throwuje exception
                        throw new InvalidNumberException(number);/*bila je prazna zagrada,pa smo u klasi
                        napravili konstuktor i prosledili int number koji prosledjujemo ovom izuzetku iliti metodi
                        */
                    }
                    numbers.add(number);


                }
                catch(InvalidNumberException ex) { //vrednost unetog broja mora biti pozitivan broj
                    System.out.println("value must be a positive number");
                }
                catch (NumberFormatException ex) {
                    System.err.println(" Error : Value \' " + userValue + " \' must be a number !!!" );
                    // ISPISACE  Error : Value ' a ' must be a number !!!
                    Thread.sleep(500); //ovde nam je ispisalo gresku pa pravimo general try catch
                }

                for (int number1 : numbers) {
                    System.err.println(number1 + "\t");


                }


            }
        }
catch(Exception ex){
            ex.printStackTrace();//znaci ovo je generalni try catch
}

    }
}
/*
* izuzeci mogu da se nasledjuju
* takodje mi mozemo da definisemo svoje greske
* */