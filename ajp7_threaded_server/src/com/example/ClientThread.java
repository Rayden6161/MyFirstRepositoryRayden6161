package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClientThread extends Thread {
    private Socket client;
    private static ArrayList<String> messages = new ArrayList();
    private static String text = "";

    public ClientThread(Socket client) {
        this.client = client;
    } //konstruktor nase klase

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            String message;
            while (true) {//beskonacna petlja
                message = reader.readLine().trim(); //smestimo u nasu promenljivu ono sto procita nas BufferedReader
                if(message.equals("!TIME")) { //uslov
                    LocalTime now = LocalTime.now(); //instanciramo trenutno vreme
                    writer.println(now.toString()); //i to upisemo tj saljemo serveru
                }else if(message.equals("!END")) {
                    break; //ukoliko je uslov !END prekidamo program
                } else if(message.contains("!PUT")) { //ukoliko je !PUT
                    String[] split = message.split("\\|");// delimo unutar naseg niza
                    messages.add(split[1]);
                    //u nasu private static AL messages dodamo prvi elemenat niza split koji je splitovan
                } else if(message.equals("!GET")) { //ukoliko je poruka !GET
                    text = "";
                    messages.forEach( m -> text += m + ";");//
                    writer.println(text); //upisemo taj text tj posaljemo serveru
                } else {
                    writer.println("Invalid command: " + message);
                }
            }
            reader.close();
            writer.close();
            client.close();
            System.out.println("Client has disconnected!");
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
