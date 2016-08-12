
package menuClasses;

import java.util.ArrayList;
/**
 * Buy/Sell Menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminBuySellMenu extends Menu {
	private static AdminBuySellMenu ABSM = new AdminBuySellMenu(); 
	private AdminBuySellMenu() { 
		super(); 
		String title; 
		ArrayList<Option> options = new ArrayList<Option>();  
		title = "Buy/Sell Shares"; 
		options.add(new Option("Buy shares", new AddSharesToSH())); 
		options.add(new Option("Sell shares", new SellSharesFromSH()));
		options.add(Option.EXIT); 

		super.InitializeMenu(title, options); 

	}
	
	public static  Menu getOperateListsMenu() { 
		return ABSM; 
	}

}
