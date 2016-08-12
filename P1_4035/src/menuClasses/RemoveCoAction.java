
package menuClasses;

import ioManagementClasses.IOComponent;
import dataManager.DMComponent;
/**
 * Executes the remove company action
 * @author Daniel Rodriguez Garcia
 */
public class RemoveCoAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		io.output("\nRemoving a Copmany from the system:\n"); 
		String listName = io.getInput("Enter name of the Company to remove: "); 
		dm.removeCo(listName);
	}

}
