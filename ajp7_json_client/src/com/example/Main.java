package com.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {
        try  {
            Socket client = new Socket("127.0.0.1", 50000);
            Scanner scanner = new Scanner(System.in);
            Gson gson = new Gson();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

            boolean running = true;
            while(running) {
                System.out.print("Enter operation (+,-,*,/) or Q to quit: ");
                String operation = scanner.nextLine().trim();
                switch(operation) {
                    case "+": case "-": case "*": case "/": {
                        System.out.print("Enter first number: ");
                        double n1 = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter second number: ");
                        double n2 = Double.parseDouble(scanner.nextLine());
                        Operation op = new Operation(n1,n2,operation);
                        System.out.println("Operation object created");
                        writer.println(gson.toJson(op));
                        op = gson.fromJson(reader.readLine(), Operation.class);
                        System.out.println(op);
                        break;
                    }
                    case "q": case "Q": {
                        writer.println("!END");
                        running = false;
                        reader.close();
                        writer.close();
                        client.close();
                        break;
                    }
                    default: {
                        System.err.println("Error: Option must be +,-,*,/ or Q.");
                    }

                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}