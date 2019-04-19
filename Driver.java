import java.util.Vector;

public class Driver
{
	public static void main(String[] args)
	{
		Vector<String> words = new Vector<String>();

		words.add("hello");
		words.add("whats");
		words.add("up");
		words.add("hoss");

		Hash test = new Hash(words);

		test.printLetterCounts();

	}


}