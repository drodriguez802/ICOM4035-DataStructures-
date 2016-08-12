package controlClasses;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import dataManager.DMComponent;
import menuClasses.MainMenu;
import menuClasses.Menu;
import menuClasses.Option;

/**
 * 
 * @author Daniel Rodriguez Garcia
 *
 */
public class p1_main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		DMComponent dm = new DMComponent(); 
		if(args.length>0){
			StringTokenizer token;
			boolean hasFiles=true;
			FileReader in = null;
			try {
				in = new FileReader("StockExchangeData.txt");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				hasFiles=false;
				
				System.out.println("File was not found");
			}
			if(hasFiles){
			Scanner read = new Scanner(in);
			String next=null;
			if(read.hasNext())
				next=read.nextLine();
			while(read.hasNext()){
				
				if(next.equals("==Companies==")){
					next=read.nextLine();
					while(!(next.equals("==StockHolders=="))){
						token=new StringTokenizer(next);
						dm.addCo(token.nextToken(), token.nextToken(), Double.parseDouble(token.nextToken()), Integer.parseInt(token.nextToken()));
						if(read.hasNext())
							next=read.nextLine();
					}
				}
					if(next.equals("==StockHolders==")){
						if(read.hasNext())
							next=read.nextLine();
						
						while(!next.equals(null)&&read.hasNext()){
							token=new StringTokenizer(next);
							token.nextToken();
							
							String name = token.nextToken();
							String id = token.nextToken();
							dm.addSH(name,id);
							if(read.hasNext()){
							next=read.nextLine();
							}
							else{
								break;
							}
							int iS=0;
							
							while(!next.substring(0,12).equals("StockHolder:")){
								
								token = new StringTokenizer(next);
								String cS = token.nextToken();
								int sQ = Integer.parseInt(token.nextToken());
								double sP = Double.parseDouble(token.nextToken());
								dm.addShareToSH(id, cS, sQ);
								int index=dm.getIndexForSH(id);
								dm.getSH().get(index).getsHShares().get(iS).setSharePrice(sP);
								iS++;
								if(read.hasNextLine()){
									next=read.nextLine();
									
								}
								else{
									break;
								}
								
							
							}
						}
							
						
					}
						
				}
					
			}
			
		}
			
		
		Stack<Menu> mStack = dm.getMenuStack();  
		mStack.push(MainMenu.getMainMenu()); 
		while(!mStack.empty()) {
			Option opt = mStack.peek().activate(); 
			opt.getAction().execute(dm); 
		} 
		
	}
}
