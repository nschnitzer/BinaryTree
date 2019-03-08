//******************************************************
// Nathan Schnitzer
// BinaryTree.java
// 2/28/19
// Represents a binary tree
//******************************************************


package Tree;
import java.lang.Thread;
import Queue.Queue;

public class BinaryTree<T>
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
		for (int i = 1; i < height; i++)
		{
			currentLoc = currentLoc.getRight();
		}
		
		//check and see if height level is full
		if (currentLoc.hasRight())
		{
			//Create new node on the left on next level
			currentLoc = root;
			for (int i = 0; i < height; i++)
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
			System.out.println("TR");
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
		currentLoc = lowestIndex.getParent();
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
		if (!(node.hasChildren()))
		{
			que.printQueue();
			return;
		}
		if (node.hasRight() == false)
		{
			System.out.println("R");
			Runnable runner = () ->
			{
				BreadthFirstSearch(node.getLeft(), que);
			};
			
			Thread thread = new Thread(runner);
			thread.start();
		}
		
		Runnable runner = () ->
		{
			BreadthFirstSearch(node.getLeft(), que);
			BreadthFirstSearch(node.getRight(), que);
		};
		
		Thread thread = new Thread(runner);
		thread.start();
		que.printQueue();
	}
	
	public void BreadthFirstSearch(TreeNode<T> node, Queue<T> q)
	{
		//System.out.println(node.getData());
		q.push(node.getData());
		if (!(node.hasChildren()))
		{
			System.out.println("q");
			return;
		}
		
		if (node.hasRight() == false)
		{
			System.out.println("r");
			Runnable runner = () ->
			{
				BreadthFirstSearch(node.getLeft(), q);
			};
			
			Thread thread = new Thread(runner);
			thread.start();
			notify();
			return;
		}
		
		Runnable runner = () ->
		{
			BreadthFirstSearch(node.getLeft(), q);
			
		};
		Runnable runner2 = () ->
		{
			
			BreadthFirstSearch(node.getRight(), q);
		};
		
		Thread thread = new Thread(runner);
		Thread thread2 = new Thread(runner2);
		thread.start();
		notify();
		thread2.start();
		notify();
		return;
		
		
	}
	

}
