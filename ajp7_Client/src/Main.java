import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
try{
     Socket client = new Socket("127.0.0.1",50000);
     Scanner input = new Scanner(System.in) ;
    BufferedReader reader = new BufferedReader
            (new InputStreamReader(client.getInputStream()));
    PrintWriter writer = new PrintWriter(client.getOutputStream(),true)  ;
    while(true){
        System.out.println("Enter your message:");
        String message = input.nextLine().trim();
        if(message.length() ==0 ){
            writer.println(message);//znaci saljemo nas message writerom ako je nula
            reader.close();
            writer.close();
            client.close();//zatvaramo sve komplet jer ne ocekujemo poruke od klijenta.
            System.out.println("YOU have disconected!");
            break;
        }
        else{ //u suprotnom isto saljemo poruku ali cemo da procitamo odgovor nazad.
            writer.println(message);
            String response =reader.readLine();
            System.out.println(response);
        }
    }



}catch (Exception ex){
    ex.printStackTrace();
}
    }
}
//mi kad zatvorimo prozor na iks imamo u grafickoj aplikaciji nesto kao closingevent da bi smo to uhvatili navreme
//znaci moramo racunati i na te stvari kad konfigurisemo server...