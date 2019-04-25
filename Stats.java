import java.util.Scanner;
import java.util.Vector;
import java.io.File;

import java.io.FileNotFoundException;

public class Stats
{
	private class Entry implements Comparable<Entry>
	{
		public Entry(String k)
		{
			key = k;
			count = 0;
		}

		public String Key() { return key; }
		public int Count() { return count; }
		public void Plus() { count++; }
		public void Reset() { count = 0; }
		public int compareTo(Entry other)
		{
			return key.compareTo(other.Key());
		}
		public int compareTo(String other)
		{
			return key.compareTo(other);
		}

		private String key;
		private int count;
	}

	private int lineCount, wordCount;
	private Vector<Entry> keyCounts;
	private Hash hash;

	public Stats(File keys) throws FileNotFoundException
	{
		Scanner sc = new Scanner(keys);
		Vector<String> keyList = new Vector<String>();
		keyCounts = new Vector<Entry>();

		while(sc.hasNext())
		{
			keyList.add(sc.next().toLowerCase());
			keyCounts.add(new Entry(keyList.lastElement()));
		}
		sc.close();

		keyCounts.sort(null);
		hash = new Hash(keyList);
		lineCount = 0;
		wordCount = 0;
	}

	public void Read(File text) throws FileNotFoundException
	{
		Scanner sc = new Scanner(text);

		while (sc.hasNextLine())
		{
			Scanner line = new Scanner(sc.nextLine());
			while (line.hasNext())
			{
				countKeyWord(line.next().toLowerCase());
				wordCount++;
			}
			lineCount++;
			line.close();
		}
		sc.close();
	}

	public void Print()
	{
		System.out.println("**********************");
		System.out.println("***** STATISTICS *****");
		System.out.println("**********************");
		System.out.println("Total Lines Read: " + lineCount);
		System.out.println("Total Words Read: " + wordCount);
		System.out.println("Break Down by Key Word");
		for (Entry e : keyCounts)
		{
			System.out.println(e.Key() + ": " + e.Count());
		}
		System.out.println("**********************");
	}

	private void countKeyWord(String word)
	{
		word = truncate(word);
		if (hash.contains(word))
		{
			int i = keyCounts.size() / 2, j = 4;
			int cmp = keyCounts.get(i).compareTo(word);
			while(cmp != 0)
			{
				if (cmp < 0)
				{
					i -= keyCounts.size() / j;
				}
				else if (cmp > 0)
				{
					i += keyCounts.size() / j;
				}
				cmp = keyCounts.get(i).compareTo(word);
				j *= 2;
			}
			keyCounts.get(i).Plus();
		}
	}

	private String truncate(String word)
	{
		if(word.charAt(word.length()-1) < 97 || word.charAt(word.length()-1) > 122)
		{
			return word.substring(0, word.length()-1);
		} else {
			return word;
		}
	}
}

