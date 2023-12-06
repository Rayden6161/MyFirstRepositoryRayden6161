import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Proxy;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

    try {
        Scanner input = new Scanner (System. in);

      Gson gson = new Gson();
      Socket client = new Socket("localhost",1055);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        PrintWriter writer = new PrintWriter(client.getOutputStream());
boolean running = true;
while(running){System.out.print("Enter operation (+,-,*,/) or Q to quit: ");
    String operation = input.nextLine().trim();
    switch(operation) {
        case "+": case "-": case "*": case "/": {
            System.out.print("Enter first number: ");
            double n1 = Double.parseDouble(input.nextLine());
            System.out.print("Enter second number: ");
            double n2 = Double.parseDouble(input.nextLine());
            Operations op = new Operations(n1,n2,operation);
            System.out.println("Operation object created");
            writer.println(gson.toJson(op)); //saljemo zahtev,znaci pretvaramo u JSON nasu operaciju...
            op = gson.fromJson(reader.readLine(), Operations.class); //ovde dobijamo odgovor nazad
            //znaci citamo odgovor po redovima a sve to iz klase Operations.
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

        }}


    }
} catch (IOException e) {
        throw new RuntimeException(e);
    } catch (NumberFormatException e) {
        throw new RuntimeException(e);
    } catch (JsonSyntaxException e) {
        throw new RuntimeException(e);
    }

    }


    }

