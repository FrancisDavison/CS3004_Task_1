package Lab_Files;
import java.net.*;
import java.io.*;


public class TimeServerThread extends Thread {

  private Socket timeSocket = null;

  public TimeServerThread(Socket timeSocket) {
    super("TimeServerThread");
    this.timeSocket = timeSocket;
  }

  public void run() {
    try {
      System.out.println("Initialising thread IO connections and state object");
      PrintWriter out = new PrintWriter(timeSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(timeSocket.getInputStream()));
      String inputLine, outputLine;
      System.out.println("Here");
      TimeServerState tss = new TimeServerState();
      System.out.println("Here1");      
      //This is the trick - in KK the server communicated first...  Need to change so that the Client communicates first
      
      while ((inputLine = in.readLine()) != null) {
          System.out.println("Here2 " + inputLine);
        outputLine = tss.processInput(inputLine);
        out.println(outputLine);
        if (outputLine.equals("Bye"))
          break;
      }

       out.close();
       in.close();
       timeSocket.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}