import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 50000);
            Scanner input = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    client.getInputStream()
            ));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

            while(true) {
                System.out.print("Enter your message: ");
                String message = input.nextLine().trim();
                if(message.length() == 0) {
                    writer.println(message);
                    reader.close();
                    writer.close();
                    client.close();
                    System.out.println("You have disconnected!");
                    break;
                } else {
                    writer.println(message);
                    String response = reader.readLine();
                    System.out.println(response);
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}