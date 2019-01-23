package dateBalancePrediction;

import java.io.File;

public class Files 
{
	public static void createTheFile()
	{
		File file = new File("dateDB.txt");
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
				System.out.println("New DB file created!!");
			}catch(Exception e)
			{
				System.out.println("Creation of file failed");
			}			
		}
	}	
}
