import java.util.Scanner;

class Sizes
{
	int size = 0;
	int block_alloc = 0;
	int index = 0;
}

public class Fitter
{
	
	static void WorstFit(int blocks[], Sizes sizes[], int n, int m)
	{
		int j,i;
		int rem_size[] = new int[n];
		for(i = 0 ; i < n; i++)
			rem_size[i] = blocks[i];
		int max_ind  = -1, max = -1;
		for(i = 0; i < m; i++)
		{			
			max = max_ind = -1;
			boolean flag = true;
			for(j = 0; j < n; j++)
			{
				if(sizes[i].size <= rem_size[j])
				{
					if( max < rem_size[j] - sizes[i].size || flag == true )
					{
						max = rem_size[j] - sizes[i].size;
						max_ind = j;
						flag = false;
					}
				}
			}
			if(!(max < 0))
			{
				rem_size[max_ind] = rem_size[max_ind] - sizes[i].size;
				sizes[i].block_alloc = blocks[max_ind];
				sizes[i].index = max_ind;

			}
		}

		System.out.print("\nProcess No.\tSizes\tBlock Size\tRemaining Size\n");
		for(i = 0; i < m; i++)
		{

			System.out.printf("%6d%13d%10d",i+1,sizes[i].size,sizes[i].block_alloc);
			if(sizes[i].block_alloc == 0)
				System.out.printf("\t\tNo Block Allocated\n");
			else
				System.out.printf("%15d\n",rem_size[sizes[i].index]);
		}
	}

	static void NextFit(int blocks[], Sizes sizes[], int n, int m)
	{
		int i,j,count = 0;
		int rem_size[] = new int[n];
		for(i = 0 ; i < n; i++ )
			rem_size[i] = blocks[i];

		for(j = 0; j < n; j++)
		{
			if(sizes[0].size <= rem_size[j])
			{
				rem_size[j] -= sizes[0].size;
				sizes[0].block_alloc = blocks[j];
				sizes[0].index = j;
				break;
			}
		}

		for(i = 1; i < m; i++)
		{
			for(count = 0,j = j + 1; count < n ;j++)
			{
				j = j % n;
				if(sizes[i].size <= rem_size[j])
				{
					rem_size[j] -= sizes[i].size;
					sizes[i].block_alloc = blocks[j];
					sizes[i].index = j;
					break;
				}
				
				count++;
			}
		}

		System.out.print("\nProcess No.\tSizes\tBlock Size\tRemaining Size\n");
		for(i = 0; i < m; i++)
		{

			System.out.printf("%6d%13d%10d",i+1,sizes[i].size,sizes[i].block_alloc);
			if(sizes[i].block_alloc == 0)
				System.out.printf("\t\tNo Block Allocated\n");
			else
				System.out.printf("%15d\n",rem_size[sizes[i].index]);
		}
	

	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Number of Blocks : ");
		int n = sc.nextInt();
		int blocks[] = new int[n];
		for(int i = 0; i < n; i++)
		{
			System.out.print("Enter " + (i + 1) + "th Block's Size : ");
			blocks[i] = sc.nextInt();
		}

		System.out.print("\nEnter the Number of Sizes : ");
		int m = sc.nextInt();
		Sizes sizes[] = new Sizes[m];
		for(int i = 0; i < m; i++)
			sizes[i] = new Sizes();

		for(int i = 0; i < m; i++)
		{
			System.out.print("Enter " + (i + 1) + "th Size : ");
			sizes[i].size = sc.nextInt();
		}
		int ch;
		do
		{
			System.out.println("\n---------MENU---------");
			System.out.println("1.Worst Fit");
			System.out.println("2.Next Fit");
			System.out.println("3.Exit");
			System.out.print("Enter your choice : ");
			ch = sc.nextInt();
			switch(ch)
			{
				case 1:
					WorstFit(blocks,sizes,n,m);
					break;
				case 2:
					NextFit(blocks,sizes,n,m);
					break;
				case 3:
					System.out.println("GG");
					break;
				default:
					System.out.println("Wrong choice entered!!");
					break;
			}
		}while(ch != 3);

	}

}
