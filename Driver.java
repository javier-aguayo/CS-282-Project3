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
		words.add("stupid");

		Hash test = new Hash(words);

		test.print();

		System.out.println("Is \"stupid\" in table?: " + test.contains("stupid"));
		System.out.println("Is \"hello\" in table?: " + test.contains("hello"));
		System.out.println("Is \"rough\" in table?: " + test.contains("rough"));
		System.out.println("Is \"up\" in table?: " + test.contains("up"));
		System.out.println("Is \"unbelievable\" in table?: " + test.contains("unbelievable"));
		System.out.println("Is \"hoss\" in table?: " + test.contains("hoss"));
		System.out.println("Is \"whats\" in table?: " + test.contains("whats"));
		System.out.println("Is \"pigeon\" in table?: " + test.contains("pigeon"));

		test.clear();
		test.print();

		words.add("poignant");
		test.load(words);
		test.print();
	}
}