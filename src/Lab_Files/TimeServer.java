package Lab_Files;
import java.net.*;
import java.io.*;

public class TimeServer {
  public static void main(String[] args) throws IOException {

	ServerSocket timeServerSocket = null;
    boolean listening = true;

    // Make the server socket

    try {
      timeServerSocket = new ServerSocket(4545);
    } catch (IOException e) {
      System.err.println("Could not start TimeServer specified port");
      System.exit(-1);
    }
    System.out.println("Server started");

    while (listening){
      new TimeServerThread(timeServerSocket.accept()).start();
      System.out.println("New Time Server thread started");
    }
    timeServerSocket.close();
  }
}