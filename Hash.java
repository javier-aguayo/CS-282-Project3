import java.util.Vector;
import java.util.Stack;

public class Hash
{

//CLASS VARIABLES ---------------------------------------------
	private String[] table;
	private int size;
	int[] gvalues = new int[26]; //holds g values for each letter, -1 means gvalue not assigned
	int maxValue;


//CONSTRUCTOR ------------------------------------------------
	public Hash(Vector<String> words)
	{
		size = words.size();
		table = new String[size];
		maxValue = size/2;

		for(int i = 0; i < gvalues.length; i++)
		{
			gvalues[i] = -1;
		}

		cichelli(stackWords(words));
	}

//PUBLIC METHODS ---------------------------------------------
	//load a new Vector<String> into hash table. Will overwrite old table (same as constructor but for existing object)
	public void load(Vector<String> words)
	{
		size = words.size();
		table = new String[size];
		maxValue = size/2;

		for(int i = 0; i < gvalues.length; i++)
		{
			gvalues[i] = -1;
		}

		cichelli(stackWords(words));
	}

	//Empty hash table and clear variables
	public void clear()
	{
		table = null;
		size = 0;
		maxValue = 0;
		for(int i = 0; i < gvalues.length; i++)
		{
			gvalues[i] = -1;
		}
	}

	//check if the table contains a certain word
	public boolean contains(String word)
	{
		if(word.compareTo(table[hashValue(word)]) == 0)
		{
			return true;
		}
		return false;
	}

	//prints the hash table for debugging
	public void print()
	{
		for(int i = 0; i < size; i++)
		{
			System.out.println("Index " + i + ": " + table[i]);
		}
	}


//PRIVATE METHODS --------------------------------------------
	//build hash table using cichelli algorithm
	private boolean cichelli(Stack<String> words)
	{
		while(!words.empty())
		{
			String word = words.pop();
			int first = charToInt(word.charAt(0));
			int last = charToInt(word.charAt(word.length()-1));

			if(gvalues[first] > -1 && gvalues[last] > -1) //first and last have g-values already
			{
				if(table[hashValue(word)] == null)
				{
					table[hashValue(word)] = word;
					if(cichelli(words))
					{
						return true;
					}
					else {
						table[hashValue(word)] = null;
					}
				}
				words.push(word);
				return false;
			}
			else if((gvalues[first] == -1 && gvalues[last] == -1)
					&& (first != last)) //BOTH first and last don't have g-values and are different
			{
				for(int n = 0; n <= maxValue; n++)
				{
					for(int m = 0; m <= maxValue; m++)
					{
						gvalues[first] = m;
						gvalues[last] = n;

						if(table[hashValue(word)] == null)
						{
							table[hashValue(word)] = word;
							if(cichelli(words))
							{
								return true;
							}
							else {
								table[hashValue(word)] = null;
							}
						}
					}
				}
				gvalues[first] = -1;
				gvalues[last] = -1;
				words.push(word);
				return false;
			}
			else { // only one letter assigned g-value OR first = last letter

				int letter = -1;
				if(gvalues[first] == -1){
					letter = first;
				} else {
					letter = last;
				}

				for(int m = 0; m <= maxValue; m++)
				{
					gvalues[letter] = m;

					if(table[hashValue(word)] == null)
					{
						table[hashValue(word)] = word;
						if(cichelli(words))
						{
							return true;
						}
						else {
							table[hashValue(word)] = null;
						}
					}
				}
				gvalues[letter] = -1;
				words.push(word);
				return false;
			}
		} // end of while(!stack.empty())
		return true; // empty stack means we have a solution
	}

	//Take in Vector<String> and return sorted Stack<String>
	private Stack<String> stackWords(Vector<String> words)
	{
		Vector<Node> sortedWords = sortVector(assignFrequencies(words));
		Stack<String> stack = new Stack<String>();
		for(int i = 0; i < sortedWords.size(); i++)
		{
			stack.push(sortedWords.get(i).getKey());
		}
		return stack;
	}

//HELPER METHODS -------------------------------------------------------
	private int hashValue(String word)
	{
		int length = word.length();
		int gFirst = gvalues[charToInt(word.charAt(0))];
		int gLast = gvalues[charToInt(word.charAt(word.length()-1))];
		int hash = (length + gFirst + gLast) % size;
/*		System.out.println();
		System.out.print(word + ": ");
		System.out.print("length= " + length);
		System.out.print(", gFirst= " + gFirst);
		System.out.print(", gLast= " + gLast);
		System.out.println(", hash= " + hash);
*/
		return hash;
	}

	//assign first and last letter frequency values to words, returns Vector<Node> (node behaves as Pair)
	private Vector<Node> assignFrequencies(Vector<String> words)
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

		return keys;
	}

	//sort vector of nodes based on frequency values of first and last letters
	private Vector<Node> sortVector(Vector<Node> keys)
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
	private void swap(Vector<Node> keys, int index1, int index2) //helper method for sortVector
	{
		Node temp = keys.get(index1);
		keys.set(index1, keys.get(index2));
		keys.set(index2, temp);
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

//HELPER NODE CLASS---------------------------------------------------------
	private class Node
	{
		private String key;
		private int value;

		public Node(String Key)
		{
			key = Key;
		}
		public Node(String Key, int Value)
		{
			key = Key;
			value = Value;
		}

		public String getKey()
		{
			return key;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int Value)
		{
			value = Value;
		}
	}

}