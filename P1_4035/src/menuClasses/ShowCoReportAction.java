
package menuClasses;

import dataManager.DMComponent;
import ioManagementClasses.IOComponent;
/**
 * Shows the menu to present a company report
 * @author Daniel Rodriguez Garcia
 */
public class ShowCoReportAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		io.output("\nCompany: ");
		String symbol = io.getInput("Enter symbol of the Company to get report of: "); 
		dm.showCoReport(symbol);
			
	}

}
