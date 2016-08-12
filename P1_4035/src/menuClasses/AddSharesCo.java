
package menuClasses;

import ioManagementClasses.IOComponent;
import dataManager.DMComponent;
/**
 * Action in charge of adding aditional shares to a copmany
 * @author Daniel Rodriguez Garcia
 */
public class AddSharesCo implements Action {

	@Override
	public void execute(Object arg) {
		boolean valid=true;
		int value=0;
		IOComponent io = IOComponent.getComponent(); 
		DMComponent dm = (DMComponent) arg; 
		String name = io.getInput("Enter the symbol of the copmany to add shares: ");
		while(valid){
			try{
				value = Integer.parseInt(io.getInput("Enter the number of shares to add: "));
				valid=false;
			}
			catch(IllegalArgumentException e){
				io.output("\nPlease input a valid integer number\n");
				valid=true;
			}
		}
		dm.addShareToCo(name, value); 		
	}

}
