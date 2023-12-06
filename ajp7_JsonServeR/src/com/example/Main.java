package com.example;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(50000);
            Gson gson = new Gson();

            Socket client = server.accept();
            System.out.println("Client has been connected. " + client);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            String message;
            while(true) {
                try {
                    message = reader.readLine();//ono sto procitamo smestamo u String message
                    if(message == null || message.equals("!END")) {
                        reader.close(); //ukoliko je prazna poruka ili se zavrsava sa !END prekini sa svim ispod
                        writer.close();
                        client.close();
                        System.out.println("Client ended communication!");
                        break;
                    } else {
                        Operations op = gson.fromJson(message, Operations.class); //u suprotnom prvo instanci klase Operations dodelimo unos iz Operations klase iz Json formata
                        op.calculate(); //i onda tom unosu dodelimo metodu calculate
                        writer.println(gson.toJson(op)); //u posaljemo u Json odgovor(op) nazad u Json
                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Client has been disconnected!");
                    break;
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}