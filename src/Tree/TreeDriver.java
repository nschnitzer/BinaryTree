package Tree;

public class TreeDriver {

	public static void main(String[] args) 
	{
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.insert(1);
		bt.insert(2);
		bt.insert(3);
		bt.insert(4);
		bt.insert(5);
		bt.insert(6);
		bt.insert(7);
		bt.insert(8);
		bt.insert(9);
		BTreePrinter.printNode(bt.root);
		bt.getGivenLength(4);
		bt.printBreadthFirst();
		System.out.println();
		
		
		
		/*
		 * WORKS:
		 * 
		 * BreadthFirstSearch
		 * getGivenLength
		 * height
		 * PreOrder
		 * 
		 * TODO:
		 * InOrder
		 * 
		 */

	}

}
