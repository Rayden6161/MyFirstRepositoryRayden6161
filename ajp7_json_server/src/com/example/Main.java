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
                    message = reader.readLine();
                    if(message == null || message.equals("!END")) {
                        reader.close();
                        writer.close();
                        client.close();
                        System.out.println("Client ended communication!");
                        break;
                    } else {
                        Operation op = gson.fromJson(message, Operation.class);
                        op.calculate();
                        writer.println(gson.toJson(op));
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