
package menuClasses;

import ioManagementClasses.IOComponent;
import dataManager.DMComponent;
/**
 * Executes the sell shares from a ShareHolder action
 * @author Daniel Rodriguez Garcia
 */
public class SellSharesFromSH implements Action {

	@Override
	public void execute(Object arg) {
		int value=0;
		boolean valid=true;
		IOComponent io = IOComponent.getComponent(); 
		DMComponent dm = (DMComponent) arg; 
		String shName = io.getInput("Enter the ID of the Stockholder to sell shares from: ");
		String cName = io.getInput("Enter the symbol of the copmany to sell shares: ");
		while(valid){
			try{
				value = Integer.parseInt(io.getInput("Enter the number of shares to sell: "));
				valid=false;
			}
			catch(IllegalArgumentException e){
				io.output("\nPlease input a valid integer number\n");
				valid=true;
			}
		}
		dm.sellShareFromSH(shName, cName, value); 		
	}

}
