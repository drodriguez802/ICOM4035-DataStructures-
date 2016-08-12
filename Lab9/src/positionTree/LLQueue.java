package positionTree;

import positionInterfaces.BTPosition;



public class LLQueue<E> implements Queue<E>
{
	BTPosition<E> front;
	private BTNode<E> rear; 
	private int size; 
	
	public LLQueue() {
		front = rear = null; 
		size = 0; 
	}
	
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()){
			throw new EmptyQueueException("dequeue: Queue is empty.");
		}
		
			E etr = front.element();
			front=front.getRight();
			size--;
			return etr;
		
	}

	public void enqueue(E e) {
		BTNode<E> nn = new BTNode<E>(e, front); 
		if (size == 0)
			front = nn; 
		else 
			rear.setRight(nn); 
		rear = nn; 
		size++; 
	}

	public E front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("front: Queue is empty."); 
		return front.element();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// just for testing and grading....
    public void showReverse() { 
	    if (size == 0)
		   System.out.println("Queue is empty."); 
		else
		   recSR(front);
    } 
    private void recSR(BTPosition<E> btPosition) { 
		if (btPosition != null) { 
		   recSR(btPosition.getRight()); 
		   System.out.println(btPosition.element()); 
	     } 
    } 


}
