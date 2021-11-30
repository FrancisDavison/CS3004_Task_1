import java.io.*;
import java.net.*;

public class Shared_Bank_State
{
	private Shared_Bank_State MySharedObj;
	private String MyThreadName;
	private boolean Accessing=false; //True if thread has lock, false otherwise
	private int ThreadsWaiting=0; //Number of waiting writers
	private double[] MyBalances;
	//Constructor
	Shared_Bank_State(double[] Balances)
	{
		MyBalances=Balances;
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
		String Money="";
		int length=TheInput.length()-1;
		
		//Add Money ========================================================================
		if(TheInput.charAt(0)=='A'||TheInput.charAt(0)=='a')
		{
			if(MyThreadName.equals("BankServerThread1")) //Client 1/A
			{
				if(TheInput.charAt(10)!='A'&&TheInput.charAt(10)!='a')
				{
					//Client does not own this account
				}
				else
				{
					for(int i=12;i<=length-1;i++)
					{
						Money+=TheInput.charAt(i);
					}
					double Money_Double = Double.parseDouble(Money);
					MyBalances[0]+=Money_Double;
					//Return Balance
				}
			}
			
			if(MyThreadName.equals("BankServerThread2")) //Client 2/B
			{
				if(TheInput.charAt(10)!='B'&&TheInput.charAt(10)!='b')
				{
					//Client does not own this account
				}
				else
				{
					for(int i=12;i<=length-1;i++)
					{
						Money+=TheInput.charAt(i);
					}
					double Money_Double = Double.parseDouble(Money);
					MyBalances[1]+=Money_Double;
					//Return Balance
				}
			}
			
			if(MyThreadName.equals("BankServerThread3")) //Client 3/C
			{
				if(TheInput.charAt(10)!='C'&&TheInput.charAt(10)!='c')
				{
					//Client does not own this account
				}
				else
				{
					for(int i=12;i<=length-1;i++)
					{
						Money+=TheInput.charAt(i);
					}
					double Money_Double = Double.parseDouble(Money);
					MyBalances[2]+=Money_Double;
					//Return Balance
				}
			}
			else
			{
				//Invalid Thread Name
			}
		}
		
		//Subtract Money ====================================================================
		if(TheInput.charAt(0)=='S'||TheInput.charAt(0)=='s')
		{
			if(MyThreadName.equals("BankServerThread1"))
			{
				if(TheInput.charAt(15)!='A'&&TheInput.charAt(15)!='a')
				{
					//Client does not own this account
				}
				else
				{
					for(int i=17;i<=length-1;i++)
					{
						Money+=TheInput.charAt(i);
					}
					double Money_Double = Double.parseDouble(Money);
					MyBalances[0]-=Money_Double;
				}
			}
			
			if(MyThreadName.equals("BankServerThread2"))
			{
				if(TheInput.charAt(15)!='B'&&TheInput.charAt(15)!='b')
				{
					//Client does not own this account
				}
				else
				{
					for(int i=17;i<=length-1;i++)
					{
						Money+=TheInput.charAt(i);
					}
					double Money_Double = Double.parseDouble(Money);
					MyBalances[1]-=Money_Double;
				}
			}
			if(MyThreadName.equals("BankServerThread3"))
			{
				if(TheInput.charAt(15)!='C'&&TheInput.charAt(15)!='c')
				{
					//Client does not own this account
				}
				else
				{
					for(int i=17;i<=length-1;i++)
					{
						Money+=TheInput.charAt(i);
					}
					double Money_Double = Double.parseDouble(Money);
					MyBalances[2]-=Money_Double;
				}
			}
			else
			{
				//Invalid Thread Name
			}
		}
		
		//Transfer Money =====================================================================
		if(TheInput.charAt(0)=='T'||TheInput.charAt(0)=='t')
		{
			if(MyThreadName.equals("BankServerThread1"))
			{
				if(TheInput.charAt(15)!='A'&&TheInput.charAt(15)!='a')
				{
					
				}
			}
		}
		
		
		
		
	
		else //Incorrect Request
		{
			TheOutput=MyThreadName+" recieved an incorrect Action";
		}
		//Return the output message to the BankServer
		System.out.println(TheOutput);
		return TheOutput;
	}
}