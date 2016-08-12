package orderedStructures;

public class Arithmetic extends Progression {
	
	private double commonDifference; 
	
	public Arithmetic(double firstValue, double commonDifference) { 
		super(firstValue); 
		this.commonDifference = commonDifference; 
	}
	
	@Override
	public double nextValue() {
		
		current = current + commonDifference; 
		return current;
	}
	public String toString(){
		String s = "Arith("+(int)super.firstValue()+","+" "+(int)commonDifference+")";
		return s;
		
	}

}
