package orderedStructures;

public class Geometric extends Progression {

	private double commonFactor; 
	
	public Geometric(double firstValue, double commonFactor) { 
		super(firstValue); 
		this.commonFactor = commonFactor; 
	}
	
	@Override
	public double nextValue() {
		current = current * commonFactor; 
		return current;
	}
	public String toString(){
		String s = "Geom("+(int)super.firstValue()+","+" "+(int)commonFactor+")";
		return s;
		
	}

}
