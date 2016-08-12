/**
 * Class that manages all the methods for administrating the system of companies, shareholders and stocks
 * @author Daniel Rodriguez Garcia
 */
package dataManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

import ioManagementClasses.IOComponent;
import menuClasses.Menu;

/**
 * Data Manager component
 * 
 * @author pedroirivera-vega
 *
 * This class implements and object the groups together all the different 
 * operations with the data that is being managed by the system. In this
 * case, the different lists that the system manages. 
 */
public class DMComponent {
	private Stack<Menu> mStack;          // stack to manage actions in this system
	private ArrayList<Company> cO;
	private ArrayList<StockHolder> sH;
	private ArrayList<Shares> s;
	IOComponent io = IOComponent.getComponent();// list of lists of Integers
	
	public DMComponent() { 
		cO = new ArrayList<Company>(); 
		sH = new ArrayList<StockHolder>();
		s = new ArrayList<Shares>();
		mStack = new Stack<Menu>(); 
	}
	/**
	 * Divides the prices of a certain Company by two and doubles the number of its shares.
	 * @param symbol Symbol of the company that needs to apply the split operation on its shares.
	 */
	public void split(String symbol){
		int index = getIndexForSymbol(symbol); 
		if (index == -1){ 
			IOComponent.getComponent().output("Company " + symbol + " does not exists.\n");
		}
		else if(cO.get(index).coShares.shareQuantity*2>2147483647||cO.get(index).coShares.shareQuantity*2<0){
			io.output("Cannot do that because of internal variable capacity limitations STOP TRYING TO BREAK THE SYSTEM");
		}
		else{
			cO.get(index).coShares.sharePrice=cO.get(index).coShares.sharePrice/2;
			cO.get(index).coShares.shareQuantity=cO.get(index).coShares.shareQuantity*2;
			for(int i=0;i<sH.size();i++){
				for(int j=0;j<sH.get(i).shShares.size();j++){
					if(sH.get(i).shShares.get(j).getSymbol().equals(symbol)){
						sH.get(i).shShares.get(j).shareQuantity=sH.get(i).shShares.get(j).shareQuantity*2;
						sH.get(i).shShares.get(j).sharePrice=sH.get(i).shShares.get(j).sharePrice/2;
					}
				}
			}
			io.output("Shares for the company were split");
		}
	}
	
/**
 * Adds a new Company to the system
 * @param name Name of the company to be added.
 * @param symbol Symbol of the company to be added.
 * @param price Price of shares for the company to be added.
 * @param shareQuantity Share quantity for shares of the company to be added.
 */
	public void addCo(String name, String symbol, double price, int shareQuantity) { 
		int indexS = getIndexForSymbol(symbol);
		if (indexS!=-1) 
			IOComponent.getComponent().output("Company already exists " + name + ".\n");
		else if(shareQuantity>2147483647){
			io.output("Invalid integer input, STOP TRYING TO BREAK THE PROGRAM");
		}
		
		else { 
			Company nlist = new Company(name, symbol, price, shareQuantity); 
			cO.add(nlist); 
			io.output("Succesfully registered \nCompany: "+name+"\nStock symbol: "+symbol+"\nPrice: $"+price+"\nQuantity: "+shareQuantity);
			}
	}
	
	/**
	 * Adds a new ShareHolder to the system.
	 * @param name Name of the ShareHolder being added to the system.
	 * @param id ID of the ShareHolder being added to the system.
	 */
	public void addSH(String name, String id) { 
		
			StockHolder sHlist = new StockHolder(name, id); 
			sH.add(sHlist);
			io.output("Succesfully registered \nName: "+name+"\nShareHolder ID: "+id+"\n");
			
	}
	/**
	 * Shows a company report of its name, symbol, price and share quantity.
	 * @param symbol Symbol of the company to show report from
	 */
	public void showCoReport(String symbol) { 
		int index = getIndexForSymbol(symbol); 
		if (index == -1){
			IOComponent.getComponent().output("Company "+symbol+" does not exists.\n"); 
		
			}
		else { 
			DecimalFormat decimal = new DecimalFormat("0.00");
			String dP = decimal.format(cO.get(index).getSharePrice());
			double sharePrice = Double.parseDouble(dP);
			io.output("\nCompany: "+cO.get(index).getName()+"\nStock symbol: "+cO.get(index).getSymbol()+"\nPrice: $"+sharePrice+"\nQuantity of Company currently"
					+ " owned shares: "+cO.get(index).getShareNum());
			}
	}
	/**
	 * Getter for  the ArrayList for Companies
	 * @return cO List of Companies
	 */
	public ArrayList<Company> getCo() { 
		return cO;
	}
	/**
	 * Getter for  the ArrayList for ShareHolders
	 * @return sH List of ShareHolders
	 */
	public ArrayList<StockHolder> getSH() { 
		return sH;
	}
	/**
	 * Getter for  the ArrayList for SharesO
	 * @return s List of Shares
	 */
	public ArrayList<Shares> getS(){
		return s;
	}
	/**
	 * Shows a report of a ShareHolder given the id of the ShareHolder
	 * @param id The ID of the ShareHolder to show its report
	 */
	public void showSHReport(String id) { 
		int index = getIndexForSH(id); 
		if (index == -1){
			IOComponent.getComponent().output("Stockholder "+id+" does not exists.\n"); 
		
			}
		else { 
			
			io.output("\nStockholder: "+sH.get(index).getName());
			io.output("\nCompany ======== Number of share ======== Price per share");
			for(int i=0; i<sH.get(index).shShares.size();i++){
				DecimalFormat decimal = new DecimalFormat("0.00");
				String dP = decimal.format(sH.get(index).shShares.get(i).getSharePrice());
				double sharePrice = Double.parseDouble(dP);
				io.output("\n"+sH.get(index).shShares.get(i).getSymbol()+"                   "+sH.get(index).shShares.get(i).shareQuantity+"                  "+sharePrice);}
			
		}
	}
	/**
	 * Removes a company given the symbol of it 
	 * @param name The name of the company that wants to be removed
	 */
	public void removeCo(String name){
		int index = getIndexForSymbol(name); 
		if (index == -1){ 
			IOComponent.getComponent().output("Company " + name + " does not exists "+".\n");
		}
		else{
			cO.remove(index);
			IOComponent.getComponent().output("Company " + name + " has been removed"+".\n");
			
		}
		
	}
	/**
	 * Removes a SotckHolder given its unique id
	 * @param id The ID of the ShareHolder that wants to be removed
	 */
	public void removeSH(String id){
		int index = getIndexForSH(id); 
		if (index == -1){ 
			IOComponent.getComponent().output("ShareHolder " + id + " does not exists.\n");
		}
		if(sH.get(index).shShares.size()!=0){
			IOComponent.getComponent().output("ShareHolder " + id + " is still active.\n");
		}
		else{
			String name = sH.get(index).getName();
			sH.remove(index);
			IOComponent.getComponent().output("ShareHolder " + name + " has been removed"+".\n");
			
		}
		
	}

	/**
	 * Adds share to an existing company.
	 * @param name The name of the company to be added shares to
	 * @param value The value which will be added to the company
	 */
	public void addShareToCo(String name, int value) { 
		int index = getIndexForSymbol(name); 
		if (index == -1) 
			IOComponent.getComponent().output("No such list, " + name + ", exists.\n");
		else if(value>2147483647){
			io.output("Invalid integer input, STOP TRYING TO BREAK THE PROGRAM");
			}
		else if((value+cO.get(index).coShares.shareQuantity)>2147483647){
			io.output("Invalid integer input, STOP TRYING TO BREAK THE PROGRAM");
			}
		else { 
			cO.get(index).coShares.shareQuantity+=value;
			
			}

	}
	/**
	 * Gets the index in the ArrayList for a company
	 * @param name The name of the company to be searched
	 * @return Returns the company index number.
	 */
	private int getIndexForSymbol(String name) { 
		for (int i=0; i<cO.size(); i++) 
			if (name.equalsIgnoreCase(cO.get(i).getSymbol())) 
				return i; 
		
		return -1; 
	}
	/**
	 * Gets the index for a ShareHolder given its unique id.
	 * @param name
	 * @return Returns the ShareHolder index number.
	 */
	public int getIndexForSH(String name) { 
		for (int i=0; i<sH.size(); i++) 
			if (name.equals(sH.get(i).getId()))
				return i; 
		
		return -1; 
	}
	
	/**
	 * Returns reference to the stack object used to manage different
	 * states of the system. 
	 * @return reference to the stack. 
	 */
	public Stack<Menu> getMenuStack() { 
		return mStack; 
	}
		
	/**
	 * Inner class defining a company.
	 * @author Daniel Rodriguez Garcia
	 *
	 */
	public static class Company extends ArrayList<Integer> { 
		private Shares coShares;
		
		public Company(String name, String symbol, double price, int shareQuantity) { 
			super(); 
			coShares=new Shares(name, symbol, price, shareQuantity);
		}
		/**
		 * Gets the original number of shares
		 * @return Returns an integer with the number of original shares created
		 */
		public int getOriginalNum(){
			return coShares.getOriginalShares();
		}
		/**
		 * Gets the symbol of a company
		 * @return Returns a string with the symbol of the company being called
		 */
		public String getSymbol() {
			return coShares.getSymbol();
		}
		/**
		 * Gets the current share quantity
		 * @return Returns an integer with the current share quantity
		 */
		public int getShareNum() {
			return coShares.getShareQuantity();
		}
		/**
		 * Gets the current price of a share
		 * @return Returns a double value with the current price of a company share
		 */
		public double getSharePrice() {
			return coShares.getSharePrice();
		}
		/**
		 * Gets the name of the Company
		 * @return Returns a String with the name of the Company
		 */
		public String getName() { 
			return coShares.getName(); 
		}
	}
	
	/**
	 * Inner class defining a ShareHolder.
	 * @author Daniel Rodriguez Garcia
	 *
	 */
	public static class StockHolder extends ArrayList<Integer> { 
		private String name;  
		private String id;
		
		public ArrayList<Shares> getsHShares(){
			return shShares;
		}
		
		private ArrayList<Shares> shShares;
		public StockHolder(String name, String id) { 
			super(); 
			this.name = name;
			this.id=id;
			shShares=new ArrayList<>();
			
			
		}
		/**
		 * Gets the name of a ShareHolder
		 * @return Returns a String with the name of a ShareHolder
		 */
		public String getName() { 
			return name; 
		}
		/**
		 * Gets the ID of a ShareHolder
		 * @return Returns a String with the ID of a ShareHolder
		 */
		public String getId() {
			return id;
		}
		
		
	}
	/**
	 * Inner class defining a Share.
	 * @author Daniel Rodriguez Garcia
	 *
	 */
	public static class Shares extends ArrayList<Double> { 
		private double sharePrice;
		private String name;
		private String id;
		private int shareQuantity;
		private int originalShares;
		public String getId() {
			return id;
		}
		public int getOriginalShares() {
			return originalShares;
		}
		private String symbol;
		public Shares(String name, String id){
			super();
			this.name=name;
			this.id=id;
			
		}
		public Shares(String name, String symbol, double price, int shareQuantity) { 
			super(); 
			this.name = name; 
			this.sharePrice=price;
			this.shareQuantity=shareQuantity;
			this.symbol=symbol;
			this.originalShares=shareQuantity;
		}
		/**
		 * Sets the share price for a Share
		 * @param sharePrice New price to be set
		 */
		public void setSharePrice(double sharePrice) {
			this.sharePrice = sharePrice;
		}
		/**
		 * Sets the share name for a Share
		 * @param name New name for a share
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * Sets the id of a share from a ShareHolder
		 * @param id New id for a share belonging to a ShareHolder
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * Sets the share Quantity for a Share
		 * @param shareQuantity New quantity for an existing share
		 */
		public void setShareQuantity(int shareQuantity) {
			this.shareQuantity = shareQuantity;
		}
		/**
		 * Saves the share original quantity.
		 * @param originalShares Original quantity of shares
		 */
		public void setOriginalShares(int originalShares) {
			this.originalShares = originalShares;
		}
		/**
		 * Sets the company symbol for a Share
		 * @param symbol Company symbol to be applied to a share
		 */
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		/**
		 * Gets the symbol of a share
		 * @return Returns the symbol of a share
		 */
		public String getSymbol() {
			return symbol;
		}
		/**
		 * Gets the name of the copmany or the ShareHolder owner of a share
		 * @return Returns string of the name of the company or the name of a ShareHolder
		 */
		public String getName() { 
			return name; 
		}
		/**
		 * Gets the share price of a share
		 * @return Returns a double value pertaining to the share price
		 */
		public double getSharePrice() {
			return sharePrice;
		}
		/**
		 * Gets the current share quantity of a company
		 * @return
		 */
		public int getShareQuantity() {
			return shareQuantity;
		}
	}
	/**
	 * Sells shares from a ShareHolder to a company
	 * @param nameSH Name of the ShareHolder
	 * @param name name of the Company
	 * @param value Quantity of shares to sell
	 */
	public void sellShareFromSH(String nameSH, String name, int value) { 
		int index = getIndexForSymbol(name); 
		int indexSH = getIndexForSH(nameSH);
		if (index == -1) 
			IOComponent.getComponent().output("No such Company " + name + ", exists.\n"); 
		else if(indexSH==-1){
			IOComponent.getComponent().output("No such ShareHolder " + nameSH + ", exists.\n"); 
		}
		else if(value>2147483647){
			io.output("Invalid integer input, STOP TRYING TO BREAK THE PROGRAM");
		}
		else if(value==0){
			io.output("Quantity 0 not valid to remove");
			
		}
		else { 
			double sum=0;
			int j=0;
			int cValue=value;
//			io.output(+cValue+" "+value+" "+sH.get(indexSH).shShares.size());
			while(value!=0&&j<sH.get(indexSH).shShares.size()){
//				io.output("paso1");
				if(sH.get(indexSH).shShares.get(j).getSymbol().equals(name)){
//					io.output("paso2");
					if(sH.get(indexSH).shShares.get(j).getShareQuantity()>=value){
//						io.output("paso3");
						sum=sum+(sH.get(indexSH).shShares.get(j).shareQuantity*value);
						sH.get(indexSH).shShares.get(j).setShareQuantity(sH.get(indexSH).shShares.get(j).getShareQuantity()-value);
						io.output(""+sum);
						break;
					}
					else if(sH.get(indexSH).shShares.get(j).shareQuantity>0){	
						
						cValue=value-sH.get(indexSH).shShares.get(j).shareQuantity;
						sum=sum+(sH.get(indexSH).shShares.get(j).shareQuantity*sH.get(indexSH).shShares.get(j).sharePrice);
						sH.get(indexSH).shShares.get(j).setShareQuantity(0);
						value=cValue;
						}
					}
				j++;
			}
			DecimalFormat decimal = new DecimalFormat("0.00");
			String tS = decimal.format(sum);
			sum=Double.parseDouble(tS);
			io.output("The net profit of selling those shares is: "+sum);
			
		}
		
	}
	/**
	 * Buys shares to a shareholder from a company.
	 * @param name Name of the ShareHolder
	 * @param value Quantity of share to buy
	 * @param name Name of the Company to buy shares from.
	 */
	public void addShareToSH(String nameSH, String name, int value) { 
		int index = getIndexForSymbol(name); 
		int indexSH = getIndexForSH(nameSH);
		if (index == -1||indexSH==-1) 
			IOComponent.getComponent().output("No such Company " + name + ", exists.\n"); 
		else if(value>2147483647){
			io.output("Invalid integer input, STOP TRYING TO BREAK THE PROGRAM");
		}
		else if(value<=cO.get(index).coShares.shareQuantity) { 
			sH.get(indexSH).shShares.add(new Shares(cO.get(index).getName(), name, cO.get(index).getSharePrice(), value));
			cO.get(index).coShares.shareQuantity=cO.get(index).coShares.shareQuantity-value;
			io.output(nameSH+" has aquired "+value+" shares from Company "+name+" at a price of "+cO.get(index).getSharePrice()+"\n");
		}
		else{
			io.output("Requested Share number exceeds Company available Shares for sale");
		}

	}
	/**
	 * Updates the share values to a new one on an existing company
	 * @param symbol Symbol of the Company which value wants to be changed
	 * @param price The new price to be established
	 */
	public void upShare(String symbol, double price) {
		int index = getIndexForSymbol(symbol); 
		if (index == -1) 
			IOComponent.getComponent().output("No such Company with symbol " + symbol + ", exists.\n"); 
		else { 
			cO.get(index).coShares.sharePrice=price;
		}
	}
	/**
	 * Shows report of stocks for a particular company showing the current owners and how much they own.
	 * @param symbol Symbol of the company to know show the reports of.
	 */
	public void showSReport(String symbol) {
		int index = getIndexForSymbol(symbol);
		if (index == -1) 
			IOComponent.getComponent().output("No such Company with symbol " + symbol + ", exists.\n"); 
		else { 
			io.output("\nShareHolder ======== Number of owned shares");
			for(int i=0;i<sH.size();i++){
				for(int j=0;j<sH.get(i).shShares.size();j++){
					if(sH.get(i).shShares.get(j).getSymbol().equals(symbol))
						io.output("\n"+sH.get(i).getName()+"                   "+sH.get(i).shShares.get(j).shareQuantity);}
				}
			}
		}
		// TODO Auto-generated method stub
		
	}
	

