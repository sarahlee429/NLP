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

	public double addOneSmoothedProbability(String s){
		int count = t.stringFreq(s);
		System.out.println("the count is " + count);
		System.out.println("t size is " + t.getTotalFreq());
		if (count > 0) {
			return count + 1 / (t.getTotalFreq() + (t.getVocabularySize()));
		}
		return 0.0;
	}

	public double unsmoothedProbability(String s){
		int count = t.stringFreq(s);
		System.out.println("the coutn is " + count);
		System.out.println("t size is " + t.getTotalFreq());
		if (count > 0) {
			return count / t.getTotalFreq();
		}
		return 0.0;
	}

	public String generateSentence(){
		StringBuilder sentence = new StringBuilder();
		String s1 = "";
		while(!s1.contains(".") && sentence.length() <= 150){
			String w = t.generateWord(s1);
			sentence.append(w + " ");
			s1 = w;
		}
		String ret = Character.toString(sentence.toString().charAt(0)).toUpperCase() + 
				sentence.toString().substring(1);
		if(ret.charAt(ret.length() - 1) != '.'){
			ret = ret + ".";
		}
		return ret;
	}


	public void print(String filename){
		t.print(filename);
	}

}
