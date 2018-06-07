/* 
 * StatsRolls.java
 * 
 * Author:          Eric Ehmann, eaehmann@gmail.com
 * Date modified:   March 19, 2018
 */

import java.util.Random;
import java.util.Arrays;

public class StatsRolls{

	public static final int ATTRIBUTENUMBER =6;
	int[] trackedScores;
	Random randomGenerator = new Random();
	String[] attributesChoices={"Dexterity", "Strength", "Toughness", "Perception", "Willpower", "Charisma"};
	StatsRules rules;

	public StatsRolls(){
		rules =new StatsRules();
		trackedScores = new int[ATTRIBUTENUMBER];
		//getAllScores();
	}

	

	// creates an array of of integers to be used for attributes 


// This right here needs to be fixed it is not keeping the scores.
 	public void getAllScores(int [] trackedValues){
 		for (int i=0; i< trackedValues.length; i++){
 			trackedValues[i]=findScore();
 			System.out.println(trackedValues[i]);		
 		}
 		System.out.println("This is working");
 	}
 	
	
 	/* This method will determine the value of a score.
 	* @returns int ability score
 	*/

 	public int findScore(){
 		int[] usedRolls = new int[rules.numberKept];
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
 		int[] keptRolls=Arrays.copyOfRange(reverseRolled, 0, rules.numberKept);
 		return keptRolls;
 	}
	
	 /* Simulates rolling a variable sided die a variable number of times
	 	@return int[] collection of die rolls
	 */
 	public int[] rollDice(){
 		int[] trackedRolls= new int [rules.dieRolls];
 		for(int i= 0; i<rules.dieRolls; i++)
 			trackedRolls[i]= randomGenerator.nextInt(rules.dieSize)+1;
 		return trackedRolls;
 	}

 		
	/* Takes array of chosen numbers, and totals the array.  
 	* @parameter trackedNumbers is array of kept rolls
 	* @ returns int total of numbers in array
 	*/ 
 	public int addRolls(int[] usedRolls) {
 		int sum=rules.base;
 		for (int number : usedRolls)
 			sum=sum+number;
 		return sum;
 	}

}