package operandHandlers;

import systemGeneralClasses.OperandAnalyzer;


public class OperandValidatorUtils {

	public static boolean isValidName(String operand) {
		if (operand.length() == 0)
			return false;

		// operand is not empty string
		boolean isName = (Character.isLetter(operand.charAt(0)));
		int cp=1;
		while (cp < operand.length() && isName) {
			char c = operand.charAt(cp);
			if (!(Character.isDigit(c) || Character.isLetter(c)))
				isName = false;
			cp++;
		}
		return isName;

	}

	public static boolean isValidFedNum(String operand) {
		if(operand.charAt(0)=='-'||operand.charAt(0)=='+'){
			operand=operand.substring(1);
		}
		if(operand.charAt(operand.length()-1)=='.')
			return false;
		if(operand.charAt(0)=='.'){
			return false;
		}
		int c = 0;
		int point = 0;
		while(c<operand.length()){
			if(operand.charAt(c)=='.'){
				point++;
			}
			else if((operand.charAt(c)<48||operand.charAt(c)>57)||(point>1)){
				return false;
			}

			c++;

		}
		return true;

	}

	public static OperandAnalyzer getAnalyzerFor(String op) {
		if (op.equals("int"))
			return IntOperandAnalyzer.getInstance();
		else if (op.equals("int_list"))
			return IntListOperandAnalyzer.getInstance();
		else if (op.equals("name"))
			return NameOperandAnalyzer.getInstance();

		// need to expand the above if to include for other analyzers that
		// are required...


		// these three are good enough for the moment...
		return null;   // if nothing matches
	}


}
