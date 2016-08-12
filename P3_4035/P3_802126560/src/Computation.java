import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * Class that makes all the computations necessary to find a winner from the ballots submitted
 * @author Daniel Rodriguez Garcia
 *
 */

public class Computation {
	VoteSorter vS;
	PositionSorter pS;
	CandidateSorter cS;
	public Computation(){
		vS = new VoteSorter();
		pS = new PositionSorter();
		cS = new CandidateSorter();
		packAndLoad();
	}
	/**
	 * Void method that takes votes already sorted and distributes them by position, then sends them to another method to decide the candidate.
	 */
	public void packAndLoad(){
		int index = 1;
		int position;
		String name;
		while(index<pS.posNum()+1){
			name = pS.getTable()[index];
			ArrayList<ArrayList<Integer>> list = new ArrayList<>(Collections.nCopies(vS.counter(index)+1, null));
			
			
			int pI = 1;
			int pICounter = vS.counter(pI)+1;
			int size = vS.getMap().get(index).size();
			while(pI<pICounter){
				ArrayList<Integer> rank = new ArrayList<>();
				int iC = 0;
			while(iC<size){
				if(vS.getMap().get(index).get(iC).getPosition()==pI){
					
					rank.add(vS.getMap().get(index).get(iC).getRank());
					
				}
				iC++;
				
			}
			Collections.sort(rank);
			
			list.set(pI, rank);
			
			
			pI++;
			
			}
			decider(list,index,vS.validBallots);
			index++;
		}
		
	}
	/**
	 * Method that writes in a file position-name.txt the winner, number of ballots, number of 1's at the moment of elemination or winning
	 * @param tList List with current votes
	 * @param ones Number of ones per positionIndex
	 * @param untouched List with the names and their original positions
	 * @param list Map that contains the 1's per candidate at the moment of their elimination
	 * @param round Current round where the process stopped
	 * @param position Position that is being examined
	 * @param winner String of the name of the winner
	 * @param onesWinner Number of ones the winner had at the time of its victory
	 * @param totalballots Total number of ballots input for the position being examined
	 * @param blankballots Number of blank ballots for the position being examined
	 * @param invalidballots Number of invalid ballots for the position being examined
	 * @throws IOException if some error happens to the file or its name
	 */
	private void writeIn(ArrayList<ArrayList<Integer>>tList,ArrayList<Integer> ones,ArrayList<String>untouched,Map<Integer,ArrayList<OnesAndNames>> list, int round, int position, String winner,int onesWinner, int totalballots, int blankballots, int invalidballots) throws IOException {
		FileWriter input = new FileWriter("../ElectionData/"+pS.getTable()[position]+".txt");
		input.write("TOTAL BALLOTS: "+totalballots);
		input.write(System.getProperty( "line.separator" ));
		input.write("BLANK BALLOTS: "+blankballots);
		input.write(System.getProperty( "line.separator" ));
		input.write("INVALID BALLOTS: "+invalidballots);
		input.write(System.getProperty( "line.separator" ));
		input.write("WINNER: "+winner+" ONES: "+onesWinner);
		input.write(System.getProperty( "line.separator" ));
		input.write("ELIMINATED BY ROUND: ");
		input.write(System.getProperty( "line.separator" ));
		for(int i=1;i<=list.size();i++){
			if(list.containsKey(i)){
				for(int j=0;j<list.get(i).size();j++){
				input.write(list.get(i).get(j).getName()+" "+i+" ONES: "+list.get(i).get(j).getOnes());
				input.write(System.getProperty( "line.separator" ));
				}
			}
		}
		input.close();
		
		 
	}
	private int onesCounter(ArrayList<Integer> list){
		int count = 0 ;
		for(int i = 0;i<list.size();i++){
			if(list.get(i)==1){
				count++;
			}
		}
		return count;
	}
	/**
	 * Arranges all the data and runs the rounds on the positions to decide who's the winner
	 * @param list List with a list of votes per position for the particular position being examined
	 * @param index Index of the position being examined
	 * @param validBallots Map with the number of valid ballots per position
	 */
	public void decider(ArrayList<ArrayList<Integer>> list, int index, Map<Integer,Integer> validBallots ){
		int totalBallots = validBallots.get(index);
		
		ArrayList<ArrayList<Integer>> tList = list;
		ArrayList<String> names = candidateSorter(index);
		ArrayList<String> unNames = names;
		Map<Integer,ArrayList<OnesAndNames>> roundGone = new HashMap<>();
		ArrayList<Integer> onesInRound = new ArrayList<>(Collections.nCopies(cS.CandidatesPerPosition(index)+1, null));
		int candidates = vS.counter(index);
		int round = 1;
		int revIndex = tList.size()-1;
		int largest = revIndex;
		while(tList.size()!=2){
			
			if(oneWinnerCounter(tList)!=-1){
				try{
					
					unNames.set(1, names.get(oneWinnerCounter(tList)));
					
						if(roundGone.containsKey(round)){
							ArrayList<OnesAndNames> temp = roundGone.get(round);
							for(int i=1;i<=roundGone.get(round).size();i++){
							
							if(temp.get(i).getName()!=names.get(oneWinnerCounter(tList))&&temp.get(i).getName()!=null){
								temp.add(new OnesAndNames(names.get(i),onesCounter(tList.get(i))));
							}
							}
							roundGone.put(round, temp);
						}
						
						else{
								ArrayList<OnesAndNames> tempE = new ArrayList<>();
								for(int j=1;j<unNames.size();j++){
									if(unNames.get(j)!=names.get(oneWinnerCounter(tList))&&unNames.get(j)!=null){
										tempE.add(new OnesAndNames(names.get(j),onesCounter(tList.get(j))));
										
									}
								}
								roundGone.put(round, tempE);
							}
					
				writeIn(tList,onesInRound,unNames,roundGone,round,index,names.get(oneWinnerCounter(tList)),onesCounter(tList.get(oneWinnerCounter(tList))),totalBallots,vS.blankBallots(index),vS.invalidBallots(index));
				break;
				
				}
				catch(IOException e){
					
				}
				
				
			}
			revIndex = tList.size()-1;
			largest = revIndex;
			
			while(revIndex>1){
				if(compareTo(tList.get(revIndex),tList.get(revIndex-1))==-1){
					largest=revIndex-1;
					revIndex--;
				}
				else{
					revIndex--;
				}
			}
			
			//onesInRound.set(largest, onesCounter(tList.remove(largest)));
			if(roundGone.containsKey(round)){
				ArrayList<OnesAndNames> temp = roundGone.get(round);
				temp.add(new OnesAndNames(names.remove(largest),onesCounter(tList.remove(largest))));
			}
			else{
				ArrayList<OnesAndNames> temp = new ArrayList<>();
				temp.add(new OnesAndNames(names.remove(largest),onesCounter(tList.remove(largest))));
				roundGone.put(round, temp);
			}
			//**************remover uno de el numero mayor de las listas restantes
			reducer(tList, largest);
			candidates--;
			round++;
		}
//		System.out.println("FINAL VOTE: "+tList);
//		System.out.println("WINNER: "+names);
//		System.out.println("GONE: "+roundGone);
//		System.out.println("BLANK 1: "+vS.blankBallots(index));
//		System.out.println("INVALID 1: "+vS.invalidBallots(index));
		try{
			writeIn(tList,onesInRound,unNames,roundGone,round,index,names.get(1),onesCounter(tList.get(1)),totalBallots,vS.blankBallots(index),vS.invalidBallots(index));
			
			}
			catch(IOException e){
				
			}
			
			
		
		
	}	
	/**
	 * Returns an int depending if it found a list with a half of more of its contents being 1's
	 * @param list List of votes as integers
	 * @return -1 if it didnt had more than half of the list being 1's or and integer with the positionIndex of the candidate that had more than half of its votes being 1's
	 */
	private int oneWinnerCounter(ArrayList<ArrayList<Integer>> list){
		//TODO
		int size = list.get(1).size();
		int limit = size/2;
		for(int i=1;i<list.size();i++){
			int index = 0;
			int ones = 0;
			while(index<list.get(i).size()){
				if(list.get(i).get(index)==1){
					ones++;
				}
				index++;
			}
			if(ones>=limit){
				return i;
			}
			
		}
		
		return -1;
	}
	/**
	 * Sorts the candidates and returns their names in ther positionIndexes depending on a position given
	 * @param index Position to know the candiates names
	 * @return An ArrayList of type Integer with the names of the candidates for that given position
	 */
	private ArrayList<String> candidateSorter(int index){
		ArrayList<String> names = new ArrayList<>(Collections.nCopies(cS.CandidatesPerPosition(index)+1, null));
		for(int i=0;i<cS.CandidatesPerPosition(index);i++){
			names.set(cS.getTable().get(index).get(i).getPosition(), cS.getTable().get(index).get(i).getName());
			
		}
		return names;
	}
	/**
	 * Decrements by 1 all numbers that are greater than the current element positionIndex being removed from the list
	 * @param tList List of votes as Integers
	 * @param largest PositionIndex of the element being removed
	 */
	private void reducer(ArrayList<ArrayList<Integer>> tList, int largest) {
		for(int i=1;i<tList.size();i++){
			for(int j=0;j<tList.get(i).size();j++){
				
				if(tList.get(i).get(j)>largest){
					tList.get(i).set(j, tList.get(i).get(j)-1);
				}
			}
			
		}
		
	}
	public int compareTo(ArrayList<Integer> a, ArrayList<Integer> b){
		int size = a.size();
		int counter = 0;
		while(counter<size){
			try{
			if(a.get(counter)>b.get(counter)){
				return 1;
			}
			else if(a.get(counter)<b.get(counter)){
				return -1;
			}
			counter++;	
			}
			catch(IndexOutOfBoundsException e){
				if(b.size()>a.size()){
					return -1;
				}
				else{
					return 1;
				}
			}
		}
		return 0;
		
	}
	

	
}
