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
	boolean isRandom;
	StatsAssigner stats;
	int[] attributes=stats.attributes;
	String[] attributeChoices=stats.attributeChoices;
	// Stats order ("Dexterity", "Strength", "Toughness", "Perception", "Willpower", "Charisma")
	public RacePicker(){
		stats= new StatsAssigner();

	}

	public boolean wantRandom(){
		char ans ='?';
		do{
			System.out.println("Would you like to have your race randomly chosen? (Y or N");
			String answer=rp.nextLine();
			if (answer.length()>0){
				ans =answer.charAt(0);
			}
		}while (!( ans==('Y')||ans==('N')));
		return(ans==('Y'));
	}

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

	public boolean validObsidiman(){
		return (attributes[1]>14);
	} 

	public boolean validTroll(){
		if(attributes[1]<11)
			return false;
		return attributes[2]>10;
	}

	public boolean validWindling(){
		return attributes[1]<12;
	}

	public int randomNumber(){
		return randomGenerator.nextInt(racesChoices.length);
	}

	public int chooseRaceNumber(){
		printRace();
		System.out.println("Select the number of the race that you would like to be?");
		int raceNumber=rp.nextInt();
		return raceNumber-1;
	}

	public void printRace(){
		for(int i =0; i< racesChoices.length; i++){
			System.out.println( i+1 + " " + racesChoices[i]);
		}
	}

	public String pickRace(){
		int choice;
		if(wantRandom()){
			do{
				choice=randomNumber();
			}while (isWrongChoice(choice));
			return chooseRace(choice);
		}

		do{
			choice=chooseRaceNumber();
		}while(isWrongChoice(choice));
		return chooseRace(choice);
	}

	public boolean isWrongChoice(int choice){
		return chooseRace(choice)!="Not valid";
	}

	public void displayResults(){
	System.out.println("Race " + pickRace());
	for(int i=0; i<attributes.length; i++){
		System.out.println(attributeChoices[i]+ " " + attributes[i]);
		}
	}
}