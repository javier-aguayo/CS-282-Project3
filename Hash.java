import java.util.Vector;
import java.util.Stack;

public class Hash
{
	//class variables
	private String[] table;
	private int size;



	//constructors
	public Hash(Vector<String> words)
	{
		this.size = words.size();
		table = new String[size];

		load(words);
		//Have tested stack, working correctly, sorted words by highest wordvalue (continue work on load())

	}

	//clear hash table and load from String vector
	public void load(Vector<String> words)
	{
		size = words.size();

		cichelli(stackWords(words));
	}

	//load hash table using cichelli methods from string stack
	private boolean cichelli(Stack<String> words)
	{
		int[] gvalues = new int[26]; //holds g values for each letter, -1 means gvalue not assigned
		for(int gvalue: gvalues) { gvalue = -1; }
		int maxValue = size/2;

		while(!words.empty())
		{
			String word = words.pop();
			System.out.println(word);

			//fill in pseudocode
		}
		return true;
	}

	//assign first and last letter frequency values to words, sort, and stack
	private Stack<String> stackWords(Vector<String> words)
	{
		Vector<Node> sortedWords = sortKeys(assignFrequencies(words));
		Stack<String> stack = new Stack<String>();
		for(int i = 0; i < sortedWords.size(); i++)
		{
			stack.push(sortedWords.get(i).getKey());
		}
		return stack;
	}

	//assign first and last letter frequency values to words
	private Vector<Node> assignFrequencies(Vector<String> words) //counts first and last letters and puts in lettercounts array
	{
		Vector<Node> keys = new Vector<Node>();
		int[] lettercounts = new int[26];

		//count first and last
		for(int i = 0; i < words.size(); i++)
		{
			String word = words.get(i);
			int charIndex = charToInt(word.charAt(0));
			lettercounts[charIndex]++;
			charIndex = charToInt(word.charAt(word.length()-1));
			lettercounts[charIndex]++;
		}

		//assign frequency values to words and load into vector
		for(int i = 0; i < words.size(); i++)
		{
			String key = words.get(i);
			int value = lettercounts[charToInt(key.charAt(0))] + lettercounts[charToInt(key.charAt(key.length()-1))];
			Node node = new Node(key, value);
			keys.add(node);
		}

		//Just for testing
		/*
		for(int i = 0; i < lettercounts.length; i++)
		{
			if(lettercounts[i] > 0)
			{
				System.out.println(intToChar(i) + ": " + lettercounts[i]);
			}
		}
		*/

		return keys;
	}

	//sort vector of nodes based on frequency values of first and last letters
	private Vector<Node> sortKeys(Vector<Node> keys)
	{
		if(keys.size() < 2)
		{
			return keys;
		}
		for(int i = 0; i < keys.size() - 1; i++)
		{
			for(int j = i + 1; j > 0; j--)
			{
				if(keys.get(j).getValue() < keys.get(j-1).getValue())
				{
					swap(keys, j, j -1);
				}
			}
		}
		return keys;
	}
	private void swap(Vector<Node> keys, int index1, int index2)
	{
		Node temp = keys.get(index1);
		keys.set(index1, keys.get(index2));
		keys.set(index2, temp);
	}


	//reset Hash object to default
	public void clear()
	{
	}


	public void contains(String word)
	{

	}


	//helper methods for dealing with chars
	private int charToInt(char letter) //turns char into int index between 0 and 25
	{
		int value = letter - 97;
		return value;
	}

	private char intToChar(int value) // turns int into char
	{
		char letter = (char)(value + 97);
		return letter;
	}
}