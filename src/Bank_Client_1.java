import java.io.*;
import java.net.*;

public class Bank_Client_1
{
	public static void main(String args[]) throws IOException
	{
		//Set up the socket in and out variables
		Socket BankClientSocket=null; //Set BankClientSocket variable to null
		PrintWriter out=null; //Set PrintWriter output null
		BufferedReader in=null; //Set bufferedReader to null
		int BankSocketNumber=4545; //Sets socket numeber for server, in this case 4545, however can be set to anything, provided the server is set to run and listen on that port
		String BankServerName="localhost"; //Sets hostname of the server, in this case localhost as it is being run on the same computer. For multi computer setup, change to hostname of computer runnign server
		String BankClientID="BankClient1"; //Sets the name for this class, in this case BankClient1 as it is Client1
		
		try //This try look
		{
			BankClientSocket=new Socket(BankServerName, BankSocketNumber);
			out=new PrintWriter(BankClientSocket.getOutputStream(), true);
			in=new BufferedReader(new InputStreamReader(BankClientSocket.getInputStream()));
		}
		
		catch(UnknownHostException e)
		{
			System.err.println("Don't know about host: localhost");
			System.exit(1);
		}
		
		catch(IOException e)
		{
			System.err.println("Couldn't get the I/O connection to: "+BankSocketNumber);
			System.exit(1);
		}
		
		BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
		String FromServer;
		String FromUser;
		
		System.out.println("Initialised "+BankClientID+" client and IO connections");
		
		//This is modified as it's the client that speaks first
		
		while(true)
		{
			FromUser=stdIn.readLine();
			if(FromUser!=null)
			{
				System.out.println(BankClientID+" sending "+FromUser+" to Bank Server");
				out.println(FromUser);
			}
			FromServer=in.readLine();
			System.out.println(BankClientID+" recieved "+FromServer+" from Bank Server");
		}
	}
}