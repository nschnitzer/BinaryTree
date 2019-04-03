//******************************************
// Nathan Schnitzer
// BinaryTreeDriver.java
// 3/20/19
// Tests a Binary Search Tree
//******************************************

package Tree;

public class BSTDriver {

	public static void main(String[] args) {
		BinarySearchTree<Integer> st = new BinarySearchTree<Integer>();

		st.insert(50);
		st.insert(24);
		st.insert(75);
		st.insert(12);
		st.insert(13);
		st.insert(100);
		st.insert(95);
		st.insert(120);
		st.insert(99);
		System.out.println(st.determineHeight());
		BTreePrinter.printNode(st.root);
		// st.printOut();
		System.out.println(st.findSuccessor(st.root.getRight()).getData());
		st.delete(100);
		BTreePrinter.printNode(st.root);
		/*
		 * st.delete(50); BTreePrinter.printNode(st.root); st.delete(24);
		 * BTreePrinter.printNode(st.root); st.delete(95);
		 * BTreePrinter.printNode(st.root);
		 */

	}

}
