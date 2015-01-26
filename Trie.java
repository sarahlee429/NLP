import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Trie {
	TrieNode root;
	int size;

	public Trie(){
		root = new TrieNode("");
		size = 0;
	}
	public void insert(String s){
		root.insert(s);
	}

	public void insertAll(Collection<String> toInsert){
		Iterator<String> it = toInsert.iterator();
		while(it.hasNext()){
			String next = it.next();
			root.insert(next);
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

	public int stringFreq(String s){
		TrieNode t = get(s);
		if(t != null) return t.getFreq();
		return 0;
	}
	
	public void getCounts(Map<Integer, Integer> map){
		root.getCounts(map);
	}
	 
	public String generateWord(String predecessor) {
         String ret = "";
         TrieNode currNode = get(predecessor);
         if(currNode == null) {
        	 currNode = root;
         }
         Random rand = new Random();
         int randomInt = rand.nextInt((int)currNode.getFreq());
         ret = currNode.randomWalk(randomInt + 1);
         return ret;
 }

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
	
	/*public static void main(String args []){
		Trie t = new Trie();
		t.insert("pika chu");
		t.insert("pika pi");
		t.insert("pika pika");
		t.insert("pika pika");
		t.insert("pika pika");
		t.insert("pika pika");
		t.insert("pika pika");
		t.insert("pika pikaaaaa");
		System.out.println(t.stringFreq("pika"));
		System.out.println(t.stringFreq("pika chu"));
		System.out.println(t.generateWord("pika"));
	}*/
}
