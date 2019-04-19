import java.util.*;

public class Hash
{
	//class variables
	private String[] table;
	private int size;
	private int[] gvalues = new int[26]; //holds g values for each letter
	private int[] lettercounts = new int[26]; //holds first and last occurrence counts
	private Stack<String> stack = new Stack<String>();
	private int MaxValue;

	//constructors
	public Hash(Vector<String> words)
	{
		this.size = words.size();

		stackWords(words);
		//Have tested stack, working correctly, sorted words by highest wordvalue
	}




	//methods
	private void stackWords(Vector<String> words)
	{
		Vector<KeyNode> sortedWords = sortKeys(KeyValues(words));

		for(int i = 0; i < sortedWords.size(); i++)
		{
			stack.push(sortedWords.get(i).getKey());
		}

	}

	private Vector<KeyNode> KeyValues(Vector<String> words) //counts first and last letters and puts in lettercounts array
	{
		Vector<KeyNode> keys = new Vector<KeyNode>();

		for(int i = 0; i < words.size(); i++)
		{
			String word = words.get(i);
			int charIndex = charToInt(word.charAt(0));
			lettercounts[charIndex]++;
			charIndex = charToInt(word.charAt(word.length()-1));
			lettercounts[charIndex]++;
		}
		for(int i = 0; i < words.size(); i++)
		{
			String key = words.get(i);
			int value = lettercounts[charToInt(key.charAt(0))] + lettercounts[charToInt(key.charAt(key.length()-1))];
			KeyNode node = new KeyNode(key, value);
			keys.add(node);
		}
		return keys;
	}

	private Vector<KeyNode> sortKeys(Vector<KeyNode> keys)
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

	public void swap(Vector<KeyNode> keys, int index1, int index2)
	{
		KeyNode temp = keys.get(index1);
		keys.set(index1, keys.get(index2));
		keys.set(index2, temp);
	}


	public void printLetterCounts() //method for testing lettercounts
	{
		for(int i = 0; i < lettercounts.length; i++)
		{
			if (lettercounts[i] > 0)
			{
				System.out.println(intToChar(i) + ": " + lettercounts[i]);
			}
		}
	}

	public void insert(String word)
	{

	}

	public void clear()
	{

	}

	public void contains(String word)
	{

	}

	//hash method??
	private int getHash(String word)
	{
		int key = 0;
		return key;
	}

	private int charToInt(char letter)
	{
		int value = letter - 97;
		return value;
	}

	private char intToChar(int value)
	{
		char letter = (char)(value + 97);
		return letter;
	}
}