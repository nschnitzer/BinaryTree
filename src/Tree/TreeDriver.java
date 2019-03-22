package Tree;

public class TreeDriver {

	public static void main(String[] args) 
	{
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		System.out.println("Inserting values 1 -> 9 into the tree");
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
		
		
		System.out.println();
		System.out.println("Height of the tree");
		System.out.println(bt.getHeight());
		System.out.println();
		
		System.out.println("Printing out level 4 (root is level 1)");
		bt.getGivenLength(4);
		System.out.println();
		
		System.out.println("Breadth First With Queue");
		bt.printBreadthFirstQueue();
		System.out.println();
		
		System.out.println("Breadth First Without Queue");
		bt.printBreadthFirst();
		System.out.println();
	
		
		System.out.println("Printing out In Order");
		bt.printInOrder();
		System.out.println();
		
		
		System.out.println("Printing out in Pre Order");
		bt.printPreOrder();
		System.out.println();
		
		System.out.println("Printing out in Post Order");
		bt.printPostOrder();
		System.out.println();
		
		
	}

}
