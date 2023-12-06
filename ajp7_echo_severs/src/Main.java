import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        try{
            ServerSocket server = new ServerSocket(50000);//koristimo ovaj port jer smo prilicno sigurni da ga niko ne koristi
            System.out.println("Server starting!");
            //ovo proverava da li se neko od klijenata na nas server kaci
            Socket client = server.accept();

            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(client.getInputStream()));
//znaci ovaj inputstreamreader nam cita podatke koji su 0 i 1 i nama ih prezentuje u citljivijem formatu.
            client.getInputStream();
            PrintWriter writer = new PrintWriter(client.getOutputStream(),true)  ;
//client.getOutputStream() znaci vracamo podatke nazad, imamo i onaj nas cuveni flush
            //ovo true nam je ako klijent ne moze da dobije sve podatke znaci ovo true ce da "ocisti tunel"da bi podaci bili prosledjeni.
            System.out.println("Client has been connected.f");
String message; //ovo ce nam biti neka poruka i moramo napraviti beskonacnu petlju gde cemo da ucitamo tu poruku

           while(true){
               message = reader.readLine()  ;
               if(message == null || message.equals("")){
                   writer.close();
                   client.close();
                   System.out.println("Client hass beeb disconected!");
               }else {
                   if(message.contains("!Calc")){
                       String[]split = message.split(" "); //splitujemo po spejsu
                       if(split.length !=3){
                           writer.println("wrong number of arguments.Comand" +
                                   "example: !Calc 7 5.5");
                       }else {//u suprotnom ako je sve dobro treba da dobijem te brojeve
                           double n1 = Double.parseDouble(split[1]); //posto nam je na nultoj poziciji !Calc pa ide razmak zato je 1
                           double n2 = Double.parseDouble(split[2]);
                           writer.println(String.format("%.2f +%.2f%.2f",n1,n2,n1+n2));
                       }
                   }
              else {
                       LocalTime now = LocalTime.now();
                       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                       writer.println(String.format("[%s]%s",
                               now.format(dtf), message));
                   }
                   //znaci ovo je sve sto je potrebno da bi server odgovorio klijentu.
               }
           }

        } catch (IOException e) {
         e.printStackTrace();
        }


    }}/*
znaci uvek gledamo da server vrati odgovor klijentu bez obzira kakav je taj odgovor jer server se opterecuje
ukoliko recimo hiljadu korisnika naprasno izadje ili se diskonektuje a razlog zbog kog je server opterecen
je taj da  server ima svoje BAFERE  i ako te bafere ne prazni onda bude preopterecen a to se upravo
desava kad ne vrati odgovor klijentu.Zato moramo paziti da lupimo ovaj zadnji else
Znaci za svaki odgovor klijentu moramo da imamo pripremljen nacin da zastitimo server od slicnih situacija.

    */