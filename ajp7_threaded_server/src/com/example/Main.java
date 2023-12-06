package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(50000);
            while(true) {
                Socket client = server.accept();
                System.out.println("Client has connected!");
                (new ClientThread(client)).start();//startujemo klijenta unutar nase klase ...

            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}