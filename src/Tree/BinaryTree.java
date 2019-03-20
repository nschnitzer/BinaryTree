//******************************************************
// Nathan Schnitzer
// BinaryTree.java
// 2/28/19
// Represents a binary tree
//******************************************************


package Tree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Queue.Queue;

public class BinaryTree<T extends Comparable>
{
	TreeNode<T> root, lowestIndex;
	int height;

	public BinaryTree()
	{
		root = null;
		lowestIndex = null;
		height = 0;
	}

	public BinaryTree(TreeNode<T> r)
	{
		root = r;
		lowestIndex = root;
		determineHeight();
	}

	//Alternate Method To Consider: Only go down rightmost path since it must be filled
	public void determineHeight()
	{
		height = heightHelper(root);
	}

	public int heightHelper(TreeNode<T> node)
	{
		if (node == null)
		{
			return 0;
		}

		return Integer.max(heightHelper(node.getLeft()), heightHelper(node.getRight())) + 1;
	}

	public int getHeight()
	{
		return height;
	}

	//Check right first
	//Fill up tree-- NOT BINARY SEARCH TREE!!!!
	public void insert(T n)
	{
		if (root == null)
		{
			root = new TreeNode<T>(n, null, null);
			lowestIndex = root;
			determineHeight();
			return;
		}
		TreeNode<T> currentLoc = root;
		//Traverse to the height - 1 level at right most node
		for (int i = 1; i < height-1; i++)
		{
			currentLoc = currentLoc.getRight();
			System.out.println(currentLoc.getData());
		}

		//check and see if height level is full

		if (currentLoc.hasRight())
		{
			//System.out.println("ssss");
			//Create new node on the left on next level
			currentLoc = root;
			for (int i = 1; i < height; i++)
			{
				currentLoc = currentLoc.getLeft();
			}
			currentLoc.setLeft(new TreeNode<T>(n, null, null));
			lowestIndex = currentLoc.getLeft();
			determineHeight();
			return;
		}

		//Shit's getting real now
		//Check if the left child is full, if it is then you can just fill it up and not have to do anything fancy
		if (currentLoc.hasLeft())
		{
			currentLoc.setRight(new TreeNode<T>(n, null, null));
			lowestIndex = currentLoc.getRight();
			determineHeight();
			return;
		}

		//Gotta check the other nodes on the level now....
		//Check if the lowest index is the root
		if (currentLoc == root)
		{
			//System.out.println("TR");
			if (currentLoc.hasLeft())
			{
				currentLoc.setRight(new TreeNode<T>(n, null, null, root));
				lowestIndex = currentLoc.getRight();
				return;
			}
			currentLoc.setLeft(new TreeNode<T>(n, null, null, root));
			lowestIndex = currentLoc.getLeft();
			return;
		}

		if (currentLoc.hasLeft() && currentLoc.hasRight())
		{
			if (currentLoc.equals(currentLoc.getParent().getRight()))
			{
				currentLoc = currentLoc.getParent().getParent().getLeft();
				currentLoc.setLeft(new TreeNode<T>(n, null, null, currentLoc));
				lowestIndex = currentLoc.getLeft();
				return;
			}
			currentLoc = currentLoc.getParent().getRight();
			currentLoc.setLeft(new TreeNode<T>(n, null,null, currentLoc));
			lowestIndex = currentLoc.getLeft();
			return;
		}

		currentLoc.setRight(new TreeNode<T>(n, null, null, currentLoc));
		lowestIndex = currentLoc.getRight();

	}

	//REDESIGN
	public void insert2(T t)
	{
		if (root == null)
		{
			root = new TreeNode<T>(t, null, null);
			lowestIndex = root;
			determineHeight();
			return;
		}


		TreeNode<T> index = root;
		while (index.hasRight())
		{
			index = index.getRight();
			
		}
		if (index.equals(root) == false)
			index = index.getParent();
		//System.out.println("INCOMING ALERT: " + index.getData());
		if (index.hasLeft() && index.hasRight() == false)
		{
			index.setRight(new TreeNode<T>(t, null, null, index));
			return;
		}
		
		if (index.hasChildren() == false)
		{
			index.setLeft(new TreeNode<T>(t, null,null, index));
			return;
		}
		System.out.println("index " + index.getData());
		if (index.hasLeft() && index.hasRight())
		{
			if (index.equals(root))
			{
			System.out.println("INDEX " + index.getData());
			insert2(index.getLeft(), t);
			return;
			}
			else
			{
				if (getDeepParent(root).hasLeft() && getDeepParent(root).hasRight())
				{
				getDeepParent(root).getLeft().setLeft(new TreeNode<T>(t, null, null, getDeepParent(root).getLeft()));
				determineHeight();
				return;
				}
				index = getDeepParent(root);
				System.out.println("INCOMING ALERTSSS: " + index.getData());
				insert2(index, t);
			}
			/*
			System.out.println(index.getData() + " DFDFD");
			index = getDeepParent(root).getLeft();
			System.out.println("INCOMING: " + index.getData());
			
			index.setLeft(new TreeNode<T>(t, null, null, index));
			determineHeight();
			return;
			*/
		}

	}

	private void insert2(TreeNode<T> index, T t)
	{
		if (index.hasLeft() && index.hasRight() == false)
		{
			index.setRight(new TreeNode<T>(t, null, null, index));
			return;
		}
		if (index.hasLeft() == false)
		{
			index.setLeft(new TreeNode<T>(t, null, null, index));
			return;
		}
		
		//Check if this is the right node of the parent
		if (index.equals(index.getParent().getRight()))
		{
			insert2(index.getParent().getParent(), t);
			return;
		}
		else
		{
			insert2(index.getParent().getRight(), t);
			return;
		}
		
	}

	//Gets parent of left most element
	public TreeNode<T> getDeepParent(TreeNode<T> index)
	{
		if (index.hasChildren() == true)
		{
			return getDeepParent(index.getLeft());
		}

		return index.getParent();
	}

	public void BreadthFirstSearch()
	{
		TreeNode<T> node = root;
		Queue<T> que = new Queue<T>();
		que.push(node.getData());
		if (node.hasLeft())
		{
			BreadthFirstSearch(node.getLeft(), que);
		}
		if (node.hasRight())
		{
			BreadthFirstSearch(node.getRight(), que);
		}

		System.out.println("OUT:");
		que.printQueue();

	}

	private void BreadthFirstSearch(TreeNode<T> node, Queue<T> q)
	{
		q.push(node.getData());
		if (node.hasLeft())
		{
			BreadthFirstSearch(node.getLeft(), q);
		}
		if (node.hasRight())
		{
			BreadthFirstSearch(node.getRight(), q);
		}

		return;

	}

	public void getGivenLength(int level)
	{
		Queue<T> q = new Queue<T>();
		if (level == 1)
		{
			q.push(root.getData());
		}
		else
		{
			getGivenLength(root, 1, level, q);
		}
		q.printQueue();
	}

	private void getGivenLength(TreeNode<T> node, int l, int target, Queue<T> q)
	{
		if (l == target-1)
		{
			q.push(node.getLeft().getData());
			q.push(node.getRight().getData());
			return;
		}

		getGivenLength(node.getLeft(), l+1, target, q);
		getGivenLength(node.getRight(), l+1, target, q);
	}

	public void printPreOrder()
	{
		TreeNode<T> node = root;
		Queue<T> que = new Queue<T>();

		que.push(node.getData());

		if (node.hasLeft())
		{
			printPreOrder(node.getLeft(), que);
		}

		if (node.hasRight())
		{
			printPreOrder(node.getRight(), que);
		}

		que.printQueue();

	}

	private void printPreOrder(TreeNode<T> node, Queue<T> que)
	{
		que.push(node.getData());
		if (node.hasLeft())
		{
			printPreOrder(node.getLeft(), que);
		}
		if (node.hasRight())
		{
			printPreOrder(node.getRight(), que);
		}
		return;
	}

	public void printPostOrder()
	{
		TreeNode<T> node = root;
		Queue<T> que = new Queue<T>();

		if (node.hasLeft())
		{
			printPostOrder(node.getLeft(), que);
		}

		if (node.hasRight())
		{
			printPostOrder(node.getRight(), que);
		}

		que.push(node.getData());

		que.printQueue();
	}

	private void printPostOrder(TreeNode<T> node, Queue<T> que)
	{
		if (node.hasLeft())
		{
			printPostOrder(node.getLeft(), que);
		}

		if (node.hasRight())
		{
			printPostOrder(node.getRight(), que);
		}

		que.push(node.getData());
	}

}
