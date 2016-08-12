
package menuClasses;

import dataManager.DMComponent;
/**
 * Executes the Admin companies menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminCoAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		dm.getMenuStack().push(AdminCoMenu.getOperateListsMenu()); 
	}

}
