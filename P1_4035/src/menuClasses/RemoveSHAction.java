
package menuClasses;

import java.util.UUID;

import dataManager.DMComponent;
import ioManagementClasses.IOComponent;
/**
 * Executes a ShareHolder removal
 * @author Daniel Rodriguez Garcia
 */
public class RemoveSHAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		io.output("\nRemove a ShareHolder: \n"); 
		String id = io.getInput("\nEnter your id: "); 
		dm.removeSH(id);
		
	}

}
