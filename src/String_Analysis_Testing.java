import java.util.*;
public class String_Analysis_Testing
{
	public static void main(String args[])
	{
		double Balances[]={1000,1000,1000};
		String TheInput="Add_Money(b,100)";
		String Money="";
		int length=TheInput.length()-1;
		
		//Add Money
		if(TheInput.charAt(0)=='A'||TheInput.charAt(0)=='a')
		{
			//Account A, Client 1
			if(TheInput.charAt(10)=='A'||TheInput.charAt(10)=='a')
			{
				for(int i=12;i<=length-1;i++)
				{
					Money+=TheInput.charAt(i);
				}
				double Money_Double = Double.parseDouble(Money);
				Balances[0]+=Money_Double;
				//Return Balance
			}
			//Account B, Client 2
			if(TheInput.charAt(10)=='B'||TheInput.charAt(10)=='b')
			{
				for(int i=12;i<=length-1;i++)
				{
					Money+=TheInput.charAt(i);
				}
				double Money_Double = Double.parseDouble(Money);
				Balances[1]+=Money_Double;
				//Return Balance
			}
			//Account C, Client 3
			if(TheInput.charAt(10)=='C'||TheInput.charAt(10)=='c')
			{
				for(int i=12;i<=length-1;i++)
				{
					Money+=TheInput.charAt(i);
				}
				double Money_Double = Double.parseDouble(Money);
				Balances[2]+=Money_Double;
				//Return Balance
			}
			else
			{
				System.out.println("Invalid account selected");
			}
		}
		
		//Subtract Money
		if(TheInput.charAt(0)=='S'||TheInput.charAt(0)=='s')
		{ 
			//Account A, Client 1
			if(TheInput.charAt(15)=='A'||TheInput.charAt(15)=='a')
			{
				for(int i=17;i<=length-1;i++)
				{
					Money+=TheInput.charAt(i);
				}
				double Money_Double = Double.parseDouble(Money);
				Balances[0]-=Money_Double;
			}
			//Account B, Client 2
			if(TheInput.charAt(15)=='B'||TheInput.charAt(15)=='b')
			{
				for(int i=17;i<=length-1;i++)
				{
					Money+=TheInput.charAt(i);
				}
				double Money_Double = Double.parseDouble(Money);
				Balances[1]-=Money_Double;
			}
			//Account C, Client 3
			if(TheInput.charAt(15)=='C'||TheInput.charAt(15)=='c')
			{
				for(int i=17;i<=length-1;i++)
				{
					Money+=TheInput.charAt(i);
				}
				double Money_Double = Double.parseDouble(Money);
				Balances[2]-=Money_Double;
			}
			else
			{
				System.out.println("Invalid account selected");
			}
		}
		
		if(TheInput.charAt(0)=='T'||TheInput.charAt(0)=='t')
		{
			if(TheInput.charAt(15)=='A'||TheInput.charAt(15)=='a')
			{
				if(TheInput.charAt(17)=='B'||TheInput.charAt(17)=='b')
				{
					for(int i=19;i<=length-1;i++)
					{
						Money+=TheInput.charAt(i);
					}
					double Money_Double = Double.parseDouble(Money);
					Balances[0]-=Money_Double;
					Balances[1]+=Money_Double;
				}
			}
		}
		else
		{
			System.out.println("Invalid action selected");
		}
	}
}