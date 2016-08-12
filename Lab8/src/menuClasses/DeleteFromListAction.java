package menuClasses;

import ioManagementClasses.IOComponent;
import dataManager.DMComponent;

public class DeleteFromListAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		io.output("\nRemoving a list from the system:\n"); 
		String listName = io.getInput("Enter name of list to remove: "); 
		dm.removeList(listName); 
	}

}
