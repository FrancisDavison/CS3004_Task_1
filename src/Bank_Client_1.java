import java.io.*;
import java.net.*;

public class Bank_Client_1
{
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException
	{
		//Set up the socket in and out variables
		Socket BankClientSocket=null;
		PrintWriter out=null;
		BufferedReader in=null;
		int BankSocketNumber=4545;
		String BankServerName="localhost";
		String BankClientID="BankClient1";
		
		try
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