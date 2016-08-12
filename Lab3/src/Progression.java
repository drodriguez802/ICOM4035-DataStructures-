public abstract class Progression<T> {

 // instance variable
 protected T current;

 /** Constructs a progression starting at zero.*/
 
 

 /** Constructs a progression with given start value. */
 public Progression(T e) {
	 current=e;
}

 /** Returns the next value of the progression. 
 * @return */
 public abstract T nextValue();	 
 

 /** Advances the current value to the next value of the progression. */
 protected abstract void advance();
 
 /** Prints the next n values of the progression, separated by spaces. */
 public void printProgression(int n) {
 System.out.print(current); // print first value without leading space
 for (int j=1; j < n; j++)
 System.out.print(" " + nextValue( )); // print leading space before others
 System.out.println( ); // end the line
 }
 }