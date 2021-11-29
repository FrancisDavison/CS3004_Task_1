import java.io.*;
import java.net.*;

public class Shared_Bank_State
{
	private Shared_Bank_State MySharedObj;
	private String MyThreadName;
	private double MySharedVariable;
	private boolean Accessing=false; //True if thread has lock, false otherwise
	private int ThreadsWaiting=0; //Number of waiting writers
	
	//Constructor4
	Shared_Bank_State(double[] SharedVariable)
	{
		double[] MySharedVariable=SharedVariable;
	}
	
	//Attempt to acquire lock
	public synchronized void AcquireLock() throws InterruptedException
	{
		Thread me=Thread.currentThread();
		System.out.println(me.getName()+" is attempting to acquire a lock");
		++ThreadsWaiting;
		while(Accessing) //While someone else is accessing or threadswaiting>0
		{
			System.out.println(me.getName()+ "waiting to get a lock, someone else is accessing...");
			//Wait for the lock to be released = see ReleaseLock() below
			wait();
		}
		//nobody has got a lock so get one
		--ThreadsWaiting;
		Accessing=true;
		System.out.println(me.getName()+" got a lock");
	}
	
	//Releases a lock when a thread is finished
	public synchronized void ReleaseLock()
	{
		//Release the lock and tell everyone
		Accessing=false;
		notifyAll();
		Thread me=Thread.currentThread();
		System.out.println(me.getName()+" released a lock");
	}
	
	//The ProcessInput method
	public synchronized String ProcessInput(String MyThreadName, String TheInput)
	{
		System.out.println(MyThreadName+" recieved "+TheInput);
		String TheOutput=null;
		//Check what client said
		if(TheInput.equalsIgnoreCase("Do my action"))
		{
			//Correct Request
			if(MyThreadName.equals("BankServerThread1"))
			{
				//Here do all the stuff like deposit, withdraw, transfer, etc
				//Add 20, Multiply by 5, Divide by 3, Just for example
				MySharedVariable+=20;
				MySharedVariable*=5;
				MySharedVariable/=3;
				System.out.println(MyThreadName+" made the Shared Variable "+MySharedVariable);
				TheOutput="Do action completed. Shared Variable now="+MySharedVariable;
			}
			
			else if(MyThreadName.equals("BankServerThread2"))
			{
				//Here do all the stuff like deposit, withdraw, transfer, etc
				//Subtract 5, Multiply by 10, Divide by 2.5
				MySharedVariable-=5;
				MySharedVariable*=10;
				MySharedVariable/=2.5;
				System.out.println(MyThreadName+" made the Shared Variable "+MySharedVariable);
				TheOutput="Do action completed. Shared Variable now="+MySharedVariable;
			}
			
			else if(MyThreadName.equals("BankServerThread3"))
			{
				//Here do all the stuff like deposit, withdraw, transfer, etc
				//Subtract 50, Divide by 2, Multiply by 33
				MySharedVariable-=50;
				MySharedVariable/=2;
				MySharedVariable*=33;
				System.out.println(MyThreadName+" made the Shared Variable "+MySharedVariable);
				TheOutput="Do action completed. Shared Variable now="+MySharedVariable;
			}
			else //Incorrect Thread call
			{
				System.out.println("Error - Thread call not recognised");
			}
		}
		
		else //Incorrect Request
		{
			TheOutput=MyThreadName+" recieved and incorrect request - only understand \"Do my action!\"";
		}
		//Return the output message to the BankServer
		System.out.println(TheOutput);
		return TheOutput;
	}
}