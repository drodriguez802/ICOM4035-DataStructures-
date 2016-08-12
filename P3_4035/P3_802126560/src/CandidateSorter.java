import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Class that organizes the candidates depending on the position they belong to and their index in that position
 * @author Daniel Rodriguez Garcia
 *
 */

public class CandidateSorter {
	int positionsSize;
	PositionSorter pS = new PositionSorter();
	Map<Integer,ArrayList<Candidate>> multiMap;
	public CandidateSorter() throws IllegalStateException{
		int lines = lineReader();
		if(lines%3==0&&lines!=0){
			positionsSize=pS.posNum();
			multiMap = new HashMap<>();
			fillTable();
		}
		else if(lines%3!=0){			
			throw new IllegalStateException("candidates.txt file has incorrect line format");
						
		}
		else{
			throw new IllegalStateException("candidates.txt file its empty");
		
			
		}
		
	}
	//candiates per position counter
	/**
	 * Returns an integer with the number of candidates that are aspiring for a condition
	 * @param position Position to know its size
	 * @return The size or the quantity as an int for candidates aspiring for a position
	 */
	public int CandidatesPerPosition(int position){
		if(multiMap.containsKey(position)){
			return multiMap.get(position).size();
		}
		return -1;
		
	}
	//LINE COUNTER
	/**
	 * Counts the number of lines in the file to know if its correctly formatted
	 * @return returns the number of lines in the File candidates.txt
	 */
	public int  lineReader(){
		try{		
			File file = new File("../ElectionData/candidates.txt");
			int linenumber = 0;
		if(file.exists()){
		    FileReader fr = new FileReader(file);
		    LineNumberReader lnr = new LineNumberReader(fr);
	            while (lnr.readLine() != null){
	        	linenumber++;
	            }
	            lnr.close();
	            return linenumber;
		}
		else{
			throw new IllegalStateException("File candidates.txt does not exists!");
			 
			}
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("Invalid input");
			 
							}
		return 0;
		}
	//GET TABLE
	/**
	 * Getter for the Table of candidates
	 * @return The table that contains the candidates and their positions that contain a Candidate
	 */
	public Map<Integer,ArrayList<Candidate>> getTable(){
		return multiMap;
	}
	//TABLE FILLER
	/**
	 * Fills the table from the File candidates to the system
	 */
	public void fillTable(){
		try{		
			File file = new File("../ElectionData/candidates.txt");
		if(file.exists()){
		    FileReader fr = new FileReader(file);
		    LineNumberReader lnr = new LineNumberReader(fr);
		    Scanner input = new Scanner(fr);
	            while (input.hasNext()){
	            	try{
	            	String name = input.next();
	            	int position = Integer.parseInt(input.next());
	            	int positionIndex = Integer.parseInt(input.next());
	            	ArrayList<Candidate> list;
	            	if(multiMap.containsKey(position)&&!multiMap.get(position).isEmpty()){
	            		list = multiMap.get(position);
	            		list.add(new Candidate(name,positionIndex));
	            		multiMap.put(position, list);
	            	}
	            	else if(!multiMap.containsKey(position)){
	            		list = new ArrayList<>();
	            		list.add(new Candidate(name,positionIndex));
	            		multiMap.put(position,list);
	            	}
	            	}
	            	catch(NoSuchElementException e){
	            
	            		throw new NoSuchElementException("Invalid file format in candidates.txt");
	            	}
	            	
	         
	            	
	            }
	            lnr.close();
		}
		else{
			throw new IllegalStateException("File does not exists!");
			}
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("Invalid input");
		}
	}
	//FINDER
	/**
	 * Finds a candidate's name if any inside the table
	 * @param position Position where the candidate should be
	 * @param index Position Index where the candidate should be
	 * @return A String with the candidates name
	 */
	public String nameFinder(int position, int index){
		String itr = null;
		for(int i=0; i<multiMap.get(position).size();i++){
			if(multiMap.get(position).get(i).getPosition()==index){
				itr=multiMap.get(position).get(i).getName();
				break;
			}
		}
		return itr;
		
	}
	//INTEGER CHECKER
	/**
	 * Checks if a string is a valid integer
	 * @param str String to be checked
	 * @return Boolean value true if its integer false otherwise
	 */
	public static boolean isInteger(String str)  
	{  
	  try  
	  {  
	    int d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
