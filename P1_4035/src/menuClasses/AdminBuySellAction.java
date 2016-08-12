
package menuClasses;

import dataManager.DMComponent;
/**
 * Executes Buy/Sell Menu
 * @author Daniel Rodriguez Garcia
 */
public class AdminBuySellAction implements Action {

	@Override
	public void execute(Object arg) {
		DMComponent dm = (DMComponent) arg; 
		dm.getMenuStack().push(AdminBuySellMenu.getOperateListsMenu()); 
	}

}
