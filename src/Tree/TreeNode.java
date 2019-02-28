package Tree;
//****************************************************************
// Nathan Schnitzer
// TreeNode.java
// 2/28/19
// Holds the data of a node in a BinaryTree
//****************************************************************

public class TreeNode<T>
{
	T data;
	T left, right;
	
	//Default Constructor
	public TreeNode()
	{
		data = null;
		left = null;
		right = null;
	}
	
	//Constructor
	public TreeNode(T t, T l, T r)
	{
		data = t;
		left = l;
		right = r;
	}
	
	public T getData()
	{
		return data;
	}
	
	public T getLeft()
	{
		return left;
	}
	
	public T getRight()
	{
		return right;
	}
	
	public void setLeft(T l)
	{
		left = l;
	}
	
	public void setRight(T r)
	{
		right = r;
	}
	
	public void setData(T t)
	{
		data = t;
	}

}
