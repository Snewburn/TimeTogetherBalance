
package datebalanceprediction;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.*;
import static java.time.temporal.ChronoUnit.SECONDS;
import java.util.Scanner;

public class CalculatePrediction 
{
    //main method for clas
    public static void run()
    {
        double hoursSinceMeetMoment = timeSinceMeet();                
        double together = hoursFromDB();
        double apart = hoursSinceMeetMoment - together;
         
        DecimalFormat df = new DecimalFormat("##.##");
        System.out.println("\nSince OCT 5, 16:20:00");
        System.out.println("\tTime Apart\tTime Together");
        System.out.println("\t" + df.format(apart) + "\t\t" + df.format(together)); 
        
        predictDateTime(apart);
    }
    
    //method returns the number of hours needed to balance as an un rounded double
    private static double timeSinceMeet()
    {
        //create time instance of first meet and right now
        LocalDateTime now = LocalDateTime.now();    //moment at 'C' Enter
        LocalDateTime meetMoment = LocalDateTime.of
        (2018, Month.OCTOBER, 5, 16, 20, 0);    //moment we first met (oct 5th, 2018, 4:20 pm
        
        ///calculate the number of hours that are needed to be made up
        long span = meetMoment.until(now, SECONDS);
        
        //convert to a double of hours
        double secondsBal = (double)span;
        double hoursBal = secondsBal / 60 / 60;
        
        return hoursBal;
    }
    
    //method takes time since meet and subtracts the hours in database
    private static double hoursFromDB()
    {
        double hoursFromDB = 0;     
        
        //Read the dateDB.txt file with a Scanner Obj
        try
        {
            Scanner scanner = new Scanner(new File("dateDB.txt"));
            //while there is a next double in file, add to the sum
            while(scanner.hasNextDouble())
            {
                hoursFromDB = hoursFromDB + scanner.nextDouble();
            }
            
        }catch(FileNotFoundException e)
        {
            System.out.println("---error in reading the file");
        }
        
        return hoursFromDB;
    }
    
    //method takes the hours needed to balance and predicts day that will occur
    private static void predictDateTime(double hrsBal)
    {
        LocalDateTime predictedDateTime = LocalDateTime.now();
        DayOfWeek day = predictedDateTime.getDayOfWeek();
        
        //advance five days from C enter for honeymoon
        predictedDateTime.plusDays(5);
        
        //advance the prediction one day for every 6 hours or 23 hours as per day of the week
        while(hrsBal > 6)
        {
            //if it is a weekday
            if( day.equals(DayOfWeek.MONDAY) || day.equals(DayOfWeek.TUESDAY) || 
                day.equals(DayOfWeek.WEDNESDAY) || day.equals(DayOfWeek.FRIDAY) || 
                day.equals(DayOfWeek.THURSDAY) )
            {
                //advance day of the week
                day = day.plus(1);
                //add one day to now
                predictedDateTime = predictedDateTime.plusDays(1);
                //subtract 6 from hrsBal
                hrsBal = hrsBal - 6;
                
            }else if ( (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) 
                        && hrsBal > 23)
            {
                //advance day of the week                
                day = day.plus(1);
                //add one day to now
                predictedDateTime = predictedDateTime.plusDays(1);
                //subtract 23 from hrsBal
                hrsBal = hrsBal - 23;
                
            }else if ( (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY))
                       && hrsBal < 23){break;}            
        }
        
        //advance now by the number of hours left                
        long hrsExtra = (long)(hrsBal);     //get the hours leftover
        double noHrs = hrsBal - (double)(hrsExtra);    //isolate the faction of the hours
        double minConvert = noHrs * 60;
        long minExtra = Math.round(minConvert);
        
        //calculate final predicted Date and Time
        predictedDateTime = predictedDateTime.plusHours(hrsExtra);
        predictedDateTime = predictedDateTime.plusMinutes(minExtra); 
        
        LocalDateTime futureStart = LocalDateTime.now();
             
        System.out.println("\nPrediction Considerations:\n\t(5 day honeymoon)"
                + "\n\t(6 hours gain on weekdays)\n\t(23 hours gain on Weekends)"
                + "\n\nPredicted Date of Balance: " + predictedDateTime);

    }            
}
