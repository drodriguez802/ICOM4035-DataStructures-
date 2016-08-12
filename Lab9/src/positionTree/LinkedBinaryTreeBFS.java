package positionTree;

import positionInterfaces.Position;
import positionListLLDirect.NodePositionList;

public class LinkedBinaryTreeBFS<E> extends LinkedBinaryTree<E> {
	Queue<BTNode<E>> que;
	protected void fillIterator(TreeNode<E> r, NodePositionList<Position<E>> list) {
		
		if(!(r.getChildren().equals(null))){
				
		que=new LLQueue<>();
		BTNode<E> rbtn = (BTNode<E>) r; 
		que.enqueue(rbtn);
		
		while(!que.isEmpty()){
			rbtn=que.dequeue();
			list.addLast(rbtn);
			if(rbtn.getLeft()!=null){
				que.enqueue((BTNode<E>)rbtn.getLeft());
				}
		
		if(rbtn.getRight()!=null){
			que.enqueue((BTNode<E>) rbtn.getRight());
				}
			}
		}
	}
}
