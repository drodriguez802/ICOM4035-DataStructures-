
package menuClasses;

import dataManager.DMComponent;
/**
 * Executes Stock administration menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminStockAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		dm.getMenuStack().push(AdminStockMenu.getOperateListsMenu()); 
	}

}
