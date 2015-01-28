import java.util.Map;

/**
 * INgrams --- Method signatures for NGrams interface
 * @author Sarah Lee
 */
public interface INgrams {
	//getters
	public Trie getTrie();
	public void setTrie(Trie t);
	public Map<Integer,Integer> getTuringMap();
	
	/**
	 * Populates the Good-Turing count map in for NGrams
	 * @return none
	 */
	public void generateTuringMap();
	
	/**
	 * Prints the Good-Turing count map in (Key,Value) format
	 * Note the key is all of the frequencies and value is the
	 * number of NGrams with the given frequency
	 * @return none
	 */
	public void printTuringMap();
	
	/**
	 * Returns add-one smoothed probability of s
	 * @param String s to search
	 * @return add-one smoothed probability as double
	 */
	public double addOneSmoothedProbability(String s);
	
	/**
	 * Returns unsmoothed probability of s
	 * @param String s to search
	 * @return add-one smoothed probability as double
	 */
	public double unsmoothedProbability(String s);
	
	/**
	 * Generates a sentence using NGram
	 * @return a String representing a sentence
	 */
	public String generateSentence();
	
	/**Prints NGram to specified file
	 * @param full file path
	 * @return none
	 */
	public void print(String filename);
}
