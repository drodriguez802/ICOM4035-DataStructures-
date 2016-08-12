
package menuClasses;

import java.util.ArrayList;
/**
 * Stock administration menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminStockMenu extends Menu {
	private static AdminStockMenu ACM = new AdminStockMenu(); 
	private AdminStockMenu() { 
		super(); 
		String title; 
		ArrayList<Option> options = new ArrayList<Option>();  
		title = "Share operations"; 
		options.add(new Option("Split shares", new SplitCoShAction())); 
		options.add(new Option("Update share values", new UpShareValAction())); 
		options.add(new Option("Show shares report", new ShowSReportAction())); 
		options.add(Option.EXIT); 

		super.InitializeMenu(title, options); 

	}
	
	public static AdminStockMenu getOperateListsMenu() { 
		return ACM; 
	}
}
