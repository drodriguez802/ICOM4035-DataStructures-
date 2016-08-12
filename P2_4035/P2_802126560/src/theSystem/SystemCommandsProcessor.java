package theSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import listsManagementClasses.ListsManager;
import listsManagementClasses.NamedList;
import operandHandlers.OperandValidatorUtils;
import stack.IntStack;
import systemGeneralClasses.Command;
import systemGeneralClasses.CommandActionHandler;
import systemGeneralClasses.CommandProcessor;
import systemGeneralClasses.FedNumbers;
import systemGeneralClasses.SystemCommand;
import systemGeneralClasses.VariableLengthCommand;


/**
 *General class for managing with commands the different arithmetical operations with FedNumbers
 * @author Daniel Rodriguez Garcia 4035b5
 *
 */
public class SystemCommandsProcessor extends CommandProcessor {





	

	// To initially place all lines for the output produced after a
	// command is entered. The results depend on the particular command.
	private ArrayList<String> resultsList;

	SystemCommand attemptedSC;
	// The system command that looks like the one the user is
	// trying to execute.

	boolean stopExecution;
	// This field is false whenever the system is in execution
	// Is set to true when in the "administrator" state the command
	// "shutdown" is given to the system.

	////////////////////////////////////////////////////////////////
	// The following are references to objects needed for management
	// of data as required by the particular octions of the command-set..
	// The following represents the object that will be capable of
	// managing the different lists that are created by the system
	// to be implemented as a lab exercise.
	private ListsManager listsManager = new ListsManager();

	/**
	 *  Initializes the list of possible commands for each of the
	 *  states the system can be in.
	 */
	public SystemCommandsProcessor() {

		// stack of states
		currentState = new IntStack();

		// The system may need to manage different states. For the moment, we
		// just assume one state: the general state. The top of the stack
		// "currentState" will always be the current state the system is at...
		currentState.push(GENERALSTATE);

		// Maximum number of states for the moment is assumed to be 1
		// this may change depending on the types of commands the system
		// accepts in other instances......
		createCommandList(1);    // only 1 state -- GENERALSTATE

		// commands for the state GENERALSTATE

		// the following are just for demonstration...
//		add(GENERALSTATE, SystemCommand.getVLSC("testoutput int",
//				new TestOutputProcessor()));        // just for demonstration
//		add(GENERALSTATE, SystemCommand.getVLSC("addnumbers int_list", new AddNumbersProcessor()));
		// just for demonstration

		// the following are for the different commands that are accepted by
		// the shell-like system that manage lists of integers

		// the command to create a new list is treated here as a command of variable length
		// as in the case of command testoutput, it is done so just to illustrate... And
		// again, all commands can be treated as of variable length command...
		// One need to make sure that the corresponding CommandActionHandler object
		// is also working (in execute method) accordingly. See the documentation inside
		// the CommandActionHandler class for testoutput command.
		add(GENERALSTATE, SystemCommand.getVLSC("add name int_list", new AddProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("sub name int_list", new SubProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("mult name int_list", new ProductProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("factorial name int_list", new FactorialProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("var name", new CreateProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("load name", new LoadProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("save name", new SaveProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("show name", new ShowListsProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("delete name", new DeleteProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("factors int_list", new FactorsProcessor()));
		add(GENERALSTATE, SystemCommand.getVLSC("prime int_list", new PrimeProcessor()));
		// the following commands are treated as fixed lentgh commands....
		//add(GENERALSTATE, SystemCommand.getFLSC("showlists", new ShowListsProcessor()));
		//		add(GENERALSTATE, SystemCommand.getFLSC("showall name", new ShowAllProcessor()));
		add(GENERALSTATE, SystemCommand.getFLSC("exit", new ShutDownProcessor()));
		add(GENERALSTATE, SystemCommand.getFLSC("help", new HelpProcessor()));

		// need to follow this pattern to add a SystemCommand for each
		// command that has been specified...
		// ...

		// set to execute....
		stopExecution = false;

	}

	public ArrayList<String> getResultsList() {
		return resultsList;
	}
	

	// INNER CLASSES -- ONE FOR EACH VALID COMMAND --
	/**
	 *  The following are inner classes. Notice that there is one such class
	 *  for each command. The idea is that enclose the implementation of each
	 *  command in a particular unique place. Notice that, for each command,
	 *  what you need is to implement the internal method "execute(Command c)".
	 *  In each particular case, your implementation assumes that the command
	 *  received as parameter is of the type corresponding to the particular
	 *  inner class. For example, the command received by the "execute(...)"
	 *  method inside the "LoginProcessor" class must be a "login" command.
	 *
	 */
	
	/**
	 * Class responsible of printing if a certain number is prime or not with a yes or no
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	public class PrimeProcessor implements CommandActionHandler {@Override
		public ArrayList<String> execute(Command c) {
		resultsList = new ArrayList<String>();
		VariableLengthCommand vlc = (VariableLengthCommand) c;

		if(vlc.getItemsForOperand(1).size()!=1){
			resultsList.add("Invalid command format");
		}
		else if(vlc.getItemsForOperand(1).get(0).charAt(0)=='-'){
			resultsList.add("Cannot use negative numbers");
		}
		else if(FedNumbers.isInteger(new FedNumbers(vlc.getItemsForOperand(1).get(0)))!=-1){
			resultsList.add("Cannot use decimal numbers");
		}


		else {

			String number = vlc.getItemsForOperand(1).get(0);
			FedNumbers factorsFN = new FedNumbers(number);
			if(factorsFN.factors().size()>1){
				resultsList.add("no");
			
			}
			else{
				resultsList.add("yes");
			}

		}


		return resultsList;


	}

	}
	
	/**
	 * Class responisble of printing the prime factors of an integer number
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	public class FactorsProcessor implements CommandActionHandler {@Override
		public ArrayList<String> execute(Command c) {
		resultsList = new ArrayList<String>();
		VariableLengthCommand vlc = (VariableLengthCommand) c;

		if(vlc.getItemsForOperand(1).size()!=1){
			resultsList.add("Invalid command format");
		}
		else if(vlc.getItemsForOperand(1).get(0).charAt(0)=='-'){
			resultsList.add("Cannot use negative numbers");
		}
		else if(FedNumbers.isInteger(new FedNumbers(vlc.getItemsForOperand(1).get(0)))!=-1){
			resultsList.add("Cannot use decimal numbers");
		}


		else {

			String number = vlc.getItemsForOperand(1).get(0);
			FedNumbers factorsFN = new FedNumbers(number);
			ArrayList<FedNumbers> factorsList = factorsFN.factors();
			int counter = 0;
			resultsList.add("FACTORS : OCCURRENCES");
			if(number.equals("1")){
				resultsList.add("     1        1");
				return resultsList;
			}
			ArrayList<FedNumbers> ifHere = new ArrayList<>();
			while(counter<factorsList.size()){
				int occurrences = Collections.frequency(factorsList, factorsList.get(counter));
				if(!ifHere.contains(factorsList.get(counter))){
					resultsList.add("     "+factorsList.get(counter)+"        "+occurrences);
				}
				ifHere.add(factorsList.get(counter));
				counter++;
			}

		}


		return resultsList;


	}
	
	

	}
	/**
	 * Class responsible of deleting a variable
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	public class DeleteProcessor implements CommandActionHandler {
		@Override
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<String>();


			VariableLengthCommand vlc = (VariableLengthCommand) c;

			String name = vlc.getItemsForOperand(1).get(0);

			//FixedLengthCommand fc = (FixedLengthCommand) c;
			//String name = fc.getOperand(1);

			if (!OperandValidatorUtils.isValidName(name))
				resultsList.add("Invalid name formation: " + name);
			else if (!listsManager.nameExists(name))
				resultsList.add("Variable "+name+" does not exists");
			else{
				int index = listsManager.getListIndex(name);
				listsManager.removeElement(index);
			}
			return resultsList;
		}
	}
	/**
	 * Class the loads a file to the system and restores variables values if they already exist, only write the name of the text file without the extension .txt
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	public class LoadProcessor implements CommandActionHandler {
		@Override
		public ArrayList<String> execute(Command c) {
			VariableLengthCommand vlc = (VariableLengthCommand) c;
			String operand = vlc.getItemsForOperand(1).get(0);
			try (BufferedReader br = new BufferedReader(new FileReader(operand+".txt"))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       StringTokenizer token = new StringTokenizer(line);
			       String name = token.nextToken();
			       String value = token.nextToken();
			       int index = listsManager.getListIndex(name);
			       if(index!=-1){
			    	   listsManager.getList().get(index).setNumber(new FedNumbers(value));
			       }
			       else{
			    	   NamedList nl = new NamedList(name);
			    	   nl.setNumber(new FedNumbers(value));
			    	   listsManager.getList().add(nl);
			       }
			    }
			} catch (FileNotFoundException e) {
				
				System.out.println("File does not exists");
			} catch (IOException e) {
				
				System.out.println("File does not exists");
			}
			
			return null;
		}
	}
	/**
	 * Class that saves the current variables to a text file, only write the name of the text file without the extension .txt
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	public class SaveProcessor implements CommandActionHandler {
		@Override
		public ArrayList<String> execute(Command c) {
			VariableLengthCommand vlc = (VariableLengthCommand) c;
			String operand = vlc.getItemsForOperand(1).get(0);
			File output = new File(operand+".txt");
			PrintWriter printOut = null;
			try {
				printOut = new PrintWriter(output);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			int counter=0;
			while(counter<listsManager.size()){
			printOut.println(listsManager.getList().get(counter).getName()+" "+listsManager.getList().get(counter).getNumber());
			counter++;
			}
			printOut.close();
			return null;
		}
	}
	
	
	/**
	 * Class responsible of exiting and closing the system
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	private class ShutDownProcessor implements CommandActionHandler {
		@Override
		public ArrayList<String> execute(Command c) {

			resultsList = new ArrayList<String>();
			resultsList.add("SYSTEM IS SHUTTING DOWN!!!!");
			stopExecution = true;
			return resultsList;
		}
	}


	
	/**
	 * Class responsible of handling the calculation of the factorial of a number
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	public class FactorialProcessor implements CommandActionHandler {
		@Override
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<String>();
			VariableLengthCommand vlc = (VariableLengthCommand) c;

			String name = vlc.getItemsForOperand(1).get(0);

			int index = listsManager.getListIndex(name);
			if (!OperandValidatorUtils.isValidName(name))
				resultsList.add("Invalid name formation: " + name);
			else if(vlc.getItemsForOperand(2).size()!=1){
				resultsList.add("Invalid syntax of the command factorial");
			}

			else if (!listsManager.nameExists(name))
				resultsList.add("Variable with name " + name+" does not exists, therefore nothing can be added to it");
			else if(vlc.getItemsForOperand(2).get(0).charAt(0)=='-'){
				resultsList.add("Cannot use factorial with negative numbers");
			}
			else if(FedNumbers.isInteger(new FedNumbers(vlc.getItemsForOperand(2).get(0)))!=-1){
				resultsList.add("Cannot use factorial with decimal numbers");
			}


			else {
				FedNumbers counter = new FedNumbers("1");
				FedNumbers result = new FedNumbers("1");
				String number = vlc.getItemsForOperand(2).get(0);
				FedNumbers num = new FedNumbers(number);
				while(counter.compareTo(num)<=0){
					result= new FedNumbers(counter.product(result).toString());
					counter.inc();
				}

				listsManager.setElement(index, result);


			}


			return resultsList;


		}
	}

	// classes added for the lab exercise about this project.
	/**
	 * Class responsible for creating variables and initializing them to 0
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	private class CreateProcessor implements CommandActionHandler {
		@Override
		public ArrayList<String> execute(Command c) {

			resultsList = new ArrayList<String>();


			VariableLengthCommand vlc = (VariableLengthCommand) c;

			String name = vlc.getItemsForOperand(1).get(0);

			//FixedLengthCommand fc = (FixedLengthCommand) c;
			//String name = fc.getOperand(1);

			if (!OperandValidatorUtils.isValidName(name))
				resultsList.add("Invalid name formation: " + name);
			else if (listsManager.nameExists(name))
				resultsList.add("Variable "+name+" already exists");
			else
				listsManager.createNewList(name);
			return resultsList;
		}

	}
	//showlist
	/**
	 * Class responsible of showing the value of a variable
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	private class ShowListsProcessor implements CommandActionHandler {
		@Override
		public ArrayList<String> execute(Command c) {
			// command has no operand \AD nothing is needed from the
			// command. if it comes here, it is the showall command....
			resultsList = new ArrayList<String>();
			VariableLengthCommand vlc = (VariableLengthCommand) c;

			String name = vlc.getItemsForOperand(1).get(0);
			int index = listsManager.getListIndex(name);
			if (index == -1)
				resultsList.add("Variable does not exists");
			else {
				resultsList.add(name+" = "+listsManager.getElement(index));
			}
			return resultsList;
		}
	}

	

	//nested class for sub
	/**
	 * Class responsible of calling the subtraction method and subtracting two numbers
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	private class SubProcessor implements CommandActionHandler {


		@Override
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<String>();

			VariableLengthCommand vlc = (VariableLengthCommand) c;
			String name = vlc.getItemsForOperand(1).get(0);



			//			FixedLengthCommand fc = (FixedLengthCommand) c;
			//			String name = fc.getOperand(1);

			if (!OperandValidatorUtils.isValidName(name))
				resultsList.add("Invalid name formation: " + name);
			else if(vlc.getItemsForOperand(2).size()!=2){
				resultsList.add("Invalid command format");
			}
			else if (!listsManager.nameExists(name))
				resultsList.add("Variable with name " + name+" does not exists, therefore nothing can be added to it");
			else {
				int indexL=listsManager.getListIndex(name);
				FedNumbers indexP = null;
				FedNumbers indexV = null;

				try {
					indexP = new FedNumbers(vlc.getItemsForOperand(2).get(0));
					indexV = new FedNumbers(vlc.getItemsForOperand(2).get(1));
				}
				catch(InvalidParameterException e){
					resultsList.add("Please input a valid integer(s)\n");


				}
				FedNumbers result = indexP.substract(indexV);
				listsManager.setElement(indexL, result);
			}

			return resultsList;
		}
		//nested class for mult


	}
	/**
	 * Class responsible of finding the product of two numbers
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	
	private class ProductProcessor implements CommandActionHandler {


		@Override
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<String>();

			VariableLengthCommand vlc = (VariableLengthCommand) c;
			String name = vlc.getItemsForOperand(1).get(0);



			//			FixedLengthCommand fc = (FixedLengthCommand) c;
			//			String name = fc.getOperand(1);

			if (!OperandValidatorUtils.isValidName(name))
				resultsList.add("Invalid name formation: " + name);
			else if(vlc.getItemsForOperand(2).size()!=2){
				resultsList.add("Invalid command format");
			}
			else if (!listsManager.nameExists(name))
				resultsList.add("Variable with name " + name+" does not exists, therefore nothing can be added to it");
			else {
				int indexL=listsManager.getListIndex(name);
				FedNumbers indexP = null;
				FedNumbers indexV = null;

				try {
					indexP = new FedNumbers(vlc.getItemsForOperand(2).get(0));
					indexV = new FedNumbers(vlc.getItemsForOperand(2).get(1));
				}
				catch(InvalidParameterException e){
					resultsList.add("Please input a valid integer(s)\n");


				}
				FedNumbers result = indexP.product(indexV);
				listsManager.setElement(indexL, result);
			}

			return resultsList;
		}

	}

	//nested class for add
	/**
	 * Class responsible for calling the methods to add two numbers
	 * @author Daniel Rodriguez Garcia 4035b5
	 *
	 */
	private class AddProcessor implements CommandActionHandler {


		@Override
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<String>();

			VariableLengthCommand vlc = (VariableLengthCommand) c;
			String name = vlc.getItemsForOperand(1).get(0);



			//			FixedLengthCommand fc = (FixedLengthCommand) c;
			//			String name = fc.getOperand(1);

			if (!OperandValidatorUtils.isValidName(name))
				resultsList.add("Invalid name formation: " + name);
			else if(vlc.getItemsForOperand(2).size()!=2){
				resultsList.add("Invalid command format");
			}
			else if (!listsManager.nameExists(name))
				resultsList.add("Variable with name " + name+" does not exists, therefore nothing can be added to it");
			else {
				int indexL=listsManager.getListIndex(name);
				FedNumbers indexP = null;
				FedNumbers indexV = null;

				try {
					indexP = new FedNumbers(vlc.getItemsForOperand(2).get(0));
					indexV = new FedNumbers(vlc.getItemsForOperand(2).get(1));
				}
				catch(InvalidParameterException e){
					resultsList.add("Please input a valid integer(s)\n");


				}
				FedNumbers result = indexP.sum(indexV);
				listsManager.setElement(indexL, result);
			}

			return resultsList;
		}

	}


	/**
	 *Method that stops the execution of the syssem
	 * @return boolean value to stop execution
	 */
	public boolean inShutdownMode() {
		return stopExecution;
	}

}





