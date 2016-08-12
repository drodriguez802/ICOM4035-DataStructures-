package positionListLLDirect;

import java.util.Iterator;

import positionInterfaces.PositionList;

public interface PositionListIteratorMaker<E> extends Iterator<E> {
	
	Iterator<E> makeIterator(PositionList<E> pl);
	
}
