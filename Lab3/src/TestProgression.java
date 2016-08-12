public class TestProgression {
 public static void main(String[ ] args) {
 Progression prog;
 // test ArithmeticProgression
 System.out.print("Arithmetic progression with default increment: ");
 prog = new ArithmeticProgression();
 prog.printProgression(10);
 System.out.print("Arithmetic progression with increment 5: ");
 prog = new ArithmeticProgression(5);
 prog.printProgression(10);
 System.out.print("Arithmetic progression with start 2: ");
 prog = new ArithmeticProgression(5, 2);
 prog.printProgression(10);
 // test GeometricProgression

 }
 }