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
	TreeNode<T> left, right, parent;
	
	//Default Constructor
	public TreeNode()
	{
		data = null;
		left = null;
		right = null;
		parent = null;
	}
	
	//Constructor
	public TreeNode(T t, TreeNode<T> l, TreeNode<T> r, TreeNode<T> p)
	{
		data = t;
		left = l;
		right = r;
		parent = p;
	}
	
	//For Roots
	public TreeNode(T t, TreeNode<T> l, TreeNode<T> r)
	{
		data = t;
		left = l;
		right = r;
	}
	
	public T getData()
	{
		return data;
	}
	
	public TreeNode<T> getLeft()
	{
		return left;
	}
	
	public TreeNode<T> getRight()
	{
		return right;
	}
	
	public void setLeft(TreeNode<T> l)
	{
		left = l;
	}
	
	public void setRight(TreeNode<T> r)
	{
		right = r;
	}
	
	public void setData(T t)
	{
		data = t;
	}
	
	public boolean hasLeft()
	{
		return left != null;
	}
	
	public boolean hasRight()
	{
		return right != null;
	}
	
	public boolean hasChildren()
	{
		if (hasRight() || hasLeft())
		{
			return true;
		}
		return false;
	}
	
	public TreeNode<T> getParent()
	{
		return parent;
	}

}
