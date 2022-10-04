import phone.Phone;
import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000);) {
            System.out.println("Server started!");
            while(true) {
                try (Phone phone = new Phone(server)) {
                    phone.getClients();
                    phone.getConnection();
                        while (true) {
                            String request = phone.readLine();
                            System.out.println("Request: " + request);
                            String response = request;
                            phone.writeLine(response);
                            System.out.println("Response: " + response);
                        }
                    } catch (IOException e){
                        System.out.println("Client is disconnect");
                    }
                catch (NullPointerException y) {
                    y.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


