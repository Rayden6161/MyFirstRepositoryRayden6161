import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(50000);
            System.out.println("Server has started.");
            Socket client = server.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    client.getInputStream()
            ));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Client has been connected!");

            String message;
            while(true) {
                message = reader.readLine();
                if(message == null || message.equals("")) {
                    writer.close();
                    reader.close();
                    client.close();
                    System.out.println("Client has been disconnected.");
                    break;
                } else {
                    if(message.contains("!CALC")) {
                        String[] split = message.split(" ");
                        if(split.length != 3) {
                            writer.println("Wrong number of arguments. Command example: !CALC 7 5.5");
                        } else {
                            double n1 = Double.parseDouble(split[1]);
                            double n2 = Double.parseDouble(split[2]);

                            writer.println(String.format("%.2f + %.2f = %.2f", n1, n2, n1+n2));
                        }
                    } else {
                        LocalTime now = LocalTime.now();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                        writer.println(String.format("[%s] %s",
                                now.format(dtf), message));
                    }
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}