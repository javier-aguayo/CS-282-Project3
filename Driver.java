import java.util.Vector;
import java.io.File;
import java.io.*;
import java.util.Scanner;

public class Driver
{
	private Stats run;
	public static void main(String[] args)
	{
		
		Driver driver = new Driver();
		driver.excecute();
		
	}
	public void excecute()
	{
		
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		while (!exit)
		{
			switch(commandline(in))
			{
				case "quit":		exit = true;		break;
				case "KeyFile":		key(in);			break;
				case "ReadFile":	read(in);			break;
				case "print":		print();			break;
				default:	    	help();		    	break;
			}
		}

		in.close();
	}
	public String commandline(Scanner in)
	{
		System.out.println("\nPlease enter a command: KeyFile, ReadFile, " +
			"print, and quit.");
		System.out.print('>');
		String line = in.nextLine();
		return line;
	}
	public void key(Scanner in)
	{
		System.out.println("Name of file? ");
		System.out.print('>');
		String keyFile = in.nextLine();
		File keys = new File(keyFile);
		if(keys.exists()) 
		{ 
			try
			{
				run = new Stats(keys); 
			}
			catch (Exception exception1) 
			{
				System.out.println("Error opening input file");
				System.exit(0);
			}
		}
	}
	public void read(Scanner in)
	{
		System.out.println("Name of file? ");
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
	}
	public void print()
	{
		run.Print();
	}
public void help()
	{
		System.out.println("\nYour options are:" +
			"\nKeyFile (ending in .txt) " +
			"\nReadFile (ending in .txt) " +
			"\nprint " +
			"\nquit (ends the program)\n");
	}	
}