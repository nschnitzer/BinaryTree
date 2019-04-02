package Tree;
//****************************************************************
// Nathan Schnitzer
// BTreeNode.java
// 2/28/19
// Holds the data of a node in a BinaryTree
//****************************************************************

public class BTreeNode<Type>
{
	Type data;
	BTreeNode<Type> left, right, parent;
	
	//Default Constructor
	public BTreeNode()
	{
		data = null;
		left = null;
		right = null;
		parent = null;
	}
	
	//Constructor
	public BTreeNode(Type t, BTreeNode<Type> l, BTreeNode<Type> r, BTreeNode<Type> p)
	{
		data = t;
		left = l;
		right = r;
		parent = p;
	}
	
	//For Roots
	public BTreeNode(Type t, BTreeNode<Type> l, BTreeNode<Type> r)
	{
		data = t;
		left = l;
		right = r;
	}
	
	public Type getData()
	{
		return data;
	}
	
	public BTreeNode<Type> getLeft()
	{
		return left;
	}
	
	public BTreeNode<Type> getRight()
	{
		return right;
	}
	
	public void setLeft(BTreeNode<Type> l)
	{
		left = l;
	}
	
	public void setRight(BTreeNode<Type> r)
	{
		right = r;
	}
	
	public void setParent(BTreeNode<Type> p)
	{
		parent = p;
	}
	
	public void setData(Type t)
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
	
	public BTreeNode<Type> getParent()
	{
		return parent;
	}

}
