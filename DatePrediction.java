package datebalanceprediction;


import java.util.Scanner;
import java.io.IOException;

public class DatePrediction
{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        //Opening message
        System.out.println("Date Balance Predictor Program\n");

        //Get user Selection
        String choice = getMainMenuChoice();

        //main program loop Adding date, Calculating balance date, or exiting
        while (true)
        {			
            switch(choice)
            {
                case "A":                    
                    AddADate.run();
                    System.out.println("\n---returning to Main Menu\n");
                    choice = getMainMenuChoice();
                    break;
                case "C":            
                    CalculatePrediction.run();
                    try{System.in.read();}catch (Exception e){}
                    System.out.println("\n---returning to Main Menu\n");
                    choice = getMainMenuChoice();
                    break;                   
                case "E":                
                    System.out.println("\n---Exiting Program");
                    System.exit(0);
                    break;
            }
            
        }		
    }

    //Get Input for the main menu - prompts for entry and validate to A C or E
    private static String getMainMenuChoice()
    {
        System.out.println("------Main Menu------\n"
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