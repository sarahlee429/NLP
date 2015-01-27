import java.util.Map;


public interface INgrams {
	public Trie getTrie();
	public void setTrie(Trie t);
	public Map<Integer,Integer> getTuringMap();
	public void generateTuringMap();
	public void printTuringMap();
	public double addOneSmoothedProbability(String s);
	public double unsmoothedProbability(String s);
	public String generateSentence();
	public void print(String filename);
}
