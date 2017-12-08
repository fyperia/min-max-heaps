import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>>
{

	private ArrayList<T> elements;
	private int type;

	public MinHeap(int type)
	{
		this.type = type;
		if (type != 1 && type != -1)
			type = 1;
		elements = new ArrayList<T>();
		elements.add(null);
	}

	public void add(T element)
	{
		elements.add(null);
		int index = elements.size() - 1;
		while (index > 1 && type * getParent(index).compareTo(element) > 0)
		{
			elements.set(index, getParent(index));
			index = getParentIndex(index);
		}
		elements.set(index, element);

	}

	public T remove()
	{
		if (elements.size() < 2)
			return null;

		T result = elements.get(1);

		if (elements.size() > 1)
			fixHeap(elements.remove(elements.size() - 1));
		return result;
	}
	
	public void changePriority(int index, T newElement)
	{
		if (type * elements.get(index).compareTo(newElement) > 0)
			trickleUp(index, newElement);
		else if (type * elements.get(index).compareTo(newElement) < 0)
			trickleDown(index, newElement);
	}
	
	private void fixHeap(T element)
	{
		trickleDown(1, element);
	}

	private void trickleDown(int index, T element)
	{
		int ptr = index;
		int next = index;

		while (getLeftIndex(ptr) < elements.size())
		{
			// check if no children^
			// check if left child; else right child
			
			if (type * element.compareTo(elements.get(next)) < 0)
				break;

			if (getRightIndex(ptr) >= elements.size()
					|| type * getRightChild(ptr).compareTo(getLeftChild(ptr)) > 0)
			{
				if (type * element.compareTo(getLeftChild(ptr)) > 0)
				{
					next = getLeftIndex(ptr);
					System.out.println("Next: " + elements.get(next));
					elements.set(ptr, elements.get(next));
					System.out.println("Line 74: Set next at index " + ptr);
				}
			} else //if (type * getLeftChild(ptr).compareTo(getRightChild(ptr)) >= 0)
			{
				if (type * element.compareTo(getRightChild(ptr)) > 0)
				{
					next = getRightIndex(ptr);
					System.out.println("Next: " + elements.get(next));
					elements.set(ptr, elements.get(next));
					System.out.println("Line 83: Set next at index " + ptr);
				}
			}
			
			ptr = next;
			
		}
		if (elements.size() > 1)
			elements.set(ptr, element);

	}
	
	private void trickleUp(int index, T newPriority)
	{
		int ptr = index, next = index;
		while (ptr > 1 && type * getParent(ptr).compareTo(newPriority) > 0)
		{
			ptr = next;
			elements.set(ptr, getParent(ptr));
			next = getParentIndex(ptr);
			if (type * getParent(ptr).compareTo(getLeftChild(getParentIndex(ptr))) < 0 &&
					(ptr < elements.size() - 1 && type * getParent(ptr).compareTo(getRightChild(getParentIndex(ptr))) < 0))
				trickleDown(getParentIndex(ptr), getParent(ptr));
		}
		elements.set(ptr, newPriority);
			
	}

	public String keysGreaterThan(T element)
	{
		return keysGreaterThan(element, 1);
	}
	
	private String keysGreaterThan(T element, int ptr)
	{
		if (getLeftIndex(ptr) >= elements.size())
			if (elements.get(ptr).compareTo(element) >= 0)
				return elements.get(ptr).toString();
			else return "";
		if (getLeftIndex(ptr) == elements.size() - 1)
			if (getLeftChild(ptr).compareTo(element) >= 0)
				if (elements.get(ptr).compareTo(element) >= 0)
					return getLeftChild(ptr).toString() + " " + elements.get(ptr).toString();
				else return getLeftChild(ptr).toString();
			else return "";
		else
			if (elements.get(ptr).compareTo(element) >= 0)
				return keysGreaterThan(element, getLeftIndex(ptr)) + " " + elements.get(ptr).toString() +
						" " + keysGreaterThan(element, getRightIndex(ptr));
			else return keysGreaterThan(element, getLeftIndex(ptr)) + " " + keysGreaterThan(element, getRightIndex(ptr));
	}


	private T getParent(int index)
	{
		return elements.get(index / 2);
	}

	private int getParentIndex(int index)
	{
		return index / 2;
	}

	private int getLeftIndex(int index)
	{
		return index * 2;
	}

	private T getLeftChild(int index)
	{
		return elements.get(getLeftIndex(index));
	}

	private int getRightIndex(int index)
	{
		return index * 2 + 1;
	}

	private T getRightChild(int index)
	{
		return elements.get(getRightIndex(index));
	}

	public void prettierPrint()
	{
		System.out.print("heapArray: "); // array format
		for (int m = 1; m < elements.size(); m++)
			if (elements.get(m) != null)
				System.out.print(elements.get(m) + " ");
			else
				System.out.print("-- ");
		System.out.println();
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 1; // current item
		String dots = "...............................";
		System.out.println(dots + dots); // dotted top line
		while (elements.size() > 1) // for each heap item
		{
			if (column == 0) // first item in row?
				for (int k = 0; k < nBlanks; k++)
					// preceding blanks
					System.out.print(" ");
			// display item
			System.out.print(elements.get(j));

			if (++j == elements.size()) // done?
				break;
			if (++column == itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			} else
				// next item on row
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(" "); // interim blanks
		} // end for
		System.out.println("\n" + dots + dots);
	}

	public String toString()
	{
		return elements.toString();
	}
}
