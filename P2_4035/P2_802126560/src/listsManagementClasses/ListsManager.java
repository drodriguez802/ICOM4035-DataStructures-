package listsManagementClasses;

import java.util.ArrayList;

import systemGeneralClasses.FedNumbers;

/**
 * This class defines the type of object that manages the different
 * lists being managed by the system for the lab session
 *
 * @author pedroirivera-vega
 *
 */
public class ListsManager {
	private ArrayList<NamedList> lists;

	public ListsManager() {
		lists = new ArrayList<>();
	}


	public int size(){
		return lists.size();
	}
	public ArrayList<NamedList> getList(){
		return lists;
		
	}
	/**
	 * Find the index of the position where a list with a given name is.
	 * If no such list it returns -1; otherwise, it returns the index of
	 * the position where it is located in the list of lists....
	 * @param name
	 * @return
	 */
	public int getListIndex(String name) {
		if(!lists.isEmpty()){
			for (int i=0; i<lists.size(); i++)
				if (lists.get(i).getName().equals(name))
					return i;
		}
		return -1;
	}

	/**
	 * Creates a new named list with the given name.
	 * @param lName the name of the new list.
	 *     PRE: the name given is assumed to be a valid name for
	 *     a list.
	 */
	public void createNewList(String lName) {
		lists.add(new NamedList(lName));
	}
	
	/**
	 * Sets the value of an existing variable to a new one given in the parameter
	 * @param listIndex index of the value to be modified
	 * @param value value to be replaced for
	 * @throws IndexOutOfBoundsException in case the index goes out of bounds
	 */
	public void setElement(int listIndex, FedNumbers value)
			throws IndexOutOfBoundsException
	{
		// PRE: the listIndex is assumed to be a valid index for the
		// list of lists....
		if(lists.get(listIndex).isEmpty())
			lists.get(listIndex).add(value);
		lists.get(listIndex).setNumber(value);
	}
/**
 * Method responsible of removing an element at the request of the user
 * @param listIndex index in the list where the element to be removed is
 * @return returns the element removed
 * @throws IndexOutOfBoundsException in case the index goes outside the size of the list
 */
	public NamedList removeElement(int listIndex)
			throws IndexOutOfBoundsException
	{
		// PRE: the listIndex is assumed to be a valid index for the
		// list of lists....
		return lists.remove(listIndex);
	}
	/**
	 * Returns the number of an element given its index
	 * @param index index where the element is stored in the ArrayList
	 * @return a FedNumbers number
	 */
	public FedNumbers getElement(int index)
	{
		// PRE: the listIndex is assumed to be a valid index for the
		// list of lists....
		return lists.get(index).getNumber();
	}


	/**
	 * Returns the name of the variable in the list
	 * @param listIndex index of the given variable name
	 * @return a String with the name of the variable
	 */
	public String getName(int listIndex) {
		// PRE: the listIndex is assumed to be a valid index for the
		// list of lists....
		return lists.get(listIndex).getName();
	}
	
	public int getNumberOfLists() {
		return lists.size();
	}

	public boolean nameExists(String name) {
		int index = getListIndex(name);
		return index != -1;
	}
}