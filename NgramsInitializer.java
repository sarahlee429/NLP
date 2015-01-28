import java.util.ArrayList;
import java.util.List;

/**
 * NgramsInitializer --- A class that creates populates unigrams and bigrams
 * @author Sarah Lee
 */
public class NgramsInitializer {
	private static final String WORD_MARKER = "#";
	private Tokenizer t;

	public NgramsInitializer(){
		t = new Tokenizer();
		t.Tokenize();
	}
	
	/** Creates a unigram using tokens from tokenizer
	 * and populating a trie.
	 * @return a Unigrams object
	 */
	public Unigrams makeUnigrams(){
		List<String> ngrams = t.getTokens();
		Trie t = new Trie();
		t.insertAll(ngrams);
		Unigrams u = new Unigrams(t);
		return u;
	}
	
	/** Creates a bigram using tokens from tokenizer
	 * and populating a trie.
	 * @return a Bigrams object
	 */
	public Bigrams makeBigrams(){
		List<String> ngrams = new ArrayList<String>();
		List<String> tokens = t.getTokens();
		Trie t = new Trie();
		//Segment bigrams from tokens
		for(int i = 0; i < tokens.size() - 1; i++){
			ngrams.add(tokens.get(i) + WORD_MARKER + tokens.get(i + 1) + WORD_MARKER);
		}
		t.insertAll(ngrams);
		Bigrams b = new Bigrams(t);
		return b;
	}
	
	public static void main(String args[]){
		if(args.length != 2){
			System.out.println("Please input 2 file paths to print Unigrams and Bigrams.");
			System.exit(1);
		}
		String outputUni = args[0];
		String outputBi = args[1];
		NgramsInitializer i = new NgramsInitializer();
		Unigrams uni = i.makeUnigrams();
		Bigrams bi = i.makeBigrams();
		System.out.println("Printing unigrams to input file 1...");
		uni.print(outputUni);
		System.out.println("Printing bigrams to input file 2...");
		bi.print(outputBi);
		System.out.println();
		System.out.println("Printing sentence generated using Unigrams:");
		System.out.println(uni.generateSentence());
		System.out.println();
		System.out.println("Printing sentence generated using Bigrams:");
		System.out.println(bi.generateSentence());
	}
}
