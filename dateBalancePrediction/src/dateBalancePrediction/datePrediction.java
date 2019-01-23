package dateBalancePrediction;

import java.io.File;
import java.util.Scanner;

public class datePrediction
{
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		//Files.createTheFile();
		//Opening message
		System.out.println("Date Balance Predictor Program\n");
		
		//Get user Selection
		String choice = getMainMenuChoice();
		
		//main program loop Adding date, Calculating balance date, or exiting
		while (true)
		{			
			if (choice.contentEquals("A"))
			{
				System.out.println("A was selected");
				addADate.startMenu();
				System.out.println("Returning to Main Menu\n");
				choice = getMainMenuChoice();
				
			}else if (choice.contentEquals("C"))
			{
				System.out.println("C was selected");
				System.out.println("Returning to Main Menu\n");
				choice = getMainMenuChoice();
				
			}else if (choice.contentEquals("E"))
			{
				System.out.println("E was selected");
				System.out.println("Exiting Program");
				System.exit(0);
			}
		}		
	}
	
	//Get Input for the main menu - prompts for entry and validate to A C or E
	private static String getMainMenuChoice()
	{
		System.out.println("------Main Menu------\nA"
				+ "Please choose:\n\t'A' to add a date to the database"
				+ "\n\t'C' to calculate the balance"
				+ "\n\t'E' to end Prgram");		
		
		String choice = scanner.nextLine();
		
		//validate a correction response
		choice = validateMainMenuChoice(choice);
		
		//return cleared entry
		return choice;
	}
	
	///Validation for the main menu - forces selection of A C or E
	private static String validateMainMenuChoice(String choice)
	{		
		boolean isValid = false;
		
		//loops until user enters A C or E
		while (isValid == false)
		{		
			if (choice.equals("A") || choice.contentEquals("C") || choice.contentEquals("E"))
			{
				isValid = true;
			}else
			{
				System.out.println("\n!!!!!!!!!!!!!!\nYOUR CHOICE WAS NOT VALID."
						+ "\n\tPlease enter 'A', 'C', or 'E'");			
				
				choice = scanner.nextLine();
			}
		}			
		return choice;
	}
}