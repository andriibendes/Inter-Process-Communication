import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainProgram {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the value x: ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String str = reader.readLine();
        int x = Integer.parseInt(str);

        try (
                ServerSocket serverSocket1 = new ServerSocket(1234);
                ServerSocket serverSocket2 = new ServerSocket(1235);
                Socket clientSocket1 = serverSocket1.accept();
                Socket clientSocket2 = serverSocket2.accept();
                BufferedReader in1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
                PrintWriter out1 = new PrintWriter(clientSocket1.getOutputStream(), true);
                BufferedReader in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));
                PrintWriter out2 = new PrintWriter(clientSocket2.getOutputStream(), true)
        ) {
            out1.println(x);
            out2.println(x);
            while (!in1.ready() || !in2.ready()) {
                System.out.println("Очікування відповіді...");
                try {
                    Thread.sleep(5000); // зачекати 5 секунд
                    System.out.println("Do you want to continue computation? (Y/N)");
                    BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
                    String userInput = consoleIn.readLine().toUpperCase();

                    if (!userInput.equals("Y")) {
                        System.out.println("Calculation stopped!");
                        return;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            int f_x = Integer.parseInt(in1.readLine());
            int g_x = Integer.parseInt(in2.readLine());

            int result = f_x * g_x;
            System.out.println("Result: " + result);
        }
    }
}
