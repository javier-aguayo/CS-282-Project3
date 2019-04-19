public class Node
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