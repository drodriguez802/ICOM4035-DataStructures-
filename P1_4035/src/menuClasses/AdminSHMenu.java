
package menuClasses;

import java.util.ArrayList;
/**
 * ShareHolder operations menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminSHMenu extends Menu {
	private static AdminSHMenu ASHM = new AdminSHMenu(); 
	private AdminSHMenu() { 
		super(); 
		String title; 
		ArrayList<Option> options = new ArrayList<Option>();  
		title = "ShareHolder operations"; 
		options.add(new Option("Add a new Shareholder", new AddSHAction())); 
		options.add(new Option("Remove an exisiting Shareholder", new RemoveSHAction())); 
		options.add(new Option("Show portfolio for a particular Shareholder", new ShowSHReportAction())); 
		options.add(Option.EXIT); 

		super.InitializeMenu(title, options); 

	}
	
	public static AdminSHMenu getOperateListsMenu() { 
		return ASHM; 
	}
}
