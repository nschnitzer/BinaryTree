//********************************************
// Nathan Schnitzer
// BinarySearchTree.java
// 3/20/19
// Represents a Binary Search Tree data structure
//********************************************

package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Queue.Queue;


public class BinarySearchTree<Type extends Comparable>
{
	TreeNode<Type> root;
	int height;

	public BinarySearchTree()
	{
		root = null;
		height = 0;
	}

	public BinarySearchTree(Type c)
	{
		root = new TreeNode<Type>(c, null, null);
		height = 1;
	}


	//Insert node
	public void insert(Type c)
	{
		if (root == null)
		{
			root = new TreeNode<Type>(c, null, null);
			height = 1;
			return;
		}

		if (root.getData().compareTo(c) > 0)
		{
			//Going to the left
			if (root.hasLeft())
			{
				insert(root.getLeft(), c);
				return;
			}

			root.setLeft(new TreeNode<Type>(c, null, null, root));
			return;
		}

		//Going to the right
		if (root.hasRight())
		{
			insert(root.getRight(), c);
			return;
		}

		root.setRight(new TreeNode<Type>(c, null, null, root));
		return;

	}

	private void insert(TreeNode<Type> node, Type c)
	{
		if (node.getData().compareTo(c) > 0)
		{
			//Going to the left
			if (node.hasLeft())
			{
				insert(node.getLeft(), c);
				return;
			}

			node.setLeft(new TreeNode<Type>(c, null, null, node));
			return;
		}

		//Going to the right
		if (node.hasRight())
		{
			insert(node.getRight(), c);
			return;
		}

		node.setRight(new TreeNode<Type>(c, null, null, node));
		return;
	}

	public void delete(Type c)
	{
		if (root.getData().equals(c))
		{
			TreeNode<Type> n = getDeepElement(root);
			if (n.hasRight() == false)
			{
				n.getParent().setLeft(null);
				root.getLeft().setParent(n);
				root.getRight().setParent(n);
				root = n;
				return;
			}
			else //Shit gets much more complicated
			{
				Queue<Type> children = getAllChildren(root.getRight());
			}

		}
	}

	private void delete(TreeNode<Type> node, Type c)
	{

	}

	//Gets left most element
	private TreeNode<Type> getDeepElement(TreeNode<Type> index)
	{
		if (index.hasChildren() == true)
		{
			return getDeepElement(index.getLeft());
		}

		return index;
	}

	private Queue<Type> getAllChildren(TreeNode<Type> node)
	{
		
		Queue<Type> que = new Queue<Type>();
		que.push(node.getData());
		if (node.hasLeft())
		{
			getAllChildren(node.getLeft(), que);
		}
		if (node.hasRight())
		{
			getAllChildren(node.getRight(), que);
		}

		return que;
	}

	private void getAllChildren(TreeNode<Type> node, Queue<Type> q)
	{
		q.push(node.getData());
		if (node.hasLeft())
		{
			getAllChildren(node.getLeft(), q);
		}
		if (node.hasRight())
		{
			getAllChildren(node.getRight(), q);
		}

		return;

	}



}
