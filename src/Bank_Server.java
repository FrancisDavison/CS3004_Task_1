//====================Modified from CS3004 Lab 4 Action Server, Simon Taylor, 2021====================

import java.io.*;
import java.net.*;


public class Bank_Server
{
	public static void main(String args[]) throws IOException
	{
		ServerSocket BankServerSocket=null; //Sets BankServerSocket variable to null, so it can be assigned later in the code
		boolean listening=true; //Sets listening to true, so it starts the code already listening for connections on port 4545
		String BankServerName="Bank Server"; //Sets the name for the Bank Sever
		int BankServerNumber=4545; //Sets the socket number for the server
		
		double Balances[]={1000,1000,1000}; //Original double changed to array of doubles to allow three values to be stored in one variable
		
		//Create the shared object in the global scope...
		
		Shared_Bank_State OurSharedBankStateObject = new Shared_Bank_State(Balances); //Sends the initial state of the shared variable double balaces[] line (above)
		
		//Make the server socket
		
		try
		{
			BankServerSocket = new ServerSocket(BankServerNumber); //Attempts to start the server socket
		}
		catch(IOException e) //If server socket could not be started, IOException will be thrown and dealt with here
		{
			System.err.println("Could not start "+BankServerName+" specified port."); //Inform user that server could not be started on selected port
			System.exit(1);
		}
		System.out.println(BankServerName+" started"); //Inform user that server started successfully
		
		while(listening) //essentially while(true), so will always be listening on port 4545
		{
			new Bank_Server_Thread(BankServerSocket.accept(), "BankServerThread1", OurSharedBankStateObject).start(); //When thread 1 is requested, this line accepts it
			System.out.println("New " + BankServerName + " thread started."); //Inform the user that the thread has been started
			new Bank_Server_Thread(BankServerSocket.accept(), "BankServerThread2", OurSharedBankStateObject).start(); //When thread 2 is requested, this line accepts it
			System.out.println("New " + BankServerName + " thread started."); //Inform the user that the thread has been started
			new Bank_Server_Thread(BankServerSocket.accept(), "BankServerThread3", OurSharedBankStateObject).start(); //When thread 3 is requested, this line accepts it
			System.out.println("New " + BankServerName + " thread started."); //Informs the user that the thread has been started
		}
		BankServerSocket.close(); //Closes socket
	}
}