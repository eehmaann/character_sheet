/* StatsRoller.java
 */

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;


class StatsRoller {

	int dieSize;
	int dieRolls;
	int numberKept;
	int baseScore=0;
	public static final int ATTRIBUTECHOICES =6;
	String[][] Stats= new String [ATTRIBUTECHOICES][2];
	int[] attributes =new int[ATTRIBUTECHOICES];
	int[] trackScores = new int[ATTRIBUTECHOICES];
	Random randomGenerator = new Random();
	Scanner u = new Scanner(System.in);
	String[] statTypes={"Dexterity", "Strength", "Toughness", "Perception", "Willpower", "Charisma"};
	

 	public static void main(String[] args) {
 		
 	StatsRoller sr =new StatsRoller();
	sr.setUpMechanic();
	int [] scores = new int [ATTRIBUTECHOICES];
	scores=sr.getAllScores();
	 	System.out.println("Your Scores");
	 	for (int number : scores)
	 		System.out.println(number);
 	}


 	/* This method determines the basics mechanics for rolling stats.
		The user is able choose what the base number is, what sided die is rolled
		and the number of times the die will be rolled per stat and the number of rolls kept
	*/

 	public void setUpMechanic(){
 		int minDie=2;
 		int minRolls=1;
 		setBase();
 		System.out.println("What sided die will be used?");
 		dieSize=enforcePositive(minDie);
 		System.out.println("How many times to roll per stat?");
 		dieRolls=enforcePositive(minRolls);
 		
 		/*  This demonstrates using recursion in an interative process.  
 		    could delete do while loop by replacing minRolls with dieRolls */
 		do{
 			System.out.println("How many dice kept?");
	 		numberKept = enforcePositive(minRolls);
 		}while (numberKept>dieRolls);
 
 	}

 	// Asks user to set a given number or 0 to be a garaunteed number for each stat

 	public void setBase(){
 			System.out.println("What is the stating number for each stat?");
 			baseScore=enforcePositive(baseScore);
 	}

 	/* This method will ensure that the user will choose a possitive number
		@int min This is the minimum number needed for the number to be positive
 		@int a valid user inputed number
 	*/

 	public int enforcePositive(int min){
 		int chosenNumber=pickWholeNumber();
 		if (chosenNumber<min){
 			System.out.println("Please insert an integer " + min +" or higher");
 			enforcePositive(min);
 		}
 		return chosenNumber;
 	}

 	/* This method is used to allow the user to choose a whole number.  It continue to ask for a 
 		whole number until the user inputs one.  And will not throw an error message if user inserts string
 		@int number chosen by user
 	*/	
 	public int pickWholeNumber(){
 			
 		while (!u.hasNextInt()) {
	   		System.out.println("Need a whole number 0 or higher");
	   		u.nextLine();
		}
		return u.nextInt();
 	}
	 
	// creates an array of of integers to be used for attributes 

 	public void getAllScores(){
 		for (int i =0; i<ATTRIBUTECHOICES; i++)
 			trackScores[i]=findScore();		
 	}
 	
	
 	/* This method will determine the value of a score.
 	* @returns int ability score
 	*/

 	public int findScore(){
 		int[] usedRolls = new int[numberKept];
 		usedRolls=chooseNumbers(rollDice());
 		return addRolls(usedRolls);
 	} 

 	/* Takes array of numbers sorts in decending order, returns sub array of largest numbers
 	* @ parameter int[] rolled an array of all dice value rolled
 	* @ returns int[] sub array of largest numbers
 	*/

 	public int[] chooseNumbers (int[] rolled){
 		Arrays.sort(rolled);
   		int [] reverseRolled= new int[rolled.length];
   		for(int i =0; i <rolled.length; i++)
   			reverseRolled[i]= rolled[rolled.length-1-i];
 		int[] keptRolls=Arrays.copyOfRange(reverseRolled, 0, numberKept);
 		return keptRolls;
 	}
	
	 /* Repeats  the rollDie method the number of times the user said 
	 	@return int[] collection of die rolls
	 */
 	public int[] rollDice(){
 		int[] trackedRolls= new int [dieRolls];
 		for(int i= 0; i<dieRolls; i++)
 			trackedRolls[i]= rollDie();
 		return trackedRolls;
 	}


 	/* simuulattes rolling a die.  The number of sides is based on user input
 	It will take the number and return an integer between 1 and that nubmber
 	** @Param sides the number of sides on the die
 	** @returns int random number between one and sides
 	*/
	 	
	public int rollDie(){
	 	return randomGenerator.nextInt(dieSize)+1;
	 }
 		
	/* Takes array of chosen numbers, and totals the array.  
 	* @parameter trackedNumbers is array of kept rolls
 	* @ returns int total of numbers in array
 	*/ 
 	public int addRolls(int[] usedRolls) {
 		int sum=baseScore;
 		for (int number : usedRolls)
 			sum=sum+number;
 		return sum;
 	}

 }