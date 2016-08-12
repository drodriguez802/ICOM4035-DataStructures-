/**
 * Class that stores votes by their positionIndex and the rank given to that positionIndex
 * @author Daniel Rodriguez Garcia
 *
 */
public class Vote {
	private int position;
	private int rank;
	
	public Vote(int position,int rank){
		this.position=position;
		this.rank=rank;
		
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
