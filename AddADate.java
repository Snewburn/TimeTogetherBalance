package datebalanceprediction;

import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class AddADate 
{
    static Scanner scanner = new Scanner(System.in);

    public static void run()
    {
        //use Date objects from the start and end times of the date
        Date startDate;
        Date endDate;		

        //present menu message	
        System.out.println("\n------ADD A DATE TO DATABASE------");

        //get input with valid date and time for two dates
        System.out.println("\n\tEnter start date and time (24 hour clock)");
        startDate = getDate();		
        System.out.println("---date and time accepted");

        System.out.println("\n\tEnter end date and time (24 hour clock)");		
        endDate = getDate();
        System.out.println("---date and time accepted");

        //calculate the hours together -- passed to commitTime()
        double dateTimeValue = timeDifference(startDate, endDate);

        //print data for review and permission to save
        System.out.print("\n---The Date You Entered:\n\tStart: "  
                                         + startDate + "\n\tEnd: " + endDate);
        System.out.println("\n\tTime together: " + dateTimeValue);                        
        
        String choice = getDatabaseChoice();
        
        //If Y is entered, write the value to the date database
        if (choice.contentEquals("Y")){commitTime(dateTimeValue);}
    }

    //Get Input for a date - takes user input and loops until format mm-dd-yyyy
    private static Date getDate()
    {	
        //Date object to be returned
        Date date = null;

        //loop until valid input is given
        while(date == null)
        {
            System.out.print("\tEnter as (mm-dd-yyyy hh:mm:ss): ");

            scanner = new Scanner(System.in);			
            String input = scanner.nextLine();			
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");				

            try
            {
                    date = dateFormat.parse(input);
            }catch(ParseException e)
            {
                System.out.println("that was not a valid entry"
                                    + "\n\tPlease enter a date in the format mm-dd-yyyy hh:mm:ss");
            }

        }
        return date;
    }

    //determine number of hours between two dates
    private static double timeDifference(Date dateStart, Date dateEnd)
    {		
        double time = (dateEnd.getTime() - dateStart.getTime());
        time = time / 1000;	//convert to seconds
        time = time / 60;	//convert to minutes
        time = time / 60;       //convert to hours        

        time = Math.round((time * 100) * 10) / 1000.0;
        
        return time;
    }

    //Get Input for the main menu - prompts for entry and validate to A C or E
    private static String getDatabaseChoice()
    {
        System.out.print("Would you like to add that date to the database (Y/N): ");	

        String choice = scanner.nextLine();

        //validate a correction response
        choice = validateDatabaseChoice(choice);

        //return cleared entry
        return choice;
    }

    ///Validation for the main menu - forces selection of A C or E
    private static String validateDatabaseChoice(String choice)
    {		
            boolean isValid = false;

            //loops until user enters Y or N
            while (isValid == false)
            {		
                if (choice.equals("Y") || choice.contentEquals("N"))
                {
                    isValid = true;
                }else
                {
                    System.out.println("\n!!!!!!!!!!!!!!\nYOUR CHOICE WAS NOT VALID."
                                    + "\n\tPlease enter 'Y' or 'N'");			

                    choice = scanner.nextLine();
                }
            }			
            return choice;
	}
    
    private static void commitTime(double time)
    {
       try
        {
            //use FileWriter to write value to the dateDB.txt file
            FileWriter out = new FileWriter("dateDB.txt", true);
            String dateValueText = Double.toString(time);  //convert the time to a string

            out.write(dateValueText + " ");
            out.close();
            System.out.println("--- " + dateValueText + " written to database");

        }catch(Exception e)
        {
                System.out.println("---error writing to Database");
        } 
    }
}
