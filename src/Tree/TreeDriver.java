package Tree;

public class TreeDriver {

	public static void main(String[] args) 
	{
		BinaryTree<String> bt = new BinaryTree<String>();
		bt.insert("Hello");
		bt.insert("Hi There");
		bt.insert("Whats up");
		bt.insert("Hi");
		System.out.println(bt.getHeight());
		bt.BreadthFirstSearch();

	}

}
