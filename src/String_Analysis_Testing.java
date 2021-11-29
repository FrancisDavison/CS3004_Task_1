public class String_Analysis_Testing
{
	public static void main(String args[])
	{
		String Input="Add_Money(A,100)";
		int length=Input.length()-1;
		System.out.println(length);
		if(Input.charAt(0)=='A'||Input.charAt(0)=='a')
		{
			System.out.println("Add money");
			if(Input.charAt(10)=='A'||Input.charAt(10)=='a')
			{
				System.out.println("Account A");
			}
		}
	}
}