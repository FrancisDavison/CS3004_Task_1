//====================Modified from CS3004 Lab 4 Action Server, Simon Taylor, 2021====================

import java.io.*;
import java.net.*;

public class Bank_Client_2
{
	public static void main(String args[]) throws IOException
	{
		//Set up the socket in and out variables
		Socket BankClientSocket=null; //Set BankClientSocket variable to null
		PrintWriter out=null; //Set PrintWriter output null
		BufferedReader in=null; //Set bufferedReader to null
		int BankSocketNumber=4545; //Sets socket numeber for server, in this case 4545, however can be set to anything, provided the server is set to run and listen on that port
		String BankServerName="localhost"; //Sets hostname of the server, in this case localhost as it is being run on the same computer. For multi computer setup, change to hostname of computer runnign server
		String BankClientID="BankClient2"; //Sets the name for this class, in this case BankClient2 as it is Client1
		
		try //This try loop will attempt to establish a connection with the bank server, provided socket number and hostname are correct, connecion will be succesful
		{
			BankClientSocket=new Socket(BankServerName, BankSocketNumber);
			out=new PrintWriter(BankClientSocket.getOutputStream(), true);
			in=new BufferedReader(new InputStreamReader(BankClientSocket.getInputStream()));
		}
		
		catch(UnknownHostException e) //If the hostname is incorrect, UnknownHostException will be thrown and dealt with here
		{
			System.err.println("Don't know about host: localhost"); //Inform user of hostname problem
			System.exit(1); //Close program
		}
		
		catch(IOException e) //If the socket number is incorrect, IOException will be thrown and dealt with here
		{
			System.err.println("Couldn't get the I/O connection to: "+BankSocketNumber); //Inform user of incorrect socket number
			System.exit(1); //Close program
		}
		
		BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
		String FromServer;
		String FromUser;
		
		System.out.println("Initialised "+BankClientID+" client and IO connections"); //Inform user that successful connection was established to BankServer
		
		//This is modified as it's the client that speaks first
		
		while(true)
		{
			FromUser=stdIn.readLine(); //Allows user to input commands to the server
			if(FromUser!=null)
			{
				System.out.println(BankClientID+" sending "+FromUser+" to Bank Server"); //User input is sent to the server
				out.println(FromUser);
			}
			FromServer=in.readLine();
			System.out.println(BankClientID+" recieved "+FromServer+" from Bank Server"); //Confirmation of successful command received from server.
		}
	}
}