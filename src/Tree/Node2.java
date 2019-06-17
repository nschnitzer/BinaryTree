package Tree;
//*************************************
// Nathan Schnitzer
// Node.java
//
// PLEASE NOTE: THIS IS ONLY TO BE USED FOR TREES!! THIS IS NOT THE NODE CLASS TO BE GRADED
//*************************************

public class Node2<T> 
{
	private Node2<T> next;
	private T value;
	
	public Node2(Node2<T> nextNode, T val)
	{
		next = nextNode;
		value = val;
	}
	
	public Node2<T> getNextNode()
	{
		return next;
	}
	
	public void setNextNode(Node2<T> newNode)
	{
		next = newNode;
	}
	
	public T getValue()
	{
		return value;
	}

}
