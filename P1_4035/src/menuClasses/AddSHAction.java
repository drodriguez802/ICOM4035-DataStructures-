
package menuClasses;

import java.util.UUID;

import dataManager.DMComponent;
import ioManagementClasses.IOComponent;
/**
 * Action in charge of executing the addition of a ShareHolder to the system.
 * @author Daniel Rodriguez Garcia
 */
public class AddSHAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		io.output("\nAdd a ShareHolder: \n"); 
		String name = io.getInput("\nEnter your name: ");
		String id=UUID.randomUUID().toString(); 
		dm.addSH(name, id);
		
	}

}
