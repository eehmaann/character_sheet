/* StatsRoller.java
 */

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;


class StatsRoller {

	int dieSize;
	int dieRolls;
	int numberKept;
	int baseNumber;
	public static final int ATTRIBUTECHOICES =6;
	String[][] Stats= new String [6][2];
	int[] attributes =new int[ATTRIBUTECHOICES];
	Random randomGenerator = new Random();
	Scanner u = new Scanner(System.in);
	
	

 	public static void main(String[] args) {
 		
 	StatsRoller sr =new StatsRoller();
 	//sr.bases();
	
	//sr.dieSize=sr.u.nextInt();
	//System.out.println("How many times to roll per stat?");
	//sr.dieRolls=sr.u.nextInt();
	sr.setUpMechanic();
	int [] scores = new int [ATTRIBUTECHOICES];
	scores=sr.getAllScores(sr.dieRolls, sr.dieSize);
	 	System.out.println("Your Scores");
	 	for (int number : scores)
	 		System.out.println(number);
 	}


 	public void setUpMechanic(){
 		int minDie=2;
 		int minRolls=1;
 		System.out.println("What sided die will be used?");
 		dieSize=enforcePositive(minDie);
 		System.out.println("How many times to roll per stat?");
 		dieRolls=enforcePositive(minRolls);
 		
 		do{
 			System.out.println("How many dice kpet?");
	 		numberKept = enforcePositive(minRolls);
 		}while (numberKept>dieRolls);
 
 	}

 	public int enforcePositive(int min){
 		int chosenNumber=pickWholeNumber();
 		if (chosenNumber<min){
 			System.out.println("Please insert an integer " + min +" or higher");
 			enforcePositive(min);
 		}
 		return chosenNumber;
 	}

 	/* simuulattes rolling a die.  The number of sides is based on user input
 		It will take the number and return an integer between 1 and that nubmber
 		** @Param sides the number of sides on the die
 		** @returns an int
 	*/

 		public void bases(){
 			System.out.println("What is the stating number for each stat?");
 			int baseNumber=enforcePositive(0);
 			for(int i=0; i<ATTRIBUTECHOICES; i++){
 				attributes[i]=baseNumber;
 			}
 		}

 		public int pickWholeNumber(){
 			
 			while (!u.hasNextInt()) {
	   			System.out.println("Need a whole number 0 or higher");
	   			u.nextLine();
			}
			return u.nextInt();
 		}
	 	public int rollDie(int sides){
	 		return randomGenerator.nextInt(sides)+1;

	 	}

 		public int[] rollDice(int dieRolls, int dieSize){
 			int[] trackedNumbers= new int [dieRolls];
 			for(int i= 0; i<dieRolls; i++)
 				trackedNumbers[i]= rollDie(dieSize);
 			return trackedNumbers;
 		}

 		public int[] chooseNumbers (int[] rolled){
 			Arrays.sort(rolled);
   			int [] reverseRolled= new int[rolled.length];
   			for(int i =0; i <rolled.length; i++)
   				reverseRolled[i]= rolled[rolled.length-1-i];
 			int[] keptRolls=Arrays.copyOfRange(reverseRolled, 0, numberKept);
 			return keptRolls;

 		}

 		public int addRolls(int[] trackedNumbers) {
 			int sum=0;
 			for (int number : trackedNumbers)
 				sum=sum+number;
 			return sum;
 		}
 		public int findScore(int dieRolls, int dieSize){
 			int[] usedRolls = new int[numberKept];
 			usedRolls=chooseNumbers(rollDice(dieRolls, dieSize));
 			return addRolls(usedRolls);

 		}

 		public int[] getAllScores(int dieRolls,  int dieSize){
 			int[] trackScores = new int[ATTRIBUTECHOICES];
 			for (int i =0; i<ATTRIBUTECHOICES; i++)
 				trackScores[i]=findScore(dieRolls, dieSize);
 			return trackScores;		

 		}



 }