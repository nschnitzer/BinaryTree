package Tree;

public class TreeDriver {

	public static void main(String[] args) 
	{
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		BinaryTree<Integer> bs = new BinaryTree<Integer>();
		bt.insert(1);
		bs.insert2(1);
		BTreePrinter.printNode(bs.root);
		bt.insert(2);
		bs.insert2(2);
		BTreePrinter.printNode(bs.root);
		bt.insert(3);
		bs.insert2(3);
		BTreePrinter.printNode(bs.root);
		bt.insert(4);
		bs.insert2(4);
		BTreePrinter.printNode(bs.root);
		bt.insert(5);
		bs.insert2(5);
		BTreePrinter.printNode(bs.root);
		bs.insert2(6);
		bs.insert2(7);
		System.out.println(bs.getDeepParent(bs.root).getData());
		bs.insert2(8);
		bs.insert2(9);
		
	//	System.out.println(bt.getHeight());
	//	bt.BreadthFirstSearch();
		//bt.getGivenLength(2);
	//	System.out.println();
	//	bt.printPreOrder();
	//	System.out.println();
		BTreePrinter.printNode(bs.root);
		//System.out.println();
	//	bt.printPostOrder();
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
