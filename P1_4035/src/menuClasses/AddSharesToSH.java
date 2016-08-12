
package menuClasses;

import ioManagementClasses.IOComponent;
import dataManager.DMComponent;
/**
 * ACtion in charge of adding shares to a ShareHOlder
 * @author Daniel Rodriguez Garcia
 */
public class AddSharesToSH implements Action {

	@Override
	public void execute(Object arg) {
		int value=0;
		boolean valid=true;
		IOComponent io = IOComponent.getComponent(); 
		DMComponent dm = (DMComponent) arg; 
		String shName = io.getInput("Enter the ID of the Stockholder to buy shares: ");
		String cName = io.getInput("Enter the symbol of the copmany to buy shares: ");
		
		while(valid){
			try{
				value = Integer.parseInt(io.getInput("Enter the number of shares to buy: "));
				valid=false;
			}
			catch(IllegalArgumentException e){
				io.output("\nPlease input a valid integer number\n");
				valid=true;
			}
		}
		dm.addShareToSH(shName, cName, value); 		
	}

}
