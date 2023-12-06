import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*Inace ovde pravimo generalni try catch blok a za specificne greske pravimo lokalni blok...
    * */
    public static void main(String[] args) {
       try {
           ArrayList<Integer> numbers = new ArrayList();
           numbers.add(1);
           numbers.add(2);

           Scanner s = new Scanner(System.in);
           System.out.println("Enter an index for item :");
           int index = Integer.parseInt(s.nextLine());

           try {
               System.out.println("Number of index 2 " + numbers.get(index));
           }catch(NumberFormatException ex){ //ukoliko unesemo slova ili nesto drugo ispisace se ova poruka
               System.err.println("vrednost mora biti celobrojna");
           }
           catch (IndexOutOfBoundsException ex) { //ukoliko ispisemo pogresan index ovo ce se ispisati...
               System.err.println("Index nije dobar"); //posle ovog catcha nece se izvrsavati ovaj ispod
               //jer je ovaj prvi uhvacen ili ako nije onda idemo ispod,ova mentolcina stalno prepravlja kod jbm ga u usta..
           }
       }catch (Exception ex) {
               System.err.println("ovde bih logovao gresku");
               ex.printStackTrace(); //prikazuje nam onu gresku pre poziva try catch metode...
               //takodje mozemo imati vise ovih catchova,MOZEMO hvatati vise izuzetaka odjednom

           }
       }
    }
