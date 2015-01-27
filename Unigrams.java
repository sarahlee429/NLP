import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Unigrams implements INgrams{
	private static final int SENTENCE_MAX = 150;
	private Trie t;
	private int n;
	private Map <Integer,Integer> turingMap;
	/**
	 * Constructor for the Unigram
	 * @param a trie containing Unigram data
	 * @return a pointer to new Unigram object
	 */
	public Unigrams(Trie t){
		this.t = t;
		this.n = n;
		turingMap = new TreeMap<Integer, Integer>();
	}

	//Getters and Setters
	public Trie getTrie(){
		return t;
	}

	public void setTrie(Trie t){
		this.t = t;
	}

	public Map<Integer,Integer> getTuringMap(){
		return this.turingMap;
	}

	public void generateTuringMap(){
		t.getTuringCounts(turingMap);
	}

	/**
	 * Prints the good turing count map in (Key,Value) format
	 * Note the key is all of the frequencies and value is the
	 * number of Ngrams with a given frequency
	 */
	public void printTuringMap(){
		for(Integer i: turingMap.keySet()){
			System.out.println("Key: " + i +","+ "Value: " + turingMap.get(i));
		}
	}

	public double addOneSmoothedProbability(String s){
		int count = t.stringFreq(s);
		System.out.println("the count is " + count);
		System.out.println("t size is " + t.getTotalFreq());
		if (count > 0) {
			return (count + 1) / (t.getTotalFreq() + (t.getVocabularySize()));
		}
		return 0.0;
	}

	public double unsmoothedProbability(String s){
		int count = t.stringFreq(s);
		System.out.println("the coutn is " + count);
		System.out.println("t size is " + t.getTotalFreq());
		if (count > 0) {
			return count / t.getTotalFreq();
		}
		return 0.0;
	}


	/**
	 * Generates a sentence using Unigram
	 * @return a String representing a sentence
	 * using trie
	 */
	public String generateSentence(){
		StringBuilder sentence = new StringBuilder();
		String s1 = "";
		/*keep appending words until either run into
		a sentence end punctuation or sentence is of
		reasonable length*/
		while(Pattern.matches("\\p{Punct}",s1) && sentence.length() <= SENTENCE_MAX){
			String w = t.generateWord(s1);
			sentence.append(w + " ");
			s1 = w;
		}
		//capitalizes the first character of the sentence
		String ret = Character.toString(sentence.toString().charAt(0)).toUpperCase() + 
				sentence.toString().substring(1);
		return ret;
	}


	public void print(String filename){
		t.print(filename);
	}

}
