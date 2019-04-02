//******************************************************
// Nathan Schnitzer
// BinaryTree.java
// 2/28/19
// Represents a binary tree
//******************************************************


package Tree;

import QueueForTrees.Queue;

public class BinaryTree<T extends Comparable<T>>
{
	BTreeNode<T> root, lowestIndex;
	int height;

	public BinaryTree()
	{
		root = null;
		lowestIndex = null;
		height = 0;
	}

	public BinaryTree(BTreeNode<T> r)
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

	public int heightHelper(BTreeNode<T> node)
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

	//REDESIGN without recursion
	//Inserts element into the tree
	public void insert(T t)
	{
		if (root == null)
		{
			root = new BTreeNode<T>(t, null, null);
			lowestIndex = root;
			determineHeight();
			return;
		}

		Queue<BTreeNode<T>> que = new Queue<BTreeNode<T>>();
		que.push(root);

		while (que.isEmpty() == false)
		{
			BTreeNode<T> node = que.pop();

			if (node.hasLeft() == false)
			{
				node.setLeft(new BTreeNode<T>(t, null, null, node));
				break;
			}
			else
			{
				que.push(node.getLeft());
			}
			if (node.hasRight() == false)
			{
				node.setRight(new BTreeNode<T>(t, null, null, node));
				break;
			}
			else
			{
				que.push(node.getRight());
			}
		}
		determineHeight();

	}


	//Gets parent of left most element
	public BTreeNode<T> getDeepParent(BTreeNode<T> index)
	{
		if (index.hasChildren() == true)
		{
			return getDeepParent(index.getLeft());
		}

		return index.getParent();
	}

	//Breadth First Traversal w/o Queue
	public void printBreadthFirst()
	{
		int i = 1;
		while (i <= getHeight())
		{
			printBreadthFirst(i);
			i++;
		}
		System.out.println();
	}
	
	private void printBreadthFirst(int i)
	{
		if (i == 1)
		{
			System.out.print(root.getData() + " ");
		}
		else
		{
			printBreadthFirst(root, 1, i);
		}
	}
	
	private void printBreadthFirst(BTreeNode<T> node, int l, int target)
	{
		if (l == target-1)
		{
			if (node.hasLeft())
				System.out.print(node.getLeft().getData() + " ");
			if (node.hasRight())
				System.out.print(node.getRight().getData() + " ");
			return;
		}

		printBreadthFirst(node.getLeft(), l+1, target);
		printBreadthFirst(node.getRight(), l+1, target);
	}
	
	//Breadth First Traversal w/ Queue
	public void printBreadthFirstQueue()
	{
		Queue<T> q = new Queue<T>();
		
		for (int i = 1; i <= getHeight(); i++)
		{
			Queue<T> que = getGivenLengthQueue(i);
			while (que.isEmpty() == false)
			{
				q.push(que.pop());
			}
		}
		
		q.printQueueOneLine();
		
	}

	public Queue<T> getGivenLengthQueue(int level)
	{
		Queue<T> que = new Queue<T>();
		if (level == 1)
		{
			que.push(root.getData());
		}
		else
		{
			getGivenLength(root, 1, level, que);
		}
		
		return que;
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
		q.printQueueOneLine();
	}

	private void getGivenLength(BTreeNode<T> node, int l, int target, Queue<T> q)
	{
		if (l == target-1)
		{
			if (node.hasLeft())
				q.push(node.getLeft().getData());
			if (node.hasRight())
				q.push(node.getRight().getData());
			return;
		}

		getGivenLength(node.getLeft(), l+1, target, q);
		getGivenLength(node.getRight(), l+1, target, q);
	}

	public void printPreOrder()
	{
		BTreeNode<T> node = root;
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

		que.printQueueOneLine();

	}

	private void printPreOrder(BTreeNode<T> node, Queue<T> que)
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
		BTreeNode<T> node = root;
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

		que.printQueueOneLine();
	}

	private void printPostOrder(BTreeNode<T> node, Queue<T> que)
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
	
	//Print InOrder
	public void printInOrder()
	{
		BTreeNode<T> node = root;
		Queue<T> que = new Queue<T>();
		
		if (node.hasLeft())
		{
			printInOrder(node.getLeft(), que);
		}
		
		que.push(node.getData());
		
		if (node.hasRight())
		{
			printInOrder(node.getRight(), que);
		}
		
		que.printQueueOneLine();
	}
	
	private void printInOrder(BTreeNode<T> node, Queue<T> que)
	{
		if (node.hasLeft())
		{
			printInOrder(node.getLeft(), que);
		}
		
		que.push(node.getData());
		
		if (node.hasRight())
		{
			printInOrder(node.getRight(), que);
		}
		
		return;
	}
	
	public BinarySearchTree<T> toBST()
	{
		Queue<T> elements = inOrder();
		
		BinarySearchTree<T> bst = new BinarySearchTree<T>();
		while (elements.isEmpty() == false)
		{
			bst.insert(elements.pop());
		}
		
		return bst;
		
	}
	
	public Queue<T> inOrder()
	{
		BTreeNode<T> node = root;
		Queue<T> que = new Queue<T>();
		
		if (node.hasLeft())
		{
			inOrder(node.getLeft(), que);
		}
		
		que.push(node.getData());
		
		if (node.hasRight())
		{
			inOrder(node.getRight(), que);
		}
		
		return que;
	}
	
	private void inOrder(BTreeNode<T> node, Queue<T> que)
	{
		if (node.hasLeft())
		{
			inOrder(node.getLeft(), que);
		}
		
		que.push(node.getData());
		
		if (node.hasRight())
		{
			inOrder(node.getRight(), que);
		}
		
		return;
	}

}
