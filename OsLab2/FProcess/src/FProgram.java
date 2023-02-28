import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FProgram {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 1234);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                int x = Integer.parseInt(in.readLine());

                int f_x = x * x + 2 * x + 1;
                out.println(f_x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}