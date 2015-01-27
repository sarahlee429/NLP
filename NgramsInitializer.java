import java.util.ArrayList;
import java.util.List;

public class NgramsInitializer {
	static final String WORD_MARKER = "#";
	static final boolean goodTuring = false;
	static final boolean addOne = true;
	Tokenizer t;

	public NgramsInitializer(){
		t = new Tokenizer();
		t.Tokenize();
	}
	
	public Ngrams makeNgrams(int n){
		List<String> ngrams = new ArrayList<String>();
		List<String> tokens = t.tokens;
		Trie t = new Trie();
		for(int i = 0; i < tokens.size() - n - 1; i++){
			ngrams.add(tokens.get(i) + WORD_MARKER + tokens.get(i + 1) + WORD_MARKER + tokens.get(i + 2));
		}
		t.insertAll(ngrams);
		Ngrams b = new Ngrams(t,n);
		//System.out.println(trigrams.toString());
		return b;
	}
	
	public static void main(String args[]){
		NgramsInitializer i = new NgramsInitializer();
		Ngrams u = i.makeNgrams(3);
		u.generateTuringMap();
		u.printTuringMap();
		((Ngrams)u).print("/Users/Sarah/Desktop/trie.txt");
		System.out.println(((Ngrams)u).generateSentence());
		System.out.println(((Ngrams)u).unsmoothedProbability("reward"));
	}
}
