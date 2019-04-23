import java.io.File;
import java.util.Vector;

public class Stats
{
    private class Entry
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

        private String key;
        private int count;
    }

    private int lineCount = 0, wordCount = 0;
    private Vector<Entry> words;
    private Hash hash;

    public Stats(File keys)
    {
        
    }

    public void Read(File text)
    {

    }

    public void Print()
    {

    }
}

