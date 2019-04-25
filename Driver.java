import java.util.Vector;
import java.io.File;
import java.io.*;


public class Driver
{
	public static void main(String[] args)
	{
		try 
		{
			String keyFile = "keys.txt";
			File keys = new File(keyFile);
			Stats run;
			String textFile = "text.txt";
			File text = new File(textFile);
			//run.Read(text);
			System.out.println("File name :"+keys.getName()); 
			System.out.println("Path: "+keys.getPath()); 
			System.out.println("Absolute path:" +keys.getAbsolutePath()); 
			System.out.println("Parent:"+keys.getParent()); 
			System.out.println("Exists :"+keys.exists());
			if(keys.exists()) 
			{ 
				run = new Stats(keys); //Breaks after this point
				System.out.println("Is writeable: "+keys.canWrite()); 
				System.out.println("Is readable: "+keys.canRead()); 
				System.out.println("Is a directory: "+keys.isDirectory()); 
				System.out.println("File Size in bytes "+keys.length()); 
			}
			if(text.exists()) 
			{
				//run.Read(textFile);
			}
		} 
		catch (Exception exception1) 
		{
			System.out.println("Error opening input file");
			System.exit(0);
		}
	}
}