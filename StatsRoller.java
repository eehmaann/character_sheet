/* StatsRoller.java
 */

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;


class StatsRoller {

	
	public static final int ATTRIBUTECHOICES =6;
	String[][] Stats= new String [ATTRIBUTECHOICES][2];
	int[] attributes =new int[ATTRIBUTECHOICES];
	int[] trackedScores = new int[ATTRIBUTECHOICES];
	Random randomGenerator = new Random();
	String[] statTypes={"Dexterity", "Strength", "Toughness", "Perception", "Willpower", "Charisma"};
	

 	public static void main(String[] args) {
 		
 		StatsRoller sr =new StatsRoller();
		sr.setUpMechanic();
		int [] scores = new int [ATTRIBUTECHOICES];
		sr.getAllScores();
	 	System.out.println("Your Scores");
	 	for (int number : trackedScores)
	 		System.out.println(number);
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