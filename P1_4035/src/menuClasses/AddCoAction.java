
package menuClasses;

import java.text.DecimalFormat;

import dataManager.DMComponent;
import ioManagementClasses.IOComponent;
/**
 * Action in charge of adding a Company to the system
 * @author Daniel Rodriguez Garcia
 */
public class AddCoAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		int shareQuantity = 0;
		boolean validS = true;
		boolean validP = true;
		boolean validN = true;
		String symbol=null;
		double price = 0;
		io.output("\nAdding a new list of Integer values to the system:\n"); 
		String listName = io.getInput("\nEnter name of new Company: ");
		//symbol
		while(validS){
		symbol = io.getInput("\nEnter symbol of Company: ");
		
			if(!(symbol.length()>=3&&symbol.length()<=5)){
			io.output("Symbol can only have from 3 to 5 character\n");
			validS=true;
		}
			else{
			validS=false;	
			}
		
		}
		//price
		while(validP){
			try{
			price = Double.parseDouble(io.getInput("\nEnter price per share: "));
			DecimalFormat decim = new DecimalFormat("0.00");
			String priceS=decim.format(price);
			price=Double.parseDouble(priceS);
			validP=false;
			}
			catch(IllegalArgumentException e){
				io.output("Please enter a valid integer for the quantity of shares\n");
				validP=true;
			}
		}
		
		//numshares
		while(validN){
		try{
		shareQuantity = Integer.parseInt(io.getInput("\nEnter number of shares: "));
		validN=false;
			if(shareQuantity<10000){
				io.output("Please enter a valid integer for the quantity of shares (must be more than 10,000 and less than 2,147,483,647)");
				validN=true;
			}
		}
		catch(IllegalArgumentException e){
			io.output("Please enter a valid integer for the quantity of shares (must be more than 10,000 and less than 2,147,483,647)\n");
			validN=true;
			
		}
		}
		dm.addCo(listName, symbol, price, shareQuantity); 
		
	}

}
