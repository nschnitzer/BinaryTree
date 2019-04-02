//**************************************************************************
// Nathan Schnitzer
// Queue.java
// A long time ago
// PLEASE NOTE: THIS QUEUE CLASS IS CUSTOMIZED FOR TREES!!! THIS IS NOT THE QUEUE CLASS TO BE GRADED (seperate project files)
//**************************************************************************

package QueueForTrees;

public class Queue<Type> 
{
	Node2<Type> first = null;
	Node2<Type> lastAdded = null;
	private int size;
	
	public Queue()
	{
		size = 0;
	}
	
	public void push(Type obj)
	{
		if (size == 0)
		{
			first = new Node2<Type>(null, obj);
			lastAdded = first;
			size++;
			return;
		}
		
		lastAdded.setNextNode(new Node2<Type>(null, obj));
		lastAdded = lastAdded.getNextNode();
		size++;
	}
	
	public Type pop()
	{
		Type type = first.getValue();
		first = first.getNextNode();
		size--;
		return type;
	}
	
	public Type peek()
	{
		return first.getValue();
	}
	
	public void printQueue()
	{
		Node2<Type> node = first;
		while (node.getNextNode() != null)
		{
			System.out.println(node.getValue());
			node = node.getNextNode();
		}
		System.out.println(node.getValue());
	}
	
	//Prints the Queue in one line
	public void printQueueOneLine()
	{
		Node2<Type> node = first;
		if (node == null)
			return;
		while (node.getNextNode() != null)
		{
			System.out.print(node.getValue() + " ");
			node = node.getNextNode();
		}
		System.out.println(node.getValue());
	}
	
	public int getLength()
	{
		int counter = 1;
		Node2<Type> node = first;
		while (node.getNextNode() != null)
		{
			counter++;
			node = node.getNextNode();
		}
		
		return counter;
	}
	
	public boolean isEmpty()
	{
		if (size == 0)
		{
			return true;
		}
		return false;
	}

}
