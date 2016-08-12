
package menuClasses;

import ioManagementClasses.IOComponent;
import dataManager.DMComponent;
/**
 * Split shares action
 * @author Daniel Rodriguez Garcia
 */
public class SplitCoShAction implements Action {

	@Override
	public void execute(Object arg) {
		IOComponent io = IOComponent.getComponent(); 
		DMComponent dm = (DMComponent) arg; 
		String symbol = io.getInput("Input the Company symbol to split the shares: ");
		dm.split(symbol); 		
	}

}