
package menuClasses;

import java.text.DecimalFormat;

import dataManager.DMComponent;
import ioManagementClasses.IOComponent;
/**
 * Updates the value of a company share
 * @author Daniel Rodriguez Garcia
 */
public class UpShareValAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		io.output("\nUpdate a share price:\n"); 
		String symbol = io.getInput("\nEnter symbol of Company: "); 
		double price=0;
		boolean valid=true;
		while(valid){
			try{
				price = Double.parseDouble(io.getInput("\nEnter price per share: "));
				valid=false;
			}
			catch(IllegalArgumentException e){
				io.output("\nPlease input a valid number\n");
				valid=true;
			}
		}
		DecimalFormat decim = new DecimalFormat("0.00");
		String priceS=decim.format(price);
		price=Double.parseDouble(priceS);
		dm.upShare(symbol, price); 
		
	}

}
