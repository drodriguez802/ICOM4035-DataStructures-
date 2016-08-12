package treeTesters;

import positionInterfaces.BinaryTree;
import positionInterfaces.Position;
import positionInterfaces.Tree;
import positionTree.BTNode;
import positionTree.LinkedBinaryTree;
import positionTree.LinkedBinaryTreeBFS;
import positionTree.LinkedBinaryTreeBFSwStack;
import positionTree.LinkedBinaryTreeInOI;
import positionTree.LinkedBinaryTreePostOI;

public class LinkedBinaryTreeTester {

	public static void main(String[] args) {
		LinkedBinaryTree<Integer> t4 = new LinkedBinaryTree<Integer>(); 
		BTNode<Integer> v; 
		
		v = (BTNode<Integer>) t4.addRoot(4); 
		//9
		t4.insertLeft(v, 9);
		t4.insertLeft(v.getLeft(), 7);
		t4.insertRight(t4.left(v), 10);
		//20
		t4.insertRight(v, 20);
		t4.insertLeft(t4.right(v), 15);
		t4.insertRight(t4.right(v), 21);
		//15
		t4.insertLeft(v.getRight().getLeft(), 12);
		t4.insertRight(v.getRight().getLeft(), 17);
		t4.insertRight(v.getRight().getLeft().getRight(), 19);
		t4.insertRight(v.getRight().getRight(), 40);
		t4.insertLeft(v.getRight().getRight().getRight(), 30);
		t4.insertRight(v.getRight().getRight().getRight(), 45);
		showTreeElements(t4); 
		
		t4.makeEmpty(); 
		showTreeElements(t4); 
		
		
		
		
		LinkedBinaryTreePostOI<Integer> t2 = new LinkedBinaryTreePostOI<Integer>(); 
		// ADD NECESSARY INSTRUCTIONS TO CONSTRUCT A NEW COPY OF THE SPECIFIED TREE...
		
				BTNode<Integer> y = (BTNode<Integer>) t2.addRoot(4); 
				//9
				t2.insertLeft(y, 9);
				t2.insertLeft(y.getLeft(), 7);
				t2.insertRight(t4.left(y), 10);
				//20
				t2.insertRight(y, 20);
				t2.insertLeft(t4.right(y), 15);
				t2.insertRight(t4.right(y), 21);
				//15
				t2.insertLeft(y.getRight().getLeft(), 12);
				t2.insertRight(y.getRight().getLeft(), 17);
				t2.insertRight(y.getRight().getLeft().getRight(), 19);
				t2.insertRight(y.getRight().getRight(), 40);
				t2.insertLeft(y.getRight().getRight().getRight(), 30);
				t2.insertRight(y.getRight().getRight().getRight(), 45);
		showTreeElements(t2);
		
		LinkedBinaryTreeInOI<Integer> t3 = new LinkedBinaryTreeInOI<Integer>();
		BTNode<Integer> x = (BTNode<Integer>) t3.addRoot(4); 
		t3.insertLeft(x, 9);
		t3.insertLeft(x.getLeft(), 7);
		t3.insertRight(t4.left(x), 10);
		//20
		t3.insertRight(x, 20);
		t3.insertLeft(t4.right(x), 15);
		t3.insertRight(t4.right(x), 21);
		//15
		t3.insertLeft(x.getRight().getLeft(), 12);
		t3.insertRight(x.getRight().getLeft(), 17);
		t3.insertRight(x.getRight().getLeft().getRight(), 19);
		t3.insertRight(x.getRight().getRight(), 40);
		t3.insertLeft(x.getRight().getRight().getRight(), 30);
		t3.insertRight(x.getRight().getRight().getRight(), 45);
		showTreeElements(t3);
		
		//BFS
		LinkedBinaryTreeBFS<Integer> t41 = new LinkedBinaryTreeBFS<Integer>();
		BTNode<Integer> z = (BTNode<Integer>) t41.addRoot(4); 
		t41.insertLeft(z, 9);
		t41.insertLeft(z.getLeft(), 7);
		t41.insertRight(t41.left(z), 10);
		//20
		t41.insertRight(z, 20);
		t41.insertLeft(t41.right(z), 15);
		t41.insertRight(t41.right(z), 21);
		//15
		t41.insertLeft(z.getRight().getLeft(), 12);
		t41.insertRight(z.getRight().getLeft(), 17);
		t41.insertRight(z.getRight().getLeft().getRight(), 19);
		t41.insertRight(z.getRight().getRight(), 40);
		t41.insertLeft(z.getRight().getRight().getRight(), 30);
		t41.insertRight(z.getRight().getRight().getRight(), 45);
		showTreeElements(t41);
		
		//BFS WITH STACK
		LinkedBinaryTreeBFSwStack<Integer> t5 = new LinkedBinaryTreeBFSwStack<Integer>();
		BTNode<Integer> d = (BTNode<Integer>) t5.addRoot(4); 
		t5.insertLeft(d, 9);
		t5.insertLeft(d.getLeft(), 7);
		t5.insertRight(t4.left(d), 10);
		//20
		t5.insertRight(d, 20);
		t5.insertLeft(t4.right(d), 15);
		t5.insertRight(t4.right(d), 21);
		//15
		t5.insertLeft(d.getRight().getLeft(), 12);
		t5.insertRight(d.getRight().getLeft(), 17);
		t5.insertRight(d.getRight().getLeft().getRight(), 19);
		t5.insertRight(d.getRight().getRight(), 40);
		t5.insertLeft(d.getRight().getRight().getRight(), 30);
		t5.insertRight(d.getRight().getRight().getRight(), 45);
		showTreeElements(t5);
		

		
	}
	
	/**
	 * Shows the elements in a particular tree based on the available
	 * iterator for the particular type of tree....
	 * @param t the tree to traverse...
	 */
	private static void showTreeElements(Tree<Integer> t) { 
		System.out.println("The tree has "+ t.size() + " elements. These are: "); 
		for (Integer e : t)
			System.out.print(e + " ");
		System.out.println(); 
	}

}
