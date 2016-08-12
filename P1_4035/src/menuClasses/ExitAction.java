
package menuClasses;

import ioManagementClasses.IOComponent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import dataManager.DMComponent;
/**
 * Implements the exit action and how it saves the current data on the system for future use
 * @author Daniel Rodriguez Garcia
 */
public class ExitAction implements Action {

	@Override
	public void execute(Object arg){
		DMComponent dm = (DMComponent) arg;
		IOComponent io = IOComponent.getComponent();
		dm.getMenuStack().pop(); 
		if(dm.getMenuStack().isEmpty()){
			File file = new File ("StockExchangeData.txt");
		    PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter (file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printWriter.println("==Companies==");
			for(int i=0;i<dm.getCo().size();i++){
				printWriter.println(dm.getCo().get(i).getName()+" "+dm.getCo().get(i).getSymbol()+" "+dm.getCo().get(i).getSharePrice()+" "+dm.getCo().get(i).getOriginalNum()+" ");
				
			}
			printWriter.println("==StockHolders==");
			for(int i=0;i<dm.getSH().size();i++){
				printWriter.println("StockHolder: "+dm.getSH().get(i).getName()+" "+dm.getSH().get(i).getId()+" ");
				for(int j=0;j<dm.getSH().get(i).getsHShares().size();j++){
					printWriter.println(dm.getSH().get(i).getsHShares().get(j).getSymbol()+" "+dm.getSH().get(i).getsHShares().get(j).getShareQuantity()+" "+dm.getSH().get(i).getsHShares().get(j).getSharePrice()+" ");
				}
				
			}
			printWriter.close();
		}
	}

}
