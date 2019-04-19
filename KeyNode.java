public class KeyNode
{
	private String key;
	private int value;

	public KeyNode(String Key)
	{
		key = Key;
	}
	public KeyNode(String Key, int Value)
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