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
		System.out.println(bt.getHeight());
		bt.BreadthFirstSearch();
		//bt.getGivenLength(2);
		System.out.println();
		bt.printPreOrder();
		System.out.println();
		BTreePrinter.printNode(bt.root);
		System.out.println();
		bt.printPostOrder();
	//	bt.getGivenLength(2);
		
		
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
		 * ISSUE WITH INSERT - Attempting Redesign
		 */

	}

}
