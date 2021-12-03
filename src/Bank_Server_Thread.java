//====================Modified from CS3004 Lab 4 Action Server, Simon Taylor, 2021====================

import java.io.*;
import java.net.*;

public class Bank_Server_Thread extends Thread
{
	private Socket BankSocket=null;
	private Shared_Bank_State MySharedBankStateObject;
	private String MyBankServerThreadName;
	private double MySharedVariable;
	
	//Set up the thread
	public Bank_Server_Thread(Socket BankSocket, String BankServerThreadName, Shared_Bank_State SharedObject)
	{
		this.BankSocket=BankSocket;
		MySharedBankStateObject=SharedObject;
		MyBankServerThreadName=BankServerThreadName;
	}
	
	public void run()
	{
		try
		{
			System.out.println(MyBankServerThreadName+" initialising");
			PrintWriter out=new PrintWriter(BankSocket.getOutputStream(), true);
			BufferedReader in=new BufferedReader(new InputStreamReader(BankSocket.getInputStream()));
			String InputLine, OutputLine;
			
			while((InputLine=in.readLine())!=null)
			{
				//Get a lock first
				try
				{
					MySharedBankStateObject.AcquireLock();
					OutputLine=MySharedBankStateObject.ProcessInput(MyBankServerThreadName, InputLine);
					out.println(OutputLine);
					MySharedBankStateObject.ReleaseLock();
				}
				catch(InterruptedException e)
				{
					System.err.println("Failed to get lock when reading: "+e);
				}
			}
			out.close();
			in.close();
			BankSocket.close();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}