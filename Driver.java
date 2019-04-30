import java.util.Vector;
import java.io.File;
import java.io.*;
import java.util.Scanner;

public class Driver
{
	private Stats run;
	private boolean hasKey = false; 
	public static void main(String[] args)
	{
		
		Driver driver = new Driver();
		driver.excecute();
		
	}
	public void excecute()
	{
		
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		//System.out.println("Enter name of key file? ");
		//System.out.print('>');
		String keyFile;
		File keys ;
		while(true)
		{
			System.out.println("Enter name of key file?(.txt) ");
			System.out.print('>');
			keyFile = in.nextLine();
			keys = new File(keyFile);
			if(keys.exists()) 
			{ 
				try
				{
					run = new Stats(keys); 
					hasKey = true;
					break;
				}
				catch (Exception exception1) 
				{
					System.out.println("Error opening input file");
					System.exit(0);
				}
			}
			else System.out.println("File does not exists\n");
		}
		while (!exit)
		{
			switch(commandline(in))
			{
				case "quit":		exit = true;		break;
				case "ReadFile":	read(in);			break;
				default:	    	help();		    	break;
			}
		}

		in.close();
	}
	public String commandline(Scanner in)
	{
		System.out.println("\nPlease enter a command: ReadFile " +
			"or  quit.");
		System.out.print('>');
		String line = in.nextLine();
		return line;
	}
	public void read(Scanner in)
	{
		if(!hasKey)
		{
			System.out.println("Error. Enter keyFile first");
		}
		System.out.println("Name of file?(.txt) ");
		System.out.print('>');
		String textFile = in.nextLine();
		File text = new File(textFile);
		if(text.exists()) 
		{ 
			try
			{
				run.Read(text);
			}
			catch (Exception exception1) 
			{
				System.out.println("Error opening input file");
				System.exit(0);
			}
		}
		else System.out.println("File does not exists");
		run.Print();
	}
	public void help()
	{
		System.out.println("\nYour options are:" +
			"\nReadFile (ending in .txt) " +
			"\nquit (ends the program)\n");
	}	
}