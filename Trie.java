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

public class Trie {
	private TrieNode root; //root of the trie
	private int totalFreq;  //represents the size of the corpus this trie holds

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

	public void insert(String s){
		root.insert(s);
		totalFreq ++;
	}

	public void insertAll(Collection<String> toInsert){
		Iterator<String> it = toInsert.iterator();
		while(it.hasNext()){
			String next = it.next();
			root.insert(next);
			totalFreq++;
		}
	}
	
	public boolean isEmpty(){
		System.out.println("total freq is " + totalFreq);
		return (totalFreq == 0);
	}

	/**
	 * Finds the node in the trie containing 
	 * input string s
	 * @return TrieNode containing the string
	 */
	public TrieNode get(String s){
		return root.get(s);
	}

	public boolean contains(String s){
		return root.contains(s);
	}

	public void traverse(){
		root.traverse();
	}

	public int stringFreq(String s){
		TrieNode t = get(s);
		if(t != null) return t.getFreq();
		return 0;
	}
	/**
	 * Populates a good Turing map for the Trie
	 * 
	 * @return none
	 */
	public void getCounts(Map<Integer, Integer> map){
		root.getCounts(map);
	}

	/**
	 * Given a preceding word, generates a random word
	 * from its children. 
	 * @return string key value of child chosen
	 */
	public String generateWord(String predecessor) {
		String ret = "";
		TrieNode currNode = get(predecessor);
		if(currNode == null) {
			currNode = root;
		}
		Random rand = new Random();
		int randomInt = rand.nextInt((int)currNode.getFreq());
		ret = currNode.randomWalk(randomInt + 1);
		if(ret == null) return currNode.getKey();
		return ret;
	}

	/**
	 * Iteratively calculates number of nodes
	 * in the Trie
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
	 * Prints to a specified file all the contents
	 * in (word,word_count) format
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
