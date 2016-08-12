package systemGeneralClasses;

public interface Position<E> {

	E getElement() throws IllegalStateException;
	
}