//******************************************************
// Nathan Schnitzer
// BinaryTree.java
// 2/28/19
// Represents a binary tree
//******************************************************


package Tree;

public class BinaryTree<T>
{
	TreeNode<T> root;
	int height;
	
	public BinaryTree()
	{
		root = null;
		height = 0;
	}
	
	public BinaryTree(TreeNode r)
	{
		root = r;
		determineHeight();
	}
	
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
	
	//Check right first
	//Fill up tree-- NOT BINARY SEARCH TREE!!!!
	public void insert(T n)
	{
		TreeNode<T> currentLoc = root;
		//Traverse to the height - 1 level at right most node
		for (int i = 0; i < height; i++)
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
			return;
		}
		
		//Shit's getting real now
		//Check if the left child is full, if it is then you can just fill it up and not have to do anything fancy
		if (currentLoc.hasLeft())
		{
			currentLoc.setRight(new TreeNode<T>(n, null, null));
			return;
		}
		
		//Gotta check the other nodes on the level now....
		insert(root);
		
	}
	
	//Check for first empty slot from left to right
	public void insert(TreeNode<T> loc)
	{
		
	}
	

}
