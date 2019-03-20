//******************************************
// Nathan Schnitzer
// BinaryTreeDriver.java
// 3/20/19
// Tests a Binary Search Tree
//******************************************

package Tree;

public class BinaryTreeDriver {

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
		BTreePrinter.printNode(st.root);

	}

}
