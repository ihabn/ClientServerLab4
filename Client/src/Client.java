import phone.Phone;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try (Phone phone = new Phone("localhost", 8000))
        {
            System.out.println("Connected to server");

            Thread thread = new Thread() {
                @Override
                public void run() {
                    while(true){
                        try {
                            String response = phone.readLine();
                            System.out.println("Answer: " + response);
                        }catch (IOException e) {
                            System.out.println("Server disconnect");
                        }
                    }
                }
            };
            thread.start();
            while (true) {
                String request = in.nextLine();
                phone.writeLine(request);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
