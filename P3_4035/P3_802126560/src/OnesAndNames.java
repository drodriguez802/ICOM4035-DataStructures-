/**
 * Class that stores the name and the number of 1's a candidate had at the time of its removal
 * @author Daniel Rodriguez Garcia
 *
 */
public class OnesAndNames {
	private String name;
	private int ones;
	public OnesAndNames(String name, int ones){
		this.name=name;
		this.ones=ones;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOnes() {
		return ones;
	}
	public void setOnes(int ones) {
		this.ones = ones;
	}
}
