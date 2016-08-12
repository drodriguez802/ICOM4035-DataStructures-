
package menuClasses;

import dataManager.DMComponent;
import ioManagementClasses.IOComponent;
/**
 * Shows the menu to present a ShareHolder report
 * @author Daniel Rodriguez Garcia
 */
public class ShowSHReportAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		IOComponent io = IOComponent.getComponent(); 
		io.output("\nCompany: ");
		String id = io.getInput("Enter the ID of the Sotckholder to get report: "); 
		dm.showSHReport(id);
			
	}

}
