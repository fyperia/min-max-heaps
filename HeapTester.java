import java.util.Scanner;

public class HeapTester
{

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Choose 1 for MinHeap or -1 for MaxHeap.");
		System.out.print("Enter choice: ");
		MinHeap<Integer> heap = new MinHeap<Integer>(in.nextInt());

		while (true)
		{
			System.out.println(" 1. Add");
			System.out.println(" 2. Remove");
			System.out.println(" 3. Keys Greater Than");
			System.out.println(" 4. Change Priority");
			System.out.println(" 9. Print");
			System.out.println("10. Pretty Print");
			System.out.println("99. Exit");

			switch (in.nextInt())
			{
			case 1: System.out.print("Please enter a value: ");
					heap.add(in.nextInt());
					break;
			case 2: System.out.println("Value: " + heap.remove());
					break;
			case 3: System.out.print("Please enter a value: ");
					System.out.println(heap.keysGreaterThan(in.nextInt()));
					break;
			case 4: System.out.print("Please enter index followed by the new priority: ");
					heap.changePriority(in.nextInt(), in.nextInt());
					break;
			case 9: System.out.println(heap);
					break;
			case 10:heap.prettierPrint();
					break;
			case 99:in.close();
					System.exit(0);
			default:System.out.println("Yo. Please enter a valid choice");
			}
			System.out.println();

		}

	}

}