//====================Modified from CS3004 Lab 4 Action Server, Simon Taylor, 2021====================

import java.io.*;
import java.net.*;

public class Bank_Server_Thread extends Thread
{
	private Socket BankSocket=null; //Setting socket name for BankSocket to null
	private Shared_Bank_State MySharedBankStateObject; //Setting Shared_bank_State object equal to MySharedBankStateObject
	private String MyBankServerThreadName; //Initialising string for MyBankServerThreadName
	private double MySharedVariable; //Initialising double MySharedVariable
	
	//Set up the thread
	public Bank_Server_Thread(Socket BankSocket, String BankServerThreadName, Shared_Bank_State SharedObject)
	{
		this.BankSocket=BankSocket; //THIS lol
		MySharedBankStateObject=SharedObject; //Setting MySharedBankStateObject equal to SharedObject
		MyBankServerThreadName=BankServerThreadName; //Setting MyBankServerThreadName equal to BankServerThreadName
	}
	
	public void run() //This run allows threads to request a lock, acquire a lock, or release a lock when needed
	{
		try
		{
			System.out.println(MyBankServerThreadName+" initialising"); //Inform user thread is being initialised
			PrintWriter out=new PrintWriter(BankSocket.getOutputStream(), true); //PrintWriter stuff, can't remember
			BufferedReader in=new BufferedReader(new InputStreamReader(BankSocket.getInputStream())); //BufferedReader from socket input
			String InputLine, OutputLine; //Initialising InputLine and OutputLine Strings to be used later
			
			while((InputLine=in.readLine())!=null) //While there is a user input to read...
			{
				try //Get a lock, then call ProcessInput to perform the analysis and carry out the action
				{
					MySharedBankStateObject.AcquireLock(); //Acquires lock
					OutputLine=MySharedBankStateObject.ProcessInput(MyBankServerThreadName, InputLine); // Calls ProcessInput and passes Client ThreadName, and then user input, this returns OutputLine string
					out.println(OutputLine); //Prints OutputLine string
					MySharedBankStateObject.ReleaseLock(); //Releases lock and allows another thread to perform action.
				}
				catch(InterruptedException e) //If lock failed to be established, InterruptedException is thrown and dealt with here
				{
					System.err.println("Failed to get lock when reading: "+e); //Inform user that lock failed to be established
				}
			}
			out.close(); //Close PrintWriter out
			in.close(); //Close BufferedReader in
			BankSocket.close(); //Close sokcet
		}
		
		catch(IOException e) //Catch IOException
		{
			e.printStackTrace(); //Print error
		}
	}
}