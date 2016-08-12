package listsManagementClasses;

import java.util.ArrayList;

import systemGeneralClasses.FedNumbers;

public class NamedList extends ArrayList<FedNumbers> {

	private String name;
	private FedNumbers number;
	public NamedList(String name) {
		this.name = name;
		this.number = new FedNumbers("0");
	}


	public FedNumbers getNumber() {
		return number;
	}


	public void setNumber(FedNumbers number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

}