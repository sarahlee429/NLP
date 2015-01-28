import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * Trie --- Trie data structure to keep track of NGrams model
 * @author Sarah Lee
 */
public class Trie {
	private TrieNode root; //root of the Trie
	private int totalFreq;  //represents the size of the corpus this Trie holds

	//constructor
	public Trie(){
		root = new TrieNode("");
		totalFreq = 0;
	}

	//field getters
	public int getTotalFreq(){
		return totalFreq;
	}

	public TrieNode getRoot(){
		return root;
	}

	/**
	 * Inserts a given word
	 * @param String s to add to Trie
	 * @return none
	 */
	public void insert(String s){
		root.insert(s);
		totalFreq ++;
	}

	/**
	 * Inserts a list of words 
	 * @param Collection of words to add to Trie
	 * @return none
	 */
	public void insertAll(Collection<String> toInsert){
		Iterator<String> it = toInsert.iterator();
		while(it.hasNext()){
			String next = it.next();
			root.insert(next);
			totalFreq++;
		}
	}

	public boolean isEmpty(){
		return (totalFreq == 0);
	}

	/**
	 * Finds the node in the Trie containing 
	 * @param string s to locate in Trie
	 * @return TrieNode containing the string
	 */
	public TrieNode get(String s){
		return root.get(s);
	}

	public boolean contains(String s){
		return root.contains(s);
	}

	/**
	 * Pre-order Trie traversal
	 * @return none
	 */
	public void traverse(){
		root.traverse();
	}

	/**
	 * Calculates the total times a string occurs
	 * @param string s to find the frequency for
	 * @return frequency of given string as int
	 */
	public int stringFreq(String s){
		TrieNode t = get(s);
		if(t != null) return t.getFreq();
		return 0;
	}
	
	/**
	 * Populates a good Turing map for the Trie
	 * @param map to populate Good-Turing counts
	 * @return none
	 */
	public void applyTuringCounts(Map<Integer, Integer> map){
		root.applyTuringCounts(map);
	}

	/**
	 * Given a preceding word, generates a random word from its children.
	 * @param the parent or preceding string
	 * @return string key value of child chosen
	 */
	public String generateWord(String predecessor) {
		String ret = "";
		TrieNode currNode = get(predecessor);
		if(currNode == null) {
			currNode = root;
		}
		Random rand = new Random();
		//Generates a random int from range 1 - totalFreq of predecessor
		ret = currNode.generateWord(1 + rand.nextInt((int)currNode.getFreq()));
		if(ret == null) return currNode.getKey();
		return ret;
	}

	/**
	 * Iteratively calculates number of nodes in the Trie
	 * @return total number of nodes in the Trie
	 */
	public int getVocabularySize(){
		int size = 0;
		LinkedList<TrieNode> stack = new LinkedList<TrieNode>();
		stack.add(root);
		while(!stack.isEmpty()){
			TrieNode f = stack.remove();
			for(Object child : f.getChildren()){
				size++;
				stack.add((TrieNode) child);
			}
		}
		return size;
	}

	/**
	 * Prints to a specified file all the contents in (word,word_count) format
	 * @param the file path to print output
	 * @return none
	 */
	public void print(String filename){
		File file = new File(filename);
		Writer output;
		try {
			output = new BufferedWriter(new FileWriter(file));
			root.print(output, "");
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}    
	}
}
