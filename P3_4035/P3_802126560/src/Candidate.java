/**
 * This class stores the values of Name and Position Index of a candidate.
 * @author Daniel Rodriguez Garcia
 *
 */
public class Candidate {
	private String name;
	private int position;
	
	public Candidate(String name, int position){
		this.name=name;
		this.position=position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
