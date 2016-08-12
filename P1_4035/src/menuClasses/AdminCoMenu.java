
package menuClasses;

import java.util.ArrayList;
/**
 * Copmany operation and administration menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminCoMenu extends Menu {
	private static AdminCoMenu ACM = new AdminCoMenu(); 
	private AdminCoMenu() { 
		super(); 
		String title; 
		ArrayList<Option> options = new ArrayList<Option>();  
		title = "Company operations"; 
		options.add(new Option("Add a new Company", new AddCoAction())); 
		options.add(new Option("Remove an exisiting Company", new RemoveCoAction())); 
		options.add(new Option("Add shares to a Company", new AddSharesCo())); 
		options.add(new Option("Show reports for a particular Company", new ShowCoReportAction())); 
		options.add(Option.EXIT); 

		super.InitializeMenu(title, options); 

	}
	
	public static AdminCoMenu getOperateListsMenu() { 
		return ACM; 
	}
}
