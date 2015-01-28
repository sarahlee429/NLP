import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TrieNode --- Maintains TrieNode used to construct and maintain a Trie
 * @author Sarah Lee
 */
public class TrieNode{
	private static final String WORD_MARKER = "#"; //used to mark the end of a word
	private boolean isWord; //marks the end of a word
	private String key; //the string associated with the node
	private Map<String,TrieNode> children; //node's children with string as key
	private int freq; //number of times the key occurs in the corpus

	public TrieNode(String toInsert){
		key = toInsert;
		isWord = false;
		children = new HashMap<String,TrieNode>();
		freq = 0;
	}
	
    //getters and setters for TrieNode fields
	public String getKey(){
		return key;
	}

	public boolean isWord(){
		return isWord;
	}
	
	public void setIsWord(boolean word){
		this.isWord = word;
	}
	
	public int getFreq(){
		return freq;
	}

	public void setFreq(int s){
		freq = s;
	}
	
	public void incFreq(){
		freq ++;
	}
	
	public Collection<TrieNode> getChildren(){
		return children.values();
	}
	
	public int getNumChildren(){
		return children.values().size();
	}

	/**
	 * Inserts a string as words separated by designated marker 
	 * @param string s to add to trie
	 * @return none
	 */
	public void insert(String s){
		String [] toInsert = s.split(WORD_MARKER);
		insertRec(toInsert, 0);
	}

	//Recursive helper method for insert
	public void insertRec(String[] s, int index){
		freq++;
		//Base Case: We have inserted all of the words in s
		if(index == s.length) {
			isWord = true;
			return;
		}
		TrieNode child = children.get(s[index]);
		//Recursive Case:
		//1) If a child matches, recurse on that child
		//2) Otherwise make a new node for the string then recurse
		if(child == null){
			children.put(s[index],new TrieNode(s[index]));
			children.get(s[index]).insertRec(s, index + 1);
		} else {
			child.insertRec(s, index + 1);
		}
	}
	
	/**
	 * Searches if s is contained in this node or descendants
	 * @param string s to locate in Trie
	 * @return true found or false if none found
	 */
	public boolean contains(String query){
		return !(get(query) == null);
	}
	
	/**
	 * Finds the child that contains s
	 * @param string s to locate in Trie
	 * @return TrieNode containing the string
	 */
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

	/**
	 * Updates/inserts this node to given map
	 * @param a map passed by ref to update
	 * @return none
	 */
	public void applyTuringCounts(Map<Integer,Integer> map){
		if(!key.equals("")){
			//map contains this node's freq as key
			if(map.containsKey(getFreq())){
				Integer val = map.get(getFreq());
				//increment value for key
				map.put(getFreq(), val + 1);
			
			} else {
				//otherwise insert with init value of 1
				map.put(getFreq(), 1);
			}
		}
		for(TrieNode n: children.values()){
			n.applyTuringCounts(map);
		}
	}

	/**
	 * Prints value of this node then recurse on all children
	 * @param none
	 * @return none
	 */
	public void traverse(){
		System.out.println(key);
		if(children == null) return;
		for(TrieNode n: children.values()){
			n.traverse();
		}
	}
	
	/**
	 * Picks a random child to return a string of its key
	 * @param a random integer between 1 - this node's totalFreq
	 * @return a string key of one of its children
	 */
	public String generateWord(int r){
		int curr = 0;
		if(children == null) return null;
		Iterator<String> it = children.keySet().iterator();
		while(it.hasNext()){
			TrieNode t = children.get(it.next());
			curr = curr + t.getFreq();
			if(curr >= r) return t.getKey();
		}
		return null;
	}

	/**
	 * Picks a random child to return a string of its key
	 * @param buffered writer
	 * @param cumulative key of its parents if any
	 * @return none
	 */
	public void print(Writer out, String keys) throws IOException{
		if(children != null){
			Iterator<String> it = children.keySet().iterator();
			while(it.hasNext()) {
				TrieNode child = children.get(it.next());
				child.print(out, keys + key + " ");
			}
		} 
		if(isWord) out.write(keys + key + " " + Integer.toString(freq)+"\n");
	}
}