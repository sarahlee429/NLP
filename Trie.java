import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Trie {
	TrieNode root;
	Collection<String> vocab;
	int size;

	public Trie(){
		root = new TrieNode("");
		vocab = new ArrayList<String>();
		size = 0;
	}
	public Trie(List<String> vocab){
		this.vocab.addAll(vocab);
	}

	public void insert(String s){
		vocab.add(s);
		root.insert(s);
	}

	public void insertAll(Collection<String> toInsert){
		Iterator<String> it = toInsert.iterator();
		while(it.hasNext()){
			String next = it.next();
			root.insert(next);
			vocab.add(next);
		}
	}

	public TrieNode get(String s){
		return root.get(s);
	}

	public boolean contains(String s){
		return root.contains(s);
	}

	public void traverse(){
		root.traverse();
	}

	public void getCounts(Map<Integer, Integer> map){
		root.getCounts(map);
	}
}
