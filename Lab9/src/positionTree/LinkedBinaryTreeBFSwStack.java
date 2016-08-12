package positionTree;

import positionInterfaces.Position;
import positionListLLDirect.NodePositionList;

public class LinkedBinaryTreeBFSwStack<E> extends LinkedBinaryTree<E> {
	Stack<BTNode<E>> stack;
	protected void fillIterator(TreeNode<E> r, NodePositionList<Position<E>> list) {
		
		if(!(r.getChildren().equals(null))){
				
		stack=new LLStack<BTNode<E>>();
		BTNode<E> rbtn = (BTNode<E>) r; 
		stack.push(rbtn);
		
		
		while(!stack.isEmpty()){
			
			rbtn=stack.pop();
			list.addLast(rbtn);
			
			

		
			if(rbtn.getRight()!=null){
				
			stack.push((BTNode<E>) rbtn.getRight());
				}
//			else{
//			stack.pop();
//		}
			if(rbtn.getLeft()!=null){
				
				stack.push((BTNode<E>)rbtn.getLeft());
				}
//			else{
//				stack.pop();
//			}
			}
		}
	}
}
