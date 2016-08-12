package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import linkedLists.LinkedList;


public class SLFLList<E> 
implements LinkedList<E>
{

	private SNode<E> first, last; 
	int length = 0; 
	
	public SLFLList() { 
		first = last = null; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		SNode<E> tNew=(SNode<E>)nuevo;
		if(length==0){
			first=tNew;
			last=tNew;
			
			}
		else{
			tNew.setNext(first);
			first=tNew;
			
		}
		length++;
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		if(target==last){
			((SNode<E>) last).setNext((SNode<E>)nuevo);
			last=(SNode<E>) nuevo;
			length++;
			
		}
		else{
		Node<E> temp = ((SNode<E>) target).getNext();
		((SNode<E>) target).setNext((SNode<E>) nuevo);
		((SNode<E>) nuevo).setNext((SNode<E>)temp);
		length++;
		}
		
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if(length==0){
			throw new NodeOutOfBoundsException("List is empty");
		}
		else if (target.equals(first)){
			this.addFirstNode(nuevo);
			length++;
		}
		else { 
			Node<E> prevNode = getNodeBefore(target);  
			this.addNodeAfter(prevNode, nuevo); 
			length++;
		}
		
	}

	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		if(length==0){
			throw new NodeOutOfBoundsException("List is empty");
		}
		// TODO Auto-generated method stub
		return first;
	}

	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		if(length==0){
			throw new NodeOutOfBoundsException("List is empty");
		}
		// TODO Auto-generated method stub
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NodeOutOfBoundsException {
		
		
		return ((SNode<E>) target).getNext();
	}

	public Node<E> getNodeBefore(Node<E> target)throws NodeOutOfBoundsException {
		if (target == first) 
			return null; 
		else { 
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev;
		}
	}

	public int length() {
		
		return length;
	}

	public void removeNode(Node<E> target) {
		if (target.equals(first)) {
			// the list is not empty....
			SNode<E> ntr = first; 
			first = first.getNext(); 
			ntr.setNext(null);      // notice that the node keeps its data..
		}
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
			((SNode<E>) target).setElement(null);
		}
		length--; 
		
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}


	///////////////////////////////////////////////////
	// private and static inner class that defines the 
	// type of node that this list implementation uses
	private static class SNode<T> implements Node<T> {
		private T element; 
		private SNode<T> next; 
		public SNode() { 
			element = null; 
			next = null; 
		}
		public SNode(T data, SNode<T> next) { 
			this.element = data; 
			this.next = next; 
		}
		public SNode(T data)  { 
			this.element = data; 
			next = null; 
		}
		public T getElement() {
			return element;
		}
		public void setElement(T data) {
			this.element = data;
		}
		public SNode<T> getNext() {
			return next;
		}
		public void setNext(SNode<T> next) {
			this.next = next;
		}
	}

}
