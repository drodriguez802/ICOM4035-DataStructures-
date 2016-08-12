/**
 * Class that organizes the positions depending on the position they belong.
 * @author Daniel Rodriguez Garcia
 *
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
public class PositionSorter {
	String[] positions;
	int lines;
	//constructor
public PositionSorter() throws IllegalStateException{
	lines = lineReader()/2;
	if(lines!=0&&lineReader()%2==0){
		positions = new String[(lineReader()/2)+1];
		fillList();
	}
	else if(lineReader()%2!=0){
		throw new IllegalStateException("Incorrect file format");
		
	}
	else{
		throw new IllegalStateException("positions.txt file its empty");
		
	}
}
//returns number of lines in a file
/**
 * Counts the number of lines in the file to know if its correctly formatted
 * @return returns the number of lines in the File positions.txt
 */
public int  lineReader(){
			try{		
				File file = new File("../ElectionData/positions.txt");
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
				throw new IllegalStateException("File does not exists!");
				}
			}
			catch(IOException e){
				e.printStackTrace();
								}
			return 0;
			}
//fillst he array with the positions names
/**
 * Fills the list from the File candidates to the system
 */
		public void fillList()throws IndexOutOfBoundsException{
			try{		
				File file = new File("../ElectionData/positions.txt");
			if(file.exists()){
				try{
			    FileReader fr = new FileReader(file);
			    LineNumberReader lnr = new LineNumberReader(fr);
			    Scanner input = new Scanner(fr);
		            while (input.hasNext()){
		            	StringTokenizer token = new StringTokenizer(input.nextLine());
		            	String next = token.nextToken();
		            	
		            	if(isInteger(next)&&Integer.parseInt(next)>0&&next.length()==1){
		            		String name = input.nextLine();
		            	
		            		positions[Integer.parseInt(next)]= name;
		        		 	}
		            	else{
		            		throw new IllegalStateException("Invalid file format");
		            	}
		            }
		            lnr.close();
			}
				catch(NoSuchElementException e){
					throw new NoSuchElementException("Invalid file format in positions.txt");
				}
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
		//gets array
		/**
		 * Returns the filled array
		 * @return returns array positions
		 */
		public String[] getTable(){
			return positions;
		}
		//gets num of positions
		/**
		 * Gets the number of total positions that can be runned for
		 * @return number of positions available to run
		 */
		public int posNum(){
			return lines;
		}
		//generic isEmpty method
		public boolean isEmpty(){
			return lines==0;
		}
		//check if its integer
		/**
		 * Checks if the contents of a String can be classified as an integer
		 * @param str String to be verified
		 * @return true if the string can be an integer false otherwise
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