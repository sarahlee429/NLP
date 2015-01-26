import java.util.ArrayList;
import java.util.List;

public class NgramsInitializer {
	static final String WORD_MARKER = "#";
	Tokenizer t;

	public NgramsInitializer(){
		t = new Tokenizer();
		t.Tokenize();
	}

	public Unigrams makeUnigrams(){
		List<String> tokens = t.tokens;
		Trie t = new Trie();
		for(int i = 0; i < tokens.size(); i++){
			t.insert(tokens.get(i));
		}
		Unigrams u = new Unigrams(t);
		return u;
	}
	
	public Bigrams makeBigrams(){
		List<String> bigrams = new ArrayList<String>();
		List<String> tokens = t.tokens;
		Trie t = new Trie();
		for(int i = 0; i < tokens.size() - 1; i++){
			bigrams.add(tokens.get(i) + WORD_MARKER + tokens.get(i + 1));
		}
		t.insertAll(bigrams);
		Bigrams b = new Bigrams(t);
		return b;
	}
	
	public static void main(String args[]){
		NgramsInitializer i = new NgramsInitializer();
		Unigrams u = i.makeUnigrams();
		u.generateTuringMap();
		u.printTuringMap();
	}
}
