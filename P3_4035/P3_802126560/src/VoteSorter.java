import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * Class that sorts the votes, classifies them as valid or not and stores them in the corresponding positions
 * @author Daniel Rodriguez Garcia
 *
 */
public class VoteSorter {
	int positionsSize;
	Map<Integer, Integer> invalidBallots;
	ArrayList<String> invalidID;
	ArrayList<String> countedInvalidID;
	Map<Integer, Integer> blankBallots;
	Map<Integer, Integer> validBallots;
	Map<Integer, ArrayList<Vote>> ballots;
	PositionSorter pS = new PositionSorter();
	CandidateSorter cS = new CandidateSorter();
	Set<String> hsID;

	public VoteSorter() throws IllegalStateException {
		int lines = lineReader();
		if (lines != 0) {
			positionsSize = pS.posNum();
			ballots = new HashMap<>();
			invalidBallots = new HashMap<>();
			blankBallots = new HashMap<>();
			countedInvalidID = new ArrayList<>();
			validBallots = new HashMap<>();
			hsID = new HashSet<>();
			ballotChecker();
			fillTable();
		} else {
			throw new IllegalStateException("votes.txt file its empty");
		}
	}

	// GETTER FOR TABLE
	/**
	 * Getter for the HashMap with the votes on their positions
	 * @return HashMap with votes in their respective positions
	 */
	public Map<Integer, ArrayList<Vote>> getMap() {
		return ballots;
	}

	// LINE COUNTER
	/**
	 * Counts how many lines the File votes.txt has in order to check for its format
	 * @return and integer with the number of lines the file has
	 */
	public int lineReader() {
		try {
			File file = new File("../ElectionData/votes.txt");
			int linenumber = 0;
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);
				while (lnr.readLine() != null) {
					linenumber++;
				}
				lnr.close();
				return linenumber;
			} else {
				throw new IllegalStateException(
						"File votes.txt does not exists!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid input");
		}
		return 0;
	}

	// BALLOT CHECKER
	/**
	 * Checks for invalid ballots to discard and stores them in their corresponding Maps and ArrayLists
	 */
	public void ballotChecker() {
		ArrayList<String> all = new ArrayList<>();
		ArrayList<String> id = new ArrayList<>();
		ArrayList<String> validIDs = new ArrayList<>();
		ArrayList<String> validBallotStrings = new ArrayList<>();
		ArrayList<String> removedValidBallotStrings = new ArrayList<>();
		
		ArrayList<String> finalList = new ArrayList<>();
		try {
			File file = new File("../ElectionData/votes.txt");
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);
				Scanner input = new Scanner(fr);
				while (input.hasNext()) {
					try {
						all.add(input.nextLine());
					} catch (NoSuchElementException e) {
					}
				}
				for (int i = 0; i < all.size(); i++) {
					String temp = all.get(i);
					StringTokenizer token = new StringTokenizer(temp);
					if (token.countTokens() == 3) {
						temp = temp + "0";
					}
					token = new StringTokenizer(temp);
					int position = Integer.parseInt(token.nextToken());
					String ballot = token.nextToken();
					int positionIndex = Integer.parseInt(token.nextToken());
					int rank = Integer.parseInt(token.nextToken());
					if (position < 1 || position > cS.getTable().size()) {
						id.add(ballot);
					}
					else if(validBallotStrings.contains(temp)){
						id.add(ballot);
					}
					else if (position >= 1
							&& position < pS.getTable().length
							&& (positionIndex > cS
									.CandidatesPerPosition(position))
							|| positionIndex < 1) {
						id.add(ballot);
					}
					else if (position >= 1&& position < pS.getTable().length	&& (positionIndex <= cS.CandidatesPerPosition(position))&& positionIndex > 0&& (rank < 0 || rank > cS
									.CandidatesPerPosition(position))) {
						id.add(ballot);
					}
					else{
						validBallotStrings.add(temp);
						if(validBallots.containsKey(position)&&!validIDs.contains(ballot)&&!id.contains(ballot)){
							int getQ = validBallots.get(position)+1;
							validBallots.put(position, getQ);
							validIDs.add(ballot);
						
						}
						else if(!validBallots.containsKey(position)&&!id.contains(ballot)){
							validBallots.put(position, 1);
							validIDs.add(ballot);
						}
							
					}
					
				}
				hsID.addAll(id);
				id.clear();
				id.addAll(hsID);
				invalidID=id;
			}
			
			
		} catch (IOException e) {
		}
	}

	// TABLE FILLER
	/**
	 * Fills the table with their corresponding if valid votes
	 */
	public void fillTable() {
		try {
			File file = new File("../ElectionData/votes.txt");
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);
				Scanner input = new Scanner(fr);
				while (input.hasNext()) {
					try {
						int position = 0;
						String ballot = "";
						int positionIndex = 0;
						int rank = 0;
						StringTokenizer sT = new StringTokenizer(
								input.nextLine());
						int tokenSize = sT.countTokens();
						position = Integer.parseInt(sT.nextToken());
						ballot = sT.nextToken();
						positionIndex = Integer.parseInt(sT.nextToken());
						if (tokenSize == 4) {
							rank = Integer.parseInt(sT.nextToken());
						}
						if (!invalidID.contains(ballot)) {
							ArrayList<Vote> votes;
							if (!ballots.containsKey(position)) {
								votes = new ArrayList<>();
								votes.add(new Vote(positionIndex, rank));
								ballots.put(position, votes);
							} else if (ballots.containsKey(position)
									&& !ballots.get(position).equals(null)) {
								votes = ballots.get(position);
								votes.add(new Vote(positionIndex, rank));
								ballots.put(position, votes);
							}
						} else if (positionIndex == 0&&position>0&&position<pS.getTable().length) {
							if (blankBallots.containsKey(position)) {
								int quantity = blankBallots.get(position);
								quantity = quantity + 1;
								blankBallots.put(position, quantity);
							} else {
								blankBallots.put(position, 1);
							}
						} else {
							if (position>0&&invalidBallots.containsKey(position)&& !countedInvalidID.contains(ballot)) {
								int quantity = invalidBallots.get(position);
								quantity = quantity + 1;
								countedInvalidID.add(ballot);
								invalidBallots.put(position, quantity);
							} else if (position > 0	&& position < pS.getTable().length	&& !invalidBallots.containsKey(position)) {
								
								invalidBallots.put(position, 1);
								countedInvalidID.add(ballot);
							}
						}
						
					} catch (NoSuchElementException e) {
						throw new NoSuchElementException(
								"Invalid file format in votes.txt");
					}
				}
				lnr.close();
			} else {
				throw new IllegalStateException("File does not exists!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Invalid input");
		}
	}

	// blank ballots getter
	/**
	 * Gets number of blank ballots for a particular position
	 * @param position Position that wants to be know its blank ballots
	 * @return number of blank ballots in the given position
	 */
	public int blankBallots(int position) {
		if (blankBallots.containsKey(position))
			return blankBallots.get(position);
		return 0;
	}

	// invalid ballots getter
	/**
	 * Gets number of invalid ballots for a particular position
	 * @param position Position that wants to be know its invalid ballots
	 * @return number of invalid ballots in the given position
	 */
	public int invalidBallots(int position) {
		if (invalidBallots.containsKey(position))
			return invalidBallots.get(position);
		return 0;
	}

	// CANDIDATES COUNTER PER POSITION
	/**
	 * Counts the candidates per position
	 * @param position Position to be know the number of its candidates
	 * @return Number of the candidates in the given position
	 */
	public int counter(int position) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < ballots.get(position).size(); i++) {
			if (!list.contains(ballots.get(position).get(i).getPosition())) {
				list.add(ballots.get(position).get(i).getPosition());
			}
		}
		return list.size();
	}

	// INTEGER CHECKER
	/**
	 * Checks if the contents of a String can be classified as an integer
	 * @param str String to be verified
	 * @return true if the string can be an integer false otherwise
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
