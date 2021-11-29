import java.io.*;
import java.net.*;


public class Bank_Server
{
	public static void main(String args[]) throws IOException
	{
		ServerSocket BankServerSocket=null;
		boolean listening=true;
		String BankServerName="Bank Server";
		int BankServerNumber=4545;
		
		double SharedVariable[]={1000,1000,1000};
		
		//Create the shared object in the global scope...
		
		Shared_Bank_State OurSharedBankStateObject = new Shared_Bank_State(SharedVariable);
		
		//Make the server socket
		
		try
		{
			BankServerSocket = new ServerSocket(BankServerNumber);
		}
		catch(IOException e)
		{
			System.err.println("Could not start "+BankServerName+" specified port.");
			System.exit(1);
		}
		System.out.println(BankServerName+" started");
		
		while(listening)
		{
			new Bank_Server_Thread(BankServerSocket.accept(), "BankServerThread1", OurSharedBankStateObject).start();
			new Bank_Server_Thread(BankServerSocket.accept(), "BankServerThread2", OurSharedBankStateObject).start();
			new Bank_Server_Thread(BankServerSocket.accept(), "BankServerThread3", OurSharedBankStateObject).start();
			System.out.println("New " + BankServerName + " thread started.");
		}
		BankServerSocket.close();
	}
}