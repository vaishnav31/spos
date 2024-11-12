import java.util.Scanner;
public class testJni
{
	static
	{
		System.loadLibrary("native");
	}
	public static void main(String args[])
	{
    Scanner sc = new Scanner(System.in);
		int a, b;
    System.out.print("Enter First No. : ");
    a = sc.nextInt();
    System.out.print("Enter Second No. : ");
    b = sc.nextInt();
		System.out.println("Addition is "+ new testJni().add(a,b));
		
	}
	private native int add(int n1,int n2);
}