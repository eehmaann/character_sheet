 /* 
 * StatsRules.java
 * 
 * Author:          Eric Ehmann, eaehmann@gmail.com
 * Date modified:   March 19, 2018
 */
import java.util.Scanner;

public class StatsRules{

	Scanner sc = new Scanner(System.in);
	int minDie=2;
 	int minRolls=1;
	int numberKept;
	int baseScore=0;
	int dieSize;
	int base;
	int dieRolls;

	// Constructor method for StatsRules

 	public StatsRules(){
 		
 		setBase();
 		base =baseScore;
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
 		int keptdice =numberKept;
 
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
 			
 		while (!sc.hasNextInt()) {
	   		System.out.println("Need a whole number 0 or higher");
	   		sc.nextLine();
		}
		return sc.nextInt();
 	}
	 
}