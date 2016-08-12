
package menuClasses;

import dataManager.DMComponent;
/**
 * Executes the ShareHolder administration menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminSHAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		dm.getMenuStack().push(AdminSHMenu.getOperateListsMenu()); 
	}

}
