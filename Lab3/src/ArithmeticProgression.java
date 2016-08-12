public class  ArithmeticProgression extends Progression<Integer> {
 private int increment;

 /** Constructs progression 0, 1, 2, ... */
 public ArithmeticProgression( ) { this(1, 0); } // start at 0 with increment of 1

 /** Constructs progression 0, stepsize, 2∗stepsize, ... */
 public ArithmeticProgression(int stepsize) {
	 this(stepsize, 0); } // start at 0

 /** Constructs arithmetic progression with arbitrary start and increment. */
 public ArithmeticProgression(int stepsize, int start) {
 super(start);
 increment = stepsize;
 }

 /** Adds the arithmetic increment to the current value. */
 protected void advance( ) {
 current+=increment;
 }

@Override
public Integer nextValue() {
	// TODO Auto-generated method stub
	return current+=increment;
}



 }