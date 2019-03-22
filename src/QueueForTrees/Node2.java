package QueueForTrees;
//*************************************
// Nathan Schnitzer
// Node.java
//
// PLEASE NOTE: THIS IS ONLY TO BE USED FOR TREES!! THIS IS NOT THE NODE CLASS TO BE GRADED
//*************************************

public class Node2<Type> 
{
	private Node2<Type> next;
	private Type value;
	
	public Node2(Node2<Type> nextNode, Type val)
	{
		next = nextNode;
		value = val;
	}
	
	public Node2<Type> getNextNode()
	{
		return next;
	}
	
	public void setNextNode(Node2<Type> newNode)
	{
		next = newNode;
	}
	
	public Type getValue()
	{
		return value;
	}

}
