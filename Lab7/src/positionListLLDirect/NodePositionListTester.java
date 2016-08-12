package positionListLLDirect;

import exceptionClasses.EmptyListException;
import positionInterfaces.Position;

public class NodePositionListTester {

	public static void main(String[] args) {
		
		NodePositionList<Integer> w = new NodePositionList<Integer> (); 
		NodePositionList<Integer> q = new NodePositionList<Integer> (); 
		q.addFirst(10); 
		
		w.addFirst(5); 
		w.addFirst(3); 
		
		w.addLast(10); 
		w.addLast(13); 
		try {
			Position<Integer> p = w.first();
			w.addAfter(p, 2); 
			p = w.next(p); 
			w.addAfter(p, 34); 
			w.addBefore(p, 40); 
			p = w.first();//YOU MUST CHANGE FROM Q TO W, THE EXCEPTION IS THROWN BECAUSE ITS A POSITION IN LIST Q NOT IN W SO W CANT ADD 89 AFTER A 
			//POSITION IT DOES NOT HAVE.
			w.addAfter(p, 89); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		showElements(w); 

	}

	private static <E> void showElements(NodePositionList<E> w) {
		for(E p : w) 
			System.out.println(p); 
	}
	
	

}
