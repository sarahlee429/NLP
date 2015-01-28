import java.util.Map;
import java.util.TreeMap;

/**
 * Bigrams --- Implements bigrams language model
 * @author Sarah Lee
 */
public class Bigrams implements INgrams{
	private static final int SENTENCE_MAX = 100;
	static private final String WORD_MARKER = "#";
	private Trie t;
	private Map <Integer,Integer> turingMap;
	
	/**
	 * Constructor for the Bigrams
	 * @param a Trie containing Bigrams data
	 * @return a pointer to new Bigrams object
	 */
	public Bigrams(Trie t){
		this.t = t;
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

	public void printTuringMap(){
		for(Integer i: turingMap.keySet()){
			System.out.println("Key: " + i +","+ "Value: " + turingMap.get(i));
		}
	}
	
	//Note the probabilities of bigrams is conditional
	//on the probability of preceding string
	//p = count(w1,w2)/count(w1)
	public double addOneSmoothedProbability(String s){
		String [] split = s.split(WORD_MARKER);
		assert(split.length == 2);
		int count = t.stringFreq(s);
		if (count > 0) {
			return (count + 1) / (t.stringFreq(split[0]) + (t.getVocabularySize()));
		}
		return 0.0;
	}

	public double unsmoothedProbability(String s){
		int count = t.stringFreq(s);
		String [] split = s.split(WORD_MARKER);
		assert(split.length == 2);
		if (count > 0) {
			return count / t.stringFreq(split[0]);
		}
		return 0.0;
	}

	public String generateSentence(){
		StringBuilder sentence = new StringBuilder();
		String s1 = "";
		String s2 = "";
		
		/*keep appending words until either run into
		a sentence end punctuation or sentence is of
		reasonable length*/
		while(!s2.contains(".") && sentence.length() <= SENTENCE_MAX){
			s2 = t.generateWord(s1);
			sentence.append(s2 + " ");
			s1 = s2;
		}
		//capitalizes the first character of the sentence
		String ret = Character.toString(sentence.toString().charAt(0)).toUpperCase() + 
				sentence.toString().substring(1);
		//appends punctuation if none at end of sentence
		if(!ret.contains(".")){
			ret = ret + ".";
		}
		return ret;
	}

	public void print(String filename){
		t.print(filename);
	}

}
