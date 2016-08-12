package systemGeneralClasses;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class that implements all the operations with big numbers as FedNumbers, such as factorization, prime search, multiplication, subtraction, division and sum.
 * @author Daniel Rodriguez Garcia 4035b5
 *
 */
public class FedNumbers implements Comparable<FedNumbers>{
	char[] numberArray;
	String number;
	LinkedPositionalList<Character> numList;
	/**
	 * FedNumbers constructor, initializes a string and an array, and assigns the parameter number to the String number.
	 * @param number Number to be assigned a FedNumbers ODT
	 */
	public FedNumbers(String number){
		numList=new LinkedPositionalList<>();
		this.number=number;
		enlist(this.number);
	}
	/**
	 * 	Sets the first position of the array to the type of number(integer or decimal) 
	 * 	and distributes the number to the other positions
	 * @param number The number represented as a String to be converted to FedNumbers
	 */

	protected void enlist(String number) {
		for(int i=0;i<number.length();i++){
			numList.addLast(number.charAt(i));
		}
	}
	/**
	 * Increments the calling variable by one(variable++)
	 */
	public void inc(){
		FedNumbers inc = this.sum(new FedNumbers("1"));
		if(inc.numList.first().getElement().equals('+')){
			inc.numList.remove(inc.numList.first());
		}
		this.numList=inc.numList;
	}
	/**
	 * Returns the residue between two numbers
	 * @param b divisor of the equation
	 * @return the residue of the numbers sent in the parameters this and b
	 */
	public FedNumbers residue(FedNumbers b){
		FedNumbers counter = new FedNumbers("1");
		FedNumbers one = new FedNumbers("1");
		FedNumbers producT = b.product(counter);
		while(producT.compareTo(this)<=0){
			counter.inc();
			producT=b.product(counter);
		}
		counter=counter.substract(one);
		producT=b.product(counter);
		FedNumbers result = this.substract(producT);
		if(result.numList.first().getElement().equals(('+'))||result.numList.first().getElement().equals('-'))
			result.numList.remove(result.numList.first());
		if(result.numList.size()>1){
			while(result.numList.first().getElement().equals(('0'))&&result.numList.size()>1){
				result.numList.remove(result.numList.first());
			}
		}
		return result;
	}
	/**
	 * Divides two numbers and returns its residue
	 * @param b divisor of the equation
	 * @return Returns the residue of the integer division between this and b
	 */
	public FedNumbers divideR(FedNumbers b){
		FedNumbers B = new FedNumbers(b.toString());
		FedNumbers THIS = new FedNumbers(this.toString());
		String quotient="";
		String residue="";
		FedNumbers residueFN;
		FedNumbers quotientFN;
		Position<Character> index = THIS.numList.first();
		while(index!=null){
			residue=residue+index.getElement();
			residueFN = new FedNumbers(residue);
			if(B.compareTo(residueFN)>0){
				quotient=quotient+'0';
			}
			else if(B.compareTo(residueFN)==0){
				quotient=quotient+'1';
				residue="";
			}
			else{
				String tempR=residueFN.residue(B).toString();
				String tempQ=new FedNumbers(residue).quotient(B).toString();
				residue=tempR;
				quotient=quotient+tempQ;
				residueFN=new FedNumbers(residue);
				if(residueFN.compareTo(new FedNumbers("0"))==0){
					residue="";
				}
			}
			index=THIS.numList.after(index);
		}
		quotientFN = new FedNumbers(quotient);
		FedNumbers result = THIS.substract((quotientFN).product(B));
		if(result.numList.first().getElement().equals(('+'))||result.numList.first().getElement().equals('-'))
			result.numList.remove(result.numList.first());
		if(result.numList.size()>1){
			while(result.numList.first().getElement().equals(('0'))&&result.numList.size()>1){
				result.numList.remove(result.numList.first());
			}
		}
		return result;
	}
	/**
	 * Divides two numbers and returns its quotient
	 * @param b divisor of the equation
	 * @return Returns the quotient of the integer division between this and b
	 */
	public FedNumbers divideQ(FedNumbers b){
		FedNumbers B = new FedNumbers(b.toString());
		FedNumbers THIS = new FedNumbers(this.toString());
		String quotient="";
		String residue="";
		FedNumbers residueFN;
		FedNumbers quotientFN;
		Position<Character> index = THIS.numList.first();
		while(index!=null){
			residue=residue+index.getElement();
			residueFN = new FedNumbers(residue);
			if(B.compareTo(residueFN)>0){
				quotient=quotient+'0';
			}
			else if(B.compareTo(residueFN)==0){
				quotient=quotient+'1';
				residue="";
			}
			else{
				String tempR=residueFN.residue(B).toString();
				String tempQ=new FedNumbers(residue).quotient(B).toString();
				residue=tempR;
				quotient=quotient+tempQ;
				residueFN=new FedNumbers(residue);
				if(residueFN.numList.first().getElement().equals(('+'))||residueFN.numList.first().getElement().equals('-'))
					residueFN.numList.remove(residueFN.numList.first());
				if(residueFN.numList.size()>1){
					while(residueFN.numList.first().getElement().equals(('0'))&&residueFN.numList.size()>1){
						residueFN.numList.remove(residueFN.numList.first());
					}
				}
				if(residueFN.compareTo(new FedNumbers("0"))==0){
					residue="";
				}
			}
			index=THIS.numList.after(index);
		}
		quotientFN = new FedNumbers(quotient);
		FedNumbers result = quotientFN;
		if(result.numList.first().getElement().equals(('+'))||result.numList.first().getElement().equals('-'))
			result.numList.remove(result.numList.first());
		if(result.numList.size()>1){
			while(result.numList.first().getElement().equals(('0'))&&result.numList.size()>1){
				result.numList.remove(result.numList.first());
			}
		}
		return result;
	}
	/**
	 * Returns the quotient between two numbers
	 * @param b divisor of the equation
	 * @return Returns the quotient between this and b
	 */
	public FedNumbers quotient(FedNumbers b){
		FedNumbers counter = new FedNumbers("1");
		FedNumbers one = new FedNumbers("1");
		FedNumbers producT = b.product(counter);
		while(producT.compareTo(this)<=0){
			counter.inc();
			producT=b.product(counter);
		}
		counter=counter.substract(one);
		if(counter.numList.first().getElement().equals(('+'))||counter.numList.first().getElement().equals('-'))
			counter.numList.remove(counter.numList.first());
		if(counter.numList.size()>1){
			while(counter.numList.first().getElement().equals(('0'))){
				counter.numList.remove(counter.numList.first());
			}
		}
		return counter;
	}
	/**
	 * Calculates the factorial of a given number
	 * @return returns the result of n! where n is the given number in the parameter this
	 */
	public  FedNumbers factorial(){
		FedNumbers counter = new FedNumbers("1");
		FedNumbers result = new FedNumbers("1");
		while(counter.compareTo(this)<=0){
			result= new FedNumbers(counter.product(result).toString());
			counter.inc();
		}
		return result;
	}
	/**
	 * Checks if a numbers is integer
	 * @param number FedNumber number to be checked if its integer
	 * @return returns a -1 if the number is integer, or any other number representing the number of positions after the point implying its not an integer
	 */
	public static int isInteger(FedNumbers number){
		int i=-1;
		Iterator<Character> iterator = number.numList.iterator();
		while(iterator.hasNext()){
			char temp=iterator.next();
			if(temp=='.'){
				i=0;
			}
			else if(i>=0){
				i++;
			}
		}
		return i;
	}
	/**
	 * Sums two numbers, integer and non-integer, positive and negative
	 * @param b the second number
	 * @return the sum of the two numbers given, this and b
	 */
	public FedNumbers sum(FedNumbers b){
		int carry=0;
		int current=0;
		String newNumber="";
		String bSign="";
		String tSign="";
		boolean bHasSign=false;
		boolean tHasSign=false;
		LinkedPositionalList<Character> t = null;
		LinkedPositionalList<Character> B = null;
		//Check for sign
		if(this.numList.first().getElement()=='+'||this.numList.first().getElement()=='-'){
			tSign=this.numList.first().getElement()+tSign;
			tHasSign=true;
			this.numList.remove(this.numList.first());
		}
		if(b.numList.first().getElement()=='+'||b.numList.first().getElement()=='-'){
			bSign=b.numList.first().getElement()+bSign;
			bHasSign=true;
			b.numList.remove(b.numList.first());
		}
		//CHECK FOR INTEGER
		if(isInteger(b)!=-1){
			//this is not integer
			if(isInteger(this)!=-1){
				if((isInteger(b)>=isInteger(this))){
					int diff=isInteger(b)-isInteger(this);
					int cD=0;
					while(cD<diff){
						cD++;
						this.numList.addLast('0');
					}
				}
				else{
					int diff=isInteger(this)-isInteger(b);
					int cD=0;
					while(cD<diff){
						cD++;
						b.numList.addLast('0');
					}
				}
			}
			else{
				int diff=isInteger(b);
				int cD=0;
				this.numList.addLast('.');
				while(cD<diff){
					cD++;
					this.numList.addLast('0');
				}
			}
		}
		else if(isInteger(this)!=-1){
			//this is not integer
			if(isInteger(b)!=-1){
				if((isInteger(this)>=isInteger(b))){
					int diff=isInteger(this)-isInteger(b);
					int cD=0;
					while(cD<diff){
						cD++;
						b.numList.addLast('0');
					}
				}
				else{
					int diff=isInteger(b)-isInteger(this);
					int cD=0;
					while(cD<diff){
						cD++;
						this.numList.addLast('0');
					}
				}
			}
			else{
				int diff=isInteger(this);
				int cD=0;
				b.numList.addLast('.');
				while(cD<diff){
					cD++;
					b.numList.addLast('0');
				}
			}
		}
		if(this.numList.size()>b.numList.size()){
			int goal = this.numList.size()-b.numList.size();
			int count =0;
			while(count<goal){
				b.numList.addFirst('0');
				count++;
			}
		}
		else if(b.numList.size()>this.numList.size()){
			int goal = b.numList.size()-this.numList.size();
			int count =0;
			while(count<goal){
				this.numList.addFirst('0');
				count++;
			}
		}
		Position<Character> indexT = null;
		Position<Character> indexB = null;
		String finalSign = "";
		if(this.compareTo(b)>=0){
			t = this.numList;
			B = b.numList;
			indexT = t.last();
			indexB = B.last();
			if(!tHasSign||tSign.equals("+")){
				if(bHasSign&&!bSign.equals(tSign)){
					return this.substract(b);
				}
			}
			else if(tSign.equals("-")){
				if(bSign.equals("+")||!bHasSign){
					return b.substract(this);
				}
				else if(tSign.equals("-")||!tHasSign){
					finalSign='-'+finalSign;
				}
			}
		}
		else if(b.compareTo(this)>0){
			t = b.numList;
			B = this.numList;
			indexT = t.last();
			indexB = B.last();
			if(tSign.equals("-")){
				if(bSign.equals("+")||!bHasSign){
					return b.substract(this);
				}
				else if(tSign.equals("-")&&(bSign.equals("-"))){
					finalSign='-'+finalSign;
				}
			}
			else if(!tHasSign||tSign.equals("+")){
				if(bSign.equals("-"))
					return this.substract(b);
			}
		}
		while(indexB!=null){
			if(indexB.getElement()!='.'){
				if(carry==0){
					current=Character.getNumericValue(indexT.getElement())+Character.getNumericValue(indexB.getElement());
					if(current>9){
						String tNumber=String.valueOf(current);
						newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
						carry=1;
					}
					else{
						String tNumber=String.valueOf(current);
						newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
						carry=0;
					}
					indexT=t.before(indexT);
					indexB=B.before(indexB);
				}
				else{
					current=Character.getNumericValue(indexT.getElement())+Character.getNumericValue(indexB.getElement())+1;
					if(current>9){
						String tNumber=String.valueOf(current);
						newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
						carry=1;
					}
					else{
						String tNumber=String.valueOf(current);
						newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
						carry=0;
					}
					indexT=t.before(indexT);
					indexB=B.before(indexB);
				}
			}
			else{
				newNumber='.'+newNumber;
				indexT=t.before(indexT);
				indexB=B.before(indexB);
			}
		}
		while(indexT!=null){
			if(carry==0){
				current=Character.getNumericValue(indexT.getElement());
				if(current>9){
					String tNumber=String.valueOf(current);
					newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
					carry=1;
				}
				else{
					String tNumber=String.valueOf(current);
					newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
					carry=0;
				}
				indexT=t.before(indexT);
			}
			else{
				current=Character.getNumericValue(indexT.getElement())+ 1;
				if(current>9){
					String tNumber=String.valueOf(current);
					newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
					carry=1;
				}
				else{
					String tNumber=String.valueOf(current);
					newNumber=(tNumber.charAt(tNumber.length()-1))+newNumber;
					carry=0;
				}
				indexT=t.before(indexT);
			}
		}
		if(carry!=0){
			newNumber='1'+newNumber;
		}
		newNumber=finalSign+newNumber;
		FedNumbers result = new FedNumbers(newNumber);
		return result;
	}
	/**
	 * Subtracts two numbers, integer and non-integer, positive and negative
	 * @param b the second number
	 * @return the difference of the two numbers given, this and b
	 */
	public FedNumbers substract(FedNumbers b){
		String newNumber="";
		String bSign="";
		String tSign="";
		boolean bHasSign=false;
		boolean tHasSign=false;
		LinkedPositionalList<Character> t = null;
		LinkedPositionalList<Character> B = null;
		//Check for sign
		if(this.numList.first().getElement()=='+'||this.numList.first().getElement()=='-'){
			tSign=this.numList.first().getElement()+tSign;
			tHasSign=true;
			this.numList.remove(this.numList.first());
		}
		if(b.numList.first().getElement()=='+'||b.numList.first().getElement()=='-'){
			bSign+=b.numList.first().getElement();
			bHasSign=true;
			b.numList.remove(b.numList.first());
		}
		//CHECK FOR INTEGER
		if(isInteger(this)==-1){
			while(this.numList.first().getElement().equals('0')&&this.numList.size()>1){
				this.numList.remove(this.numList.first());
			}
		}
		if(isInteger(b)==-1){
			while(b.numList.first().getElement().equals('0')&&b.numList.size()>1){
				b.numList.remove(b.numList.first());
			}
		}
		if(isInteger(b)!=-1){
			//this is not integer
			if(isInteger(this)!=-1){
				if((isInteger(b)>=isInteger(this))){
					int diff=isInteger(b)-isInteger(this);
					int cD=0;
					while(cD<diff){
						cD++;
						this.numList.addLast('0');
					}
				}
				else{
					int diff=isInteger(this)-isInteger(b);
					int cD=0;
					while(cD<diff){
						cD++;
						b.numList.addLast('0');
					}
				}
			}
			else{
				int diff=isInteger(b);
				int cD=0;
				this.numList.addLast('.');
				while(cD<diff){
					cD++;
					this.numList.addLast('0');
				}
			}
		}
		else if(isInteger(this)!=-1){
			//this is not integer
			if(isInteger(b)!=-1){
				if((isInteger(this)>=isInteger(b))){
					int diff=isInteger(this)-isInteger(b);
					int cD=0;
					while(cD<diff){
						cD++;
						b.numList.addLast('0');
					}
				}
				else{
					int diff=isInteger(b)-isInteger(this);
					int cD=0;
					while(cD<diff){
						cD++;
						this.numList.addLast('0');
					}
				}
			}
			else{
				int diff=isInteger(this);
				int cD=0;
				b.numList.addLast('.');
				while(cD<diff){
					cD++;
					b.numList.addLast('0');
				}
			}
		}
		if(this.numList.size()>b.numList.size()){
			int goal = this.numList.size()-b.numList.size();
			int count =0;
			while(count<goal){
				b.numList.addFirst('0');
				count++;
			}
		}
		else if(b.numList.size()>this.numList.size()){
			int goal = b.numList.size()-this.numList.size();
			int count =0;
			while(count<goal){
				this.numList.addFirst('0');
				count++;
			}
		}
		Position<Character> indexT = null;
		Position<Character> indexB = null;
		String finalSign="";
		//CHECK FOR LENGTH
		if(this.compareTo(b)>=0){
			t = this.numList;
			B = b.numList;
			indexT = t.last();
			indexB = B.last();
			if(tHasSign&&tSign.equals("-")){
				if(bHasSign){
					if(!bSign.equals(tSign)){
						this.numList.addFirst('-');
						b.numList.addFirst('+');
						return this.sum(b);
					}
					else if(this.compareTo(b)!=0){
						finalSign='-'+finalSign;
					}
				}
				else{
					this.numList.addFirst('-');
					b.numList.addFirst('-');
					return this.sum(b);
				}
			}
			else if(!tHasSign||tSign.equals("+")){
				if(!bHasSign||bSign.equals(tSign)){
					finalSign='+'+finalSign;
				}
				else if(bHasSign&&!bSign.equals("+")){
					return this.sum(b);
				}
			}
		}
		else if(b.compareTo(this)>0){
			t = b.numList;
			B = this.numList;
			indexT = t.last();
			indexB = B.last();
			if(bHasSign&&bSign.equals("+")||!bHasSign){
				if(!tHasSign||!tSign.equals(bSign)){
					finalSign='-'+finalSign;
				}
				else if(tHasSign&&tSign.equals(bSign)){
					finalSign='-'+finalSign;
				}
			}
			else if(bSign.equals("-")){
				if(!tHasSign||tSign.equals("+")){
					return this.sum(b);
				}
				else if(bSign.equals(tSign)){
					finalSign='+'+finalSign;
				}
			}
		}
		while(indexB!=null){
			if(indexB.getElement()!='.'){
				int temp1 = Character.getNumericValue(indexT.getElement());
				int temp2 = Character.getNumericValue(indexB.getElement());
				if(temp1-temp2<0){
					Position<Character> temp = t.before(indexT);
					if(temp.getElement()!='.'){
						int tempN = Character.getNumericValue(temp.getElement())-1;
						String tempS="";
						tempS=tempN+tempS;
						t.set(temp, tempS.charAt(0));
						temp1+=10;
						int tempResult=temp1-temp2;
						newNumber=tempResult+newNumber;
					}
					else{
						Position<Character> tempD = t.before(temp);
						if(tempD!=null){
							int tempN = Character.getNumericValue(tempD.getElement())-1;
							String tempS="";
							tempS=tempN+tempS;
							t.set(tempD, tempS.charAt(0));
							temp1+=10;
							int tempResult=temp1-temp2;
							newNumber=tempResult+newNumber;
						}
					}
				}
				else{
					newNumber=(temp1-temp2)+newNumber;
				}
			}
			else{
				newNumber='.'+newNumber;
			}
			indexT=t.before(indexT);
			indexB=B.before(indexB);
		}
		while(indexT!=null){
			newNumber=indexT.getElement()+newNumber;
			indexT=t.before(indexT);
		}
		if(finalSign!=null){
			newNumber=finalSign+newNumber;
		}
		FedNumbers result = new FedNumbers(newNumber);
		return result;
	}
	/**
	 * Product two numbers, integer and non-integer, positive and negative
	 * @param b the second number
	 * @return the product of the two numbers given, this and b
	 */
	public FedNumbers product(FedNumbers b){
		FedNumbers BP = new FedNumbers(b.toString());
		FedNumbers THIS = new FedNumbers(this.toString());
		if(THIS.numList.first().getElement()=='-'||THIS.numList.first().getElement()=='+'){
			if(THIS.numList.after(THIS.numList.first()).equals('.')){
				THIS.numList.addAfter(THIS.numList.first(), '0');
			}
		}
		else if(THIS.numList.first().getElement()=='.'){
			THIS.numList.addFirst('0');
		}
		//B
		if(BP.numList.first().getElement()=='-'||BP.numList.first().getElement()=='+'){
			if(BP.numList.after(BP.numList.first()).getElement().equals('.')){
				BP.numList.addAfter(BP.numList.first(), '0');
			}
		}
		else if(BP.numList.first().getElement()=='.'){
			BP.numList.addFirst('0');
		}
		int tPosAfterDot = isInteger(THIS);
		int bPosAfterDot = isInteger(BP);
		int dotPositions=0;
		if(tPosAfterDot>0&&bPosAfterDot>0)
			dotPositions = tPosAfterDot+bPosAfterDot;
		else if(tPosAfterDot>0&&bPosAfterDot<0){
			dotPositions=tPosAfterDot;
		}
		else if(tPosAfterDot<0&&bPosAfterDot>0){
			dotPositions=bPosAfterDot;
		}
		char finalSign='+';
		//signcheck
		char tSign = THIS.numList.first().getElement();
		char bSign = BP.numList.first().getElement();
		if((tSign=='-'&&bSign=='+')||(tSign=='+'&&bSign=='-')||(tSign=='-'&&(bSign!='+'&&bSign!='-'))||((tSign!='+'&&tSign!='-')&&bSign=='-')){
			finalSign='-';
		}
		else if((tSign=='-'&&bSign=='-')||(tSign=='+'&&bSign=='+')||(tSign=='+'&&(bSign!='+'&&bSign!='-'))||((tSign!='+'&&(bSign!='+'&&bSign!='-')))){
			finalSign='+';
		}
		if(THIS.numList.first().getElement().equals('+')||THIS.numList.first().getElement().equals('-')){
			THIS.numList.remove(THIS.numList.first());
		}
		if(BP.numList.first().getElement().equals('+')||BP.numList.first().getElement().equals('-')){
			BP.numList.remove(BP.numList.first());
		}
		Position<Character> indexT;
		Position<Character> indexB;
		LinkedPositionalList<Character> T;
		LinkedPositionalList<Character> B;
		if(THIS.compareTo(b)>=0){
			T = THIS.numList;
			B = BP.numList;
			indexT=THIS.numList.last();
			indexB=BP.numList.last();
		}
		else{
			T = BP.numList;
			B = THIS.numList;
			indexB=THIS.numList.last();
			indexT=BP.numList.last();
		}
		Position<Character> original = indexT;
		int counter=0;
		int carry=0;
		ArrayList<FedNumbers> sumList = new ArrayList<>();
		String newNumber="";
		while(indexB!=null){
			if(indexB.getElement()!='.'){
				indexT=original;
				newNumber="";
				while(indexT!=null){
					if(indexT.getElement()!='.'){
						int tempM=Character.getNumericValue(indexT.getElement())*Character.getNumericValue(indexB.getElement())+carry;
						if(tempM>9&&T.before(indexT)!=null){
							String tempN ="";
							tempN=tempM+tempN;
							carry=Character.getNumericValue(tempN.charAt(0));
							newNumber=tempN.charAt(1)+newNumber;
						}
						else if(T.before(indexT)!=null){
							carry=0;
							newNumber=tempM+newNumber;
						}
						else{
							String tempN="";
							tempN=tempM+tempN;
							carry=0;
							newNumber=tempN+newNumber;
						}
					}
					indexT=T.before(indexT);
				}
				int localC=0;
				while(localC<counter){
					newNumber=newNumber+'0';
					localC++;
				}
				counter++;
				sumList.add(new FedNumbers(newNumber));
			}
			indexB=B.before(indexB);
		}
		Iterator<FedNumbers> iterator = sumList.iterator();
		FedNumbers result = new FedNumbers("0");
		while(iterator.hasNext()){
			result=result.sum(iterator.next());
		}
		if(dotPositions>0&&result.toString().length()>dotPositions){
			String tempS = result.toString();
			tempS=tempS.substring(0,tempS.length()-dotPositions)+'.'+tempS.substring(tempS.length()-dotPositions);
			result=new FedNumbers(tempS);
		}
		if(finalSign=='-'){
			result=new FedNumbers(finalSign+result.toString());
		}
		return result;
	}
	
	@Override
	public String toString(){
		String number="";
		Iterator<Character> iterator = numList.iterator();
		while(iterator.hasNext())
			number=number+iterator.next();
		return number;
	}
	
	@Override
	public int compareTo(FedNumbers b) {
		FedNumbers THIS = new FedNumbers(this.toString());
		FedNumbers B = new FedNumbers(b.toString());
		while(THIS.numList.first().getElement().equals('0')&&THIS.numList.size()>1){
			THIS.numList.remove(THIS.numList.first());
		}
		while(B.numList.first().getElement().equals('0')&&B.numList.size()>1){
			B.numList.remove(B.numList.first());
		}
		if(isInteger(THIS)!=-1){
			THIS.numList.addFirst('0');
			if(isInteger(B)==-1){
				B.numList.addLast('.');
				int counter=0;
				int lim = isInteger(THIS);
				while(counter<lim){
					B.numList.addLast('0');
					counter++;
				}
			}
			if(isInteger(B)!=-1&&isInteger(B)<isInteger(THIS)){
				int lim = isInteger(THIS)-isInteger(B);
				int counter=0;
				while(counter<lim){
					B.numList.addLast('0');
					counter++;
				}
			}
			else if(isInteger(B)!=-1&&isInteger(B)>isInteger(THIS)){
				int lim = isInteger(B)-isInteger(THIS);
				int counter=0;
				while(counter<lim){
					THIS.numList.addLast('0');
					counter++;
				}
			}
		}
		if(isInteger(B)!=-1){
			B.numList.addFirst('0');
			if(isInteger(THIS)==-1){
				THIS.numList.addLast('.');
				int counter=0;
				int lim = isInteger(B);
				while(counter<lim){
					THIS.numList.addLast('0');
					counter++;
				}
			}
			if(isInteger(THIS)!=-1&&isInteger(THIS)<isInteger(B)){
				int lim = isInteger(B)-isInteger(THIS);
				int counter=0;
				while(counter<lim){
					THIS.numList.addLast('0');
					counter++;
				}
			}
			else if(isInteger(THIS)!=-1&&isInteger(THIS)>isInteger(B)){
				int lim = isInteger(THIS)-isInteger(B);
				int counter=0;
				while(counter<lim){
					B.numList.addLast('0');
					counter++;
				}
			}
		}
		if(THIS.numList.size() < B.numList.size()) {
			return -1;
		}
		else if (B.numList.size() < THIS.numList.size()) {
			return 1;
		}
		Position<Character> indexT = THIS.numList.first();
		Position<Character> indexB= B.numList.first();
		while(indexT!=null||indexB!=null) {
			if(indexT.getElement() < indexB.getElement()) {
				return -1;
			}
			if(indexB.getElement() < indexT.getElement()) {
				return 1;
			}
			indexB=B.numList.after(indexB);
			indexT=THIS.numList.after(indexT);
		}
		return 0;
	}
	/**
	 * Finds the factors if any of a positive integer number
	 * @return returns an ArrayList of type FedNumbers with the prime factors of the number given in the this parameter
	 */
	public ArrayList<FedNumbers> factors(){
		ArrayList<FedNumbers> pList = new ArrayList<>();
		FedNumbers in = new FedNumbers(this.toString());
		FedNumbers original = new FedNumbers(in.toString());
		FedNumbers counter = new FedNumbers("2");
		FedNumbers zero = new FedNumbers("0");
		FedNumbers one = new FedNumbers("1");
		FedNumbers sq = counter.product(counter);
		while(original.compareTo(sq)>=0||in.compareTo(counter)==0){
			if(in.divideR(counter).compareTo(zero)==0){
				pList.add(counter);
				in=new FedNumbers(in.divideQ(counter).toString());
			}
			else{
				counter=new FedNumbers(counter.sum(one).toString());
				sq=counter.product(counter);
			}
		}
		if(in.compareTo(one)!=0)
			pList.add(in);
		return pList;
	}
}