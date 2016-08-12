package systemGeneralClasses;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionList<E> {
	// ---------------- nested Node class ----------------
	private static class Node<E> implements Position<E> {

		private E element; // reference to the element stored at this node

		private Node<E> prev; // reference to the previous node in the list

		private Node<E> next; // reference to the subsequent node in the list

		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}

		public E getElement() throws IllegalStateException {
			if (next == null) // convention for defunct node
				throw new IllegalStateException("Position no longer valid");
			return element;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setElement(E e) {
			element = e;
		}

		public void setPrev(Node<E> p) {
			prev = p;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
	} // ----------- end of nested Node class -----------

	private Node<E> header; // header sentinel

	private Node<E> trailer; // trailer sentinel

	private int size = 0; // number of elements in the list

	public LinkedPositionalList() {
		header = new Node<>(null, null, null); // create header
		trailer = new Node<>(null, header, null); // trailer is preceded by
													// header
		header.setNext(trailer); // header is followed by trailer
	}

	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getNext() == null) // convention for defunct node
			throw new IllegalArgumentException("p is no longer in the list");
		return node;
	}

	private Position<E> position(Node<E> node) {
		if (node == header || node == trailer)
			return null; // do not expose user to the sentinels
		return node;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Position<E> first() {
		return position(header.getNext());
	}

	public Position<E> last() {
		return position(trailer.getPrev());
	}

	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ); // create and link a new
													// node
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}

	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext()); // just after the header
	}

	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer); // just before the
															// trailer
	}

	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null); // help with garbage collection
		node.setNext(null); // and convention for defunct node
		node.setPrev(null);
		return answer;
	}

	// ---------------- nested PositionIterator class ----------------
	/**
	 * A (nonstatic) inner class. Note well that each instance contains an
	 * implicit reference to the containing list, allowing us to call the list's
	 * methods directly.
	 */
	private class PositionIterator implements Iterator<Position<E>> {

		/**
		 * A Position of the containing list, initialized to the first position.
		 */
		private Position<E> cursor = first(); // position of the next element to
												// report
		/** A Position of the most recent element reported (if any). */
		private Position<E> recent = null; // position of last reported element

		/**
		 * Tests whether the iterator has a next object.
		 * 
		 * @return true if there are further objects, false otherwise
		 */
		public boolean hasNext() {
			return (cursor != null);
		}

		/**
		 * Returns the next position in the iterator.
		 *
		 * @return next position
		 * @throws NoSuchElementException
		 *             if there are no further elements
		 */
		public Position<E> next() throws NoSuchElementException {
			if (cursor == null)
				throw new NoSuchElementException("nothing left");
			recent = cursor; // element at this position might later be removed
			cursor = after(cursor);
			return recent;
		}

		/**
		 * Removes the element returned by most recent call to next.
		 * 
		 * @throws IllegalStateException
		 *             if next has not yet been called
		 * @throws IllegalStateException
		 *             if remove was already called since recent next
		 */
		public void remove() throws IllegalStateException {
			if (recent == null)
				throw new IllegalStateException("nothing to remove");
			LinkedPositionalList.this.remove(recent); // remove from outer list
			recent = null; // do not allow remove again until next is called
		}
	} // ------------ end of nested PositionIterator class ------------

	// ---------------- nested PositionIterable class ----------------
	private class PositionIterable implements Iterable<Position<E>> {
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	} // ------------ end of nested PositionIterable class ------------

	/**
	 * Returns an iterable representation of the list's positions.
	 * 
	 * @return iterable representation of the list's positions
	 */

	public Iterable<Position<E>> positions() {
		return new PositionIterable(); // create a new instance of the inner
										// class
	}

	// ---------------- nested ElementIterator class ----------------
	/*
	 * This class adapts the iteration produced by positions() to return
	 * elements.
	 */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = new PositionIterator();

		public boolean hasNext() {
			return posIterator.hasNext();
		}

		public E next() {
			return posIterator.next().getElement();
		} // return element!

		public void remove() {
			posIterator.remove();
		}
	}

	/**
	 * Returns an iterator of the elements stored in the list.
	 * 
	 * @return iterator of the list's elements
	 */

	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	// Debugging code
	/**
	 * Produces a string representation of the contents of the list. This exists
	 * for debugging purposes only.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		Node<E> walk = header.getNext();
		while (walk != trailer) {
			sb.append(walk.getElement());
			walk = walk.getNext();
			if (walk != trailer)
				sb.append(", ");
		}
		sb.append(")");
		return sb.toString();
	}
}
