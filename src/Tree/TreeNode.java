package Tree;
//****************************************************************
// Nathan Schnitzer
// TreeNode.java
// 2/28/19
// Holds the data of a node in a BinaryTree
//****************************************************************

public class TreeNode<Type extends Comparable>
{
	Type data;
	TreeNode<Type> left, right, parent;
	
	//Default Constructor
	public TreeNode()
	{
		data = null;
		left = null;
		right = null;
		parent = null;
	}
	
	//Constructor
	public TreeNode(Type t, TreeNode<Type> l, TreeNode<Type> r, TreeNode<Type> p)
	{
		data = t;
		left = l;
		right = r;
		parent = p;
	}
	
	//For Roots
	public TreeNode(Type t, TreeNode<Type> l, TreeNode<Type> r)
	{
		data = t;
		left = l;
		right = r;
	}
	
	public Type getData()
	{
		return data;
	}
	
	public TreeNode<Type> getLeft()
	{
		return left;
	}
	
	public TreeNode<Type> getRight()
	{
		return right;
	}
	
	public void setLeft(TreeNode<Type> l)
	{
		left = l;
	}
	
	public void setRight(TreeNode<Type> r)
	{
		right = r;
	}
	
	public void setParent(TreeNode<Type> p)
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
	
	public TreeNode<Type> getParent()
	{
		return parent;
	}

}
