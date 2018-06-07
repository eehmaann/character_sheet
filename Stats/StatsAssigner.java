/* 
 * StatsRules.java
 * 
 * This class will use the object of a set of rolled scores and allow a user to assign 
 * the rolls to which attribute.
 * Author:          Eric Ehmann, eaehmann@gmail.com
 * Date modified:   June 2, 2018
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class StatsAssigner{
	String[] attributeChoices={"Dexterity", "Strength", "Toughness", "Perception", "Willpower", "Charisma"};
	StatsRolls rolls;  // Object set of totalled dice rolls
	int attributenumber;// The number of available attributes
	int[] numberChoices;  // the rolls for each attribute
	int[] assignedAttributes; // array to be filled with avaiable numbers for each attribute
	int numberCompleted=0; // Number of times that an attribute has been assigned
	int numberHold;  //temporary variable for holding a varibale which will be used for sorting

	// zerp argument constructor method will have produce an array with attributes assigned based on dice rolls
	public StatsAssigner(){
		rolls = new StatsRolls();		
		attributenumber = rolls.ATTRIBUTENUMBER;
		assignedAttributes = new int[attributenumber];
		numberChoices=new int[attributenumber];
		rolls.getAllScores(numberChoices);
		assignStats();
	}

	/* constructor method will have produce an array with attributes assigned based on dice rolls
	 * @param Object StatsRolls already rolled set of dice scores
	 */
	public StatsAssigner(StatsRolls rolls){
		assignStats();
	}

	//  Method to assign the created rolls into the respective attibute.  
	public int[] assignStats(){
		for(String word: attributeChoices){
			System.out.println("Please type the number corresponding the stat you would like to assign to "+ word);
			listPossibleChoices();
			pickScore();
		}
	
		return assignedAttributes;
	}

// list all numbers available to be used
	public void listPossibleChoices(){
		int findOne=numberCompleted-1;
		for (int i = numberCompleted; i< attributeChoices.length; i++){
			System.out.println((i-findOne) + "   " + numberChoices[i]);
		}
	}

// Picks a number to assign to an attribute
	public void pickScore(){
		int selection;
		//do{
			selection = enforceMax(1, attributenumber-numberCompleted)+numberCompleted-1;
		//}while(!isScore(numberChoices[selection]));	
		assignedAttributes[numberCompleted]=numberChoices[selection];
		decreaseScoresShown(selection);
	}

// Places all the values that equal zero at the beginning of list and others at the back
	public void decreaseScoresShown(int number){
		numberHold=numberChoices[numberCompleted];
		numberChoices[number]=numberHold;
		numberChoices[numberCompleted]=0;
		numberCompleted++;
	}

/** method checks whether the number is bigger than the minimum
  * @param number entered
  */
	public int enforceMin(int min){
 		int chosenNumber=rolls.rules.pickWholeNumber();
 		if (chosenNumber<min){
 			System.out.println("Please choose a valid number");
 			listPossibleChoices();
 			enforceMin(min);
 		}
 		return chosenNumber;
 	}

/** recursive checks whether there is a number within range
  * @param number entered
  */
 	public int enforceMax(int min, int max){
 		int selection=enforceMin(min);
 		if(selection>max)
 			enforceMax(min, max);
 		return selection;
 	}

}