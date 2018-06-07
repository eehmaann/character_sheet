/* 
 * RacePicker.java
 * 
 * Author:          Eric Ehmann, eaehmann@gmail.com
 * Date modified:   March 19, 2018
 */
import java.util.Scanner;
import java.util.Random;

public class RacePicker{
	Scanner rp= new Scanner(System.in);
	Random randomGenerator = new Random();
	String[] racesChoices={"Dwarf", "Elf", "Human", "Obsidiman", "Ork", "Troll", "T'skrang", "Windling"};
	int numberOfRaces = racesChoices.length;
	boolean isRandom;
	StatsAssigner stats;
	int[] attributes; 
	String[] attributeChoices; 
	String race = "Not valid";

	// Constructor method that takes attributes and applies racial modifiers
	public RacePicker(){
		stats= new StatsAssigner();
		attributes= stats.assignedAttributes;
		attributeChoices= stats.attributeChoices;
		displayResults();

	}

	/** method that allows user to declare whether the race will be chosen by user or randomly
	  *
	  * @return boolean true if random is wanted
	  */
	public boolean wantRandom(){
		char ans ='?';
		do{
			System.out.println("Would you like to have your race randomly chosen? (Y or N)");
			String answer=rp.nextLine();
			if (answer.length()>0){
				ans =answer.charAt(0);
			}
		}while (!( ans==('Y')||ans==('N')));
		return(ans==('Y'));
	}

	/** takes numeric input and deteremines whether it is a valid option, if so will apply modifiers
      *
      * @param int race number
      * @return string name of race
      */
	public String chooseRace(int choice){
		switch( choice){
		case 1:
			attributes[1]=attributes[1]+2;
			attributes[2]=attributes[2]+3;
			attributes[5]=attributes[5]-1;
			return "Dwarf";
		case 2:
			attributes[0]=attributes[0]+2;
			attributes[2]=attributes[2]-2;
			attributes[3]++;
			attributes[4]++;
			attributes[5]++;
			return "Elf";
		case 3:
			return "Human";
		case 4:
			if(validObsidiman()){
				attributes[0]=attributes[0]-2;
				attributes[1]=attributes[1]+6;
				attributes[2]=attributes[2]+4;
				attributes[3]=attributes[3]-1;
				attributes[5]=attributes[5]-1;
				return "Obsidiman";
			}
			else return "Not valid";
		case 5: 
			attributes[0]=attributes[0]-1;
			attributes[1]=attributes[1]+3;
			attributes[2]=attributes[2]+1;
			attributes[4]=attributes[4]-1;
			attributes[5]=attributes[5]-1;
			return "Ork";
		case 6:
			if(validTroll()){
				attributes[1]=attributes[1]+4;
				attributes[2]=attributes[2]+2;
				attributes[3]=attributes[3]-1;
				attributes[4]=attributes[4]+1;
				return "Troll";

			}
			else return "Not valid";
		case 7:
				attributes[0]=attributes[0]+1;
				attributes[2]=attributes[2]+1;
				attributes[5]=attributes[5]+1;
				return "T'skrang";
		case 8:
			if(validWindling()){
				attributes[0]=attributes[0]-1;
				attributes[1]=attributes[1]-4;
				attributes[2]=attributes[2]-3;
				attributes[3]=attributes[3]+1;
				attributes[5]=attributes[5]+21;
				return "Windling";	
				}
			else return "Not valid";
		default:
			return "Not valid";
		}
	
	}

	/** Checls whether is able to be an Obsidiman 
	  *
	  * @returns boolean.  True if can be Obsidiman
	  */
	public boolean validObsidiman(){
		return (attributes[1]>14);
	} 

	/**. Checks whether is qualified to be a troll
	  * 
	  * @returns boolean True if can be a troll
	  */
	public boolean validTroll(){
		if(attributes[1]<11)
			return false;
		return attributes[2]>10;
	}

	/**. Checks whether is qualified to be a  windling
	  * 
	  * @returns boolean True if can be a windling
	  */
	public boolean validWindling(){
		return attributes[1]<12;
	}

	/** generates a random number to determine race
	  *
	  * @return int number selecting race
	  */
	public int randomNumber(){
		return randomGenerator.nextInt(numberOfRaces);
	}

	/** user chooses a number to determine race
	  *
	  * @return int number selecting race
	  */
	public int chooseRaceNumber(){
		printRaceChoices();
		System.out.println("Select the number of the race that you would like to be?");
		int raceNumber=rp.nextInt();
		if(raceNumber<1 || raceNumber>numberOfRaces){
			chooseRaceNumber();
		} 
		return raceNumber-1;
	}

	// prints a list of all races 
	public void printRaceChoices(){
		for(int i =0; i< numberOfRaces; i++){
			System.out.println( i+1 + " " + racesChoices[i]);
		}
	}

	/** determine race fo character while applying racial modifiers
	  *
	  * @returns String name of race
	  */

	public String assignRace(){
		if(wantRandom()){
			return getRace();
		}
		return selectRace();
	}
		/**int choice;
		if(wantRandom()){
			do{
				choice=randomNumber();
			}while (!isValidChoice(choice));
		}

		else {
			do{
				choice=chooseRaceNumber();
			}while(!isValidChoice(choice));
		}
		return chooseRace(choice);
		*/

 	public String getRace(){
 		do{
 			 race =chooseRace(randomNumber());
 		}while(race=="Not valid");
 		return race;		 
 	}

 	public String selectRace(){
 		do{
 			 race =chooseRace(chooseRaceNumber());
 		}while(race=="Not valid");
 		return race;		 
 	}

	//public boolean isValidChoice(int choice){
	//	return chooseRace(choice)!="Not valid";
	//}

 	// uses assign race to assign the race and then shows results
	public void displayResults(){
	System.out.println("Race " + assignRace());
	for(int i=0; i<attributes.length; i++){
		System.out.println(attributeChoices[i]+ " " + attributes[i]);
		}
	}
}