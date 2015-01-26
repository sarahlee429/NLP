import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrieNode{
	static final int ALPHABET_SIZE = 26;
	static final String WORD_MARKER = "#";
	private boolean isLeaf;
	private boolean isWord;
	private String key;
	private Map<String,TrieNode> children;
	private int freq;

	public TrieNode(String toInsert){
		key = toInsert;
		isLeaf = false;
		isWord = false;
		children = new HashMap<String,TrieNode>();
		freq = 0;
	}

	public int getFreq(){
		return freq;
	}

	public String getKey(){
		return key;
	}

	public void setFreq(int s){
		freq = s;
	}
	public void incFreq(){
		freq ++;
	}

	public Collection<?> getChildren(){
		return children.values();
	}

	public boolean isLeaf(){
		return isLeaf;
	}

	public void setIsLeaf(boolean leaf){
		this.isLeaf = leaf;
	}

	public void setIsWord(boolean word){
		this.isWord = word;
	}

	public boolean isWord(){
		return isWord;
	}

	public void insert(String s){
		String [] toInsert = s.split(WORD_MARKER);

		insertRec(toInsert, 0);
	}

	public void insertRec(String[] s, int index){
		freq++;
		if(index == s.length) return;
		if(index == s.length - 1) isWord = true;
		TrieNode child = children.get(s[index]);
		if(child == null){
			children.put(s[index],new TrieNode(s[index]));
			children.get(s[index]).insertRec(s, index + 1);
		} else {
			child.insertRec(s, index + 1);
		}
	}

	public TrieNode get(String s){
		TrieNode current = this;
		String [] toFind = s.split(WORD_MARKER);
		for(int i = 0; i < toFind.length; i++){
			TrieNode child = current.children.get(toFind[i]);
			if(child == null) return null;
			current = child;
		}
		return current;
	}

	public void getCounts(Map<Integer,Integer> map){
		if(key.equals("")){} else{
			if(map.containsKey(getFreq())){
				Integer val = map.get(getFreq());
				map.put(getFreq(), val + 1);
			} else {
				map.put(getFreq(), 1);
			}
		}
		for(TrieNode n: children.values()){
			n.getCounts(map);
		}
	}

	public void traverse(){
		System.out.println(key);
		if(children == null){
			return;
		}
		for(TrieNode n: children.values()){
			n.traverse();
		}
	}

	public boolean contains(String query){
		return !(get(query) == null);
	}
}