import java.util.Map;
import java.util.TreeMap;

public class Ngrams {
	protected Trie t;
	protected int n;
	protected Map <Integer,Integer> turingMap;

	public Ngrams(Trie t, int n){
		this.t = t;
		this.n = n;
		turingMap = new TreeMap<Integer, Integer>();
	}

	public void generateTuringMap(){
		t.getCounts(turingMap);
	}
	
	public void printTuringMap(){
		for(Integer i: turingMap.keySet()){
			System.out.println("Key: " + i);
			System.out.println("Value: " + turingMap.get(i));
		}
	}

}
