import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 50000);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            boolean running = true;
            while(running) {
                System.out.print("Enter command (T - time, P - put message, G - all messages, E - end): ");
                String option = scanner.nextLine().trim();
                switch(option) {
                    case "t": case "T": {
                        writer.println("!TIME");
                        String response = reader.readLine();
                        System.out.println("Time: " + response);
                        break;
                    }
                    case "e": case "E": {
                        writer.println("!END");
                        running = false;
                        reader.close();
                        writer.close();
                        client.close();
                        break;
                    }
                    case "p": case "P": {
                        System.out.print("Enter your message: ");
                        String message = scanner.nextLine();
                        writer.println("!PUT|" + message);
                        System.out.println("Message has been added!");
                        break;
                    }
                    case "g": case "G": {
                        writer.println("!GET");
                        System.out.println(reader.readLine());
                        break;
                    }
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}