/* 
 * StatsRules.java
 * 
 * Author:          Eric Ehmann, eaehmann@gmail.com
 * Date modified:   March 19, 2018
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class StatsAssigner{
	String[] attributeChoices={"Dexterity", "Strength", "Toughness", "Perception", "Willpower", "Charisma"};
	StatsRolls rolls;
	int[] attributes;
	int ATTRIBUTENUMBER= rolls.ATTRIBUTENUMBER;

	public StatsAssigner(){
		rolls = new StatsRolls();
		attributes= new int[ATTRIBUTENUMBER];


	}

	public int[] assignStats(){
		for(String word: attributeChoices){
			System.out.println("Please type the number corresponding the stat you would like to assign to "+ word);
			listPossibleChoices();
		}
		pickScore();
		return attributes;
	}

	public void listPossibleChoices(){
		for (int i = 0; i< attributeChoices.length; i++){
			System.out.println((i+1) + "   " + rolls.trackedScores[i]);
		}
	}

	public void pickScore(){
		int selection;
		for (int i =0; i< ATTRIBUTENUMBER; i++){
			do{
				 selection = enforceMax(1, ATTRIBUTENUMBER);
			}while(!isScore(rolls.trackedScores[selection]));
			attributes[i]=rolls.trackedScores[selection];
			rolls.trackedScores[selection]=0;
		}
	}

	public boolean isScore(int number){
		return number!=0;
	}

	public int enforceMin(int min){
 		int chosenNumber=rolls.rules.pickWholeNumber();
 		if (chosenNumber<min){
 			System.out.println("Please choose a valid number");
 			listPossibleChoices();
 			enforceMin(min);
 		}
 		return chosenNumber;
 	}

 	public int enforceMax(int min, int max){
 		int selection=enforceMin(min);
 		if(selection>max)
 			enforceMax(min, max);
 		return selection;
 	}

}