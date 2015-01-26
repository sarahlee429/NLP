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
	
	public String generateSentence(){
        StringBuilder sentence = new StringBuilder();
        String s1 = "";
        while(!s1.contains(".") && sentence.length() <= 150){
        	String w = t.generateWord(s1);
        	sentence.append(w);
        	sentence.append(" ");
        	s1 = w;
        }
        return sentence.toString();
    }
	
	public void print(String filename){
		t.print(filename);
	}

}
