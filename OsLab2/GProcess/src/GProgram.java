import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GProgram {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 1235);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {

            while (true) {
                int x = Integer.parseInt(in.readLine());
                int g_x = x * x * x;
                out.println(g_x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}