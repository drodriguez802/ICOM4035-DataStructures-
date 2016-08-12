
package menuClasses;

import java.util.ArrayList;
/**
 * Main menu
 * @author Daniel Rodriguez Garcia
 */
public class MainMenu extends Menu {
	private static final MainMenu MM = new MainMenu(); 
	private MainMenu() { 
		super(); 
		String title; 
		ArrayList<Option> options = new ArrayList<Option>();  
		title = "Main Menu"; 
		options.add(new Option("Administration of Companies", new AdminCoAction())); 
		options.add(new Option("Administration of Shareholders", new AdminSHAction()));
		options.add(new Option("Administration of Stocks", new AdminStockAction()));
		options.add(new Option("Buy/Sell Transactions", new AdminBuySellAction()));
		options.add(Option.EXIT); 

		super.InitializeMenu(title, options); 
	}
	
	public static MainMenu getMainMenu() { 
		return MM; 
	}
}
