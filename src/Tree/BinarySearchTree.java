//********************************************
// Nathan Schnitzer
// BinarySearchTree.java
// 3/20/19
// Represents a Binary Search Tree data structure
//********************************************

package Tree;

import QueueForTrees.Queue;


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
			determineHeight();
			return;
		}

		//Going to the right
		if (node.hasRight())
		{
			insert(node.getRight(), c);
			return;
		}

		node.setRight(new TreeNode<Type>(c, null, null, node));
		determineHeight();
		return;
	}


	public void delete(Type c)
	{
		if (root.getData().equals(c))
		{
			//If it doesnt have any children
			if (root.hasChildren() == false)
			{
				root = null;
				return;
			}
			else //Shit gets much more complicated
			{
				TreeNode<Type> successor = findSuccessor(root);
				TreeNode<Type> temp = successor.getParent();
				Queue<Type> que = new Queue<Type>();
				que = getAllChildren(root); //Will insert back in later
				successor.setLeft(null);
				successor.setRight(null); //Detach successor's children from the tree - now unaccessable
				//Detach successor
				if (root.getParent().hasRight() && root.getParent().getRight() == root)
				{
					root.getParent().setRight(successor); //Detached node
				}
				else
				{
					root.getParent().setLeft(successor);
				}

				while (que.isEmpty() == false)
				{
					insert(successor, que.pop());
				}
			}
			determineHeight();

		}
		else
		{
			delete(findNode(root, c), c);
		}
	}

	private void delete(TreeNode<Type> node, Type c)
	{
		System.out.println("ENTERRR");
		//If it doesnt have any children
		if (node.hasChildren() == false)
		{
			node = null;
			return;
		}
		else //Shit gets much more complicated
		{
			TreeNode<Type> successor = findSuccessor(root);
			TreeNode<Type> temp = successor.getParent();
			Queue<Type> que = new Queue<Type>();
			que = getAllChildren(node); //Will insert back in later
			successor.setLeft(null);
			successor.setRight(null); //Detach successor's children from the tree - now unaccessable
			//Detach successor
			if (node.getParent().hasRight() && node.getParent().getRight() == node)
			{
				node.getParent().setRight(successor); //Detached node
			}
			else
			{
				node.getParent().setLeft(successor);
			}

			if (node.hasLeft() && node.getLeft() != successor)
			{
				node.getLeft().setParent(successor);
			}
			else if (node.hasRight() && node.getRight() != successor)
			{
				node.getRight().setParent(successor);

			}

			while (que.isEmpty() == false)
			{
				insert(successor, que.pop());
			}
		}
		determineHeight();
	}

	//Finds the left most value of the right child
	private TreeNode<Type> findSuccessor(TreeNode<Type> node)
	{
		node = node.getRight();

		if (node.hasLeft() == false)
		{
			return node;
		}

		return getDeepElement(node);
	}

	//Finds the node where the value is
	private TreeNode<Type> findNode(TreeNode<Type> node, Type c) throws AssertionError
	{
		if (node.getData().equals(c))
		{
			return node;
		}

		if (node.hasChildren() == false)
		{
			System.out.println(c + " not found");
			throw new AssertionError(c + " does not exust");
		}
		else
		{
			if (node.getData().compareTo(c) > 0)
			{
				//Going left
				if (node.hasLeft() == false)
				{
					System.out.println(c + " not found");
					throw new AssertionError(c + " does not exust");
				}
				return findNode(node.getLeft(), c);
			}
			else
			{
				//Going right
				if (node.hasRight() == false)
				{
					System.out.println(c + " not found");
					throw new AssertionError(c + " does not exust");
				}
				return findNode(node.getRight(), c);
			}
		}
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


	private void determineHeight()
	{
		if (root == null)
		{
			height = 0;
			return;
		}
		height = determineHeight(root);
	}

	private int determineHeight(TreeNode<Type> node)
	{

		int lDeep = 0, rDeep = 0;
		if (node.hasLeft())
			lDeep = determineHeight(node.getLeft());
		if (node.hasRight())
			rDeep = determineHeight(node.getRight());

		if (lDeep > rDeep)
		{
			return lDeep + 1;
		}
		return rDeep + 1;

	}

	public int getHeight()
	{
		determineHeight();
		return height;
	}

}
