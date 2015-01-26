import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

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
		t.insert("pika#chu");
		t.insert("pika#pi");
		t.insert("pika#pika");
		System.out.println(t.stringFreq("pika"));
		System.out.println(t.stringFreq("pika#chu"));
	}*/
}
