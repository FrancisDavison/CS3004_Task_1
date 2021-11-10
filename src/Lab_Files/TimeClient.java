package Lab_Files;
import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables

        Socket TimeClientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            TimeClientSocket = new Socket("localhost", 4545);
            out = new PrintWriter(TimeClientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(TimeClientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: 4545.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        System.out.println("Initialised client and IO connections");
        
        // This is modified as it's the client that speaks first

        while (true) {
            
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
            }
            fromServer = in.readLine();
            System.out.println("Server: " + fromServer);
        }
            
        
       // Tidy up - not really needed due to true condition in while loop
      //  out.close();
       // in.close();
       // stdIn.close();
       // TimeClientSocket.close();
    }
}
