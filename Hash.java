public class Hash
{
	//class variables
	private String[] table;
	private int size;
	private int[] gvalues;

	//constructors
	public Hash()
	{
		gvalues = new int[26];
		size = 0;
	}
	public Hash(int size)
	{
		this.size = size;
		gvalues = new int[26];
	}
	public Hash(int size, String word)
	{
		this.size = size;
		gvalues = new int[26];
		insert(word);
	}

	//methods
	public void insert(String word)
	{

	}

	public void clear()
	{

	}

	public void contains(String word)
	{

	}

	//hash method
	private int getHash(String word)
	{
		int key = 0;
		return key;
	}

	private int charValue(char letter)
	{
		int value = letter - 97;
		return value;
	}

	private char valueChar(int value)
	{
		char letter = (char)(value + 97);
		return letter;
	}
}