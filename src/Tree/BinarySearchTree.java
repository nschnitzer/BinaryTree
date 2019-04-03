//********************************************
// Nathan Schnitzer
// BinarySearchTree.java
// 3/20/19
// Represents a Binary Search Tree data structure
//********************************************

package Tree;

import java.util.Collections;
import java.util.LinkedList;

import QueueForTrees.Queue;

public class BinarySearchTree<Type extends Comparable> {
	TreeNode<Type> root;
	int height;

	public BinarySearchTree() {
		root = null;
		height = 0;
	}

	public BinarySearchTree(Type c) {
		root = new TreeNode<Type>(c, null, null);
		height = 1;
	}

	// Insert node
	public void insert(Type c) {
		if (root == null) {
			root = new TreeNode<Type>(c, null, null);
			height = 1;
			return;
		}

		if (root.getData().compareTo(c) > 0) {
			// Going to the left
			if (root.hasLeft()) {
				insert(root.getLeft(), c);
				return;
			}

			root.setLeft(new TreeNode<Type>(c, null, null, root));
			return;
		}

		// Going to the right
		if (root.hasRight()) {
			insert(root.getRight(), c);
			return;
		}

		root.setRight(new TreeNode<Type>(c, null, null, root));
		return;

	}

	private void insert(TreeNode<Type> node, Type c) {
		if (node.getData().compareTo(c) > 0) {
			// Going to the left
			if (node.hasLeft()) {
				insert(node.getLeft(), c);
				return;
			}

			node.setLeft(new TreeNode<Type>(c, null, null, node));
			// determineHeight();
			return;
		}

		// Going to the right
		if (node.hasRight()) {
			insert(node.getRight(), c);
			return;
		}

		node.setRight(new TreeNode<Type>(c, null, null, node));
		// determineHeight();
		return;
	}

	public void delete(Type c) {
		if (root.getData().equals(c)) // if deleting the root
		{
			// If it doesnt have any children
			if (root.hasChildren() == false) {
				root = null;
				return;
			} else // Shit gets much more complicated
			{
				TreeNode<Type> successor = findSuccessor(root);
				TreeNode<Type> temp = successor.getParent();
				Queue<Type> que = new Queue<Type>();
				que = getAllChildren(successor); // Will insert back in later
				successor.setLeft(null);
				successor.setRight(null); // Detach successor's children from
											// the tree - now unaccessable
				if (root.hasLeft() && root.getLeft() == successor) {
					successor.setRight(root.getRight());
					if (root.hasRight())
						root.getRight().setParent(successor);
				} else if (root.hasRight() && root.getRight() == successor) {
					successor.setLeft(root.getLeft());
					if (root.hasLeft())
						root.getLeft().setParent(successor);
				} else {
					successor.setRight(root.getRight());
					if (root.hasRight())
						root.getRight().setParent(successor);
					if (root.hasLeft())
						root.getLeft().setParent(successor);
				}
				root = successor;

				while (que.isEmpty() == false) {
					insert(successor, que.pop());
				}
			}
		} else {
			delete(findNode(root, c), c);
		}
	}

	private void delete(TreeNode<Type> node, Type c) {
		System.out.println("Removing: " + c);
		// If it doesnt have any children
		if (node.hasChildren() == false) {
			node = null;
			return;
		} else // Shit gets much more complicated
		{
			TreeNode<Type> successor = findSuccessor(node);
			TreeNode<Type> temp = successor.getParent();
			Queue<Type> que = new Queue<Type>();
			que = getAllChildren(successor); // Will insert back in later
			successor.setLeft(null);
			successor.setRight(null); // Detach successor's children from the
										// tree - now unaccessable

			if (node.hasLeft() && node.getLeft() == successor) {
				successor.setRight(node.getRight());
				if (node.hasRight())
					node.getRight().setParent(successor);
			} else if (node.hasRight() && node.getRight() == successor) {
				successor.setLeft(node.getLeft());
				if (node.hasLeft())
					node.getLeft().setParent(successor);
			} else {
				successor.setRight(node.getRight());
				if (node.hasRight())
					node.getRight().setParent(successor);
				if (node.hasLeft())
					node.getLeft().setParent(successor);
			}

			// Detach successor
			if (node.getParent().hasRight() && node.getParent().getRight() == node) // if this is the right node of the element
			{

				node.getParent().setRight(successor); // Detached node
				successor.setParent(node.getParent());
			} else {

				node.getParent().setLeft(successor);
				successor.setParent(node.getParent());
			}

			if (node.hasLeft() && node.getLeft() != successor) {

				node.getLeft().setParent(successor);
			} else if (node.hasRight() && node.getRight() != successor) {

				node.getRight().setParent(successor);

			}

			node = null;
			que.printQueueOneLine();

			while (que.isEmpty() == false) {
				insert(successor, que.pop());

			}
		}
	}

	// Finds the left most value of the right child
	public TreeNode<Type> findSuccessor(TreeNode<Type> node) {
		if (node.hasRight())
			node = node.getRight();

		if (node.hasLeft() == false) {
			return node;
		}

		return getDeepElement(node);
	}

	// Finds the node where the value is
	private TreeNode<Type> findNode(TreeNode<Type> node, Type c) throws AssertionError {
		if (node.getData().equals(c)) {
			return node;
		}

		if (node.hasChildren() == false) {
			System.out.println(c + " not found");
			throw new AssertionError(c + " does not exust");
		} else {
			if (node.getData().compareTo(c) > 0) {
				// Going left
				if (node.hasLeft() == false) {
					System.out.println(c + " not found");
					throw new AssertionError(c + " does not exust");
				}
				return findNode(node.getLeft(), c);
			} else {
				// Going right
				if (node.hasRight() == false) {
					System.out.println(c + " not found");
					throw new AssertionError(c + " does not exust");
				}
				return findNode(node.getRight(), c);
			}
		}
	}

	// Gets left most element
	private TreeNode<Type> getDeepElement(TreeNode<Type> index) {
		if (index.hasLeft() == true) {
			return getDeepElement(index.getLeft());
		}

		return index;
	}

	private Queue<Type> getAllChildren(TreeNode<Type> node) {

		Queue<Type> que = new Queue<Type>();
		// que.push(node.getData());
		if (node.hasLeft()) {
			getAllChildren(node.getLeft(), que);
		}
		if (node.hasRight()) {
			getAllChildren(node.getRight(), que);
		}

		return que;
	}

	private void getAllChildren(TreeNode<Type> node, Queue<Type> q) {
		q.push(node.getData());
		if (node.hasLeft()) {
			getAllChildren(node.getLeft(), q);
		}
		if (node.hasRight()) {
			getAllChildren(node.getRight(), q);
		}

		return;

	}

	public int determineHeight() {
		return determineHeight(root);
	}

	private int determineHeight(TreeNode<Type> node) {

		if (node == null) {
			return 0;
		}

		return Integer.max(determineHeight(node.getLeft()), determineHeight(node.getRight())) + 1;

	}

	public int getHeight() {
		determineHeight();
		return height;
	}

	public void printOut() {
		for (int i = 1; i <= getHeight(); i++) {
			printOutLevel(root, i);
		}
	}

	public void printOutLevel(TreeNode<Type> node, int level) {
		if (node == null)
			return;
		if (level == 1)
			System.out.print(node.data + " ");
		else if (level > 1) {
			printOutLevel(node.getLeft(), level - 1);
			printOutLevel(node.getRight(), level - 1);
		}
	}

	public boolean search(Type c) {
		try {
			findNode(root, c);
		} catch (AssertionError e) {
			return false;
		}

		return true;

	}
	
	static BinarySearchTree convert(BinaryTree<Double> bt)
	{
		Queue<Double> que = bt.inOrder();
		BinarySearchTree<Double> bst = new BinarySearchTree<Double>();
		while (que.isEmpty() == false)
		{
			bst.insert(que.pop());
		}
		
		return bst;
	}
	
	static BinarySearchTree convert(LinkedList<Double> link)
	{
		Collections.sort(link);
		BinarySearchTree<Double> bst = new BinarySearchTree<Double>();
		while (link.size() > 0)
		{
			bst.insert(link.get(link.size()/2));
			link.remove(link.size()/2);
		}
		
		return bst;
	}
	
	static BinarySearchTree convert(double[] arr)
	{
		BinarySearchTree<Double> bst = new BinarySearchTree<Double>();
		
		bst.insert(arr[arr.length/2]);
		for (int i = 0; i < arr.length/2;i++)
		{
			if (arr.length/2 + i < arr.length)
			{
				bst.insert(arr[arr.length/2+i]);
			}
			
			if (arr.length/2 - i > 0)
			{
				bst.insert(arr[arr.length/2-i]);
			}
		}
		
		return bst;
	}

}
