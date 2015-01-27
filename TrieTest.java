import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Test;


public class TrieTest {
	private String testExcerpt = "Gallop#apace,#you#Gallop#apace,#her#Gallop#apace,#me#Gallop#apace,#him#Gallop#you#aspace";
	private String[] toInsert = testExcerpt.split("#");
	List<String> trieSegments = new ArrayList<String>();

	private Trie simpleT = new Trie();
	private Trie complexT = new Trie();
	
	public void makeTrieSegments(){
		int curr = 2;
		while(curr < toInsert.length){
			StringBuilder sb = new StringBuilder();
			sb.append(toInsert[curr - 2] + "#");
			sb.append(toInsert[curr - 1]+ "#");
			sb.append(toInsert[curr] + "#");
			curr = curr + 1;
			System.out.println(sb.toString());
			trieSegments.add(sb.toString());
		}
		System.out.println(trieSegments.toString());
	}
	
	@Test
	public void SimpleTestEmpty() {
		assertTrue(simpleT.isEmpty());
		assertEquals(0,simpleT.getTotalFreq());
		assertEquals(0,simpleT.getVocabularySize());
		System.out.println("--------->" + getCountInExcerpt("Gallop#apace,"));
	}
	
	/**
	 * Test state of trie after insertions
	 * Size of trie should be equal to corpus length
	 * Size of vocab size should equal the number of distinct
	 * words in the trie 
	 */
	@Test
	public void SimpleTestNonEmpty(){
		simpleT.insertAll(Arrays.asList(toInsert));
		assertFalse(simpleT.isEmpty());
		assertEquals(toInsert.length,simpleT.getTotalFreq());
		Set<String> st = new HashSet<String>(Arrays.asList(toInsert));
		assertEquals(st.size(),simpleT.getVocabularySize());
	}
	
	@Test
	public void SimpleTestFrequencies(){
		simpleT.insertAll(Arrays.asList(toInsert));
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		for(int i = 0; i < toInsert.length; i++){
			if(wordMap.containsKey(toInsert[i])){
				int freq = wordMap.get(toInsert[i]);
				wordMap.put(toInsert[i], freq + 1);
			} else {
				wordMap.put(toInsert[i], 1);
			}
		}
		Collection<TrieNode> children = (Collection<TrieNode>)simpleT.getRoot().getChildren();
		if(children != null){
			Iterator<TrieNode> iterator = children.iterator();
			while(iterator.hasNext()) {
				TrieNode child = iterator.next();
				assertEquals(child.getFreq(), (int) wordMap.get(child.getKey()));
			}
		} 
	}
	
	@Test
	public void ComplexTestEmpty() {
		//assertTrue(complexT.isEmpty());
		assertEquals(0,complexT.getTotalFreq());
		assertEquals(0,complexT.getVocabularySize());

	}
	
	public int getCountInExcerpt(String s){
		System.out.println("======="+s);
		String [] seqArr = s.split("#");
		int seq = seqArr.length, curr = 0, count = 0;
		System.out.println("======="+seq);
		while(curr < toInsert.length - seq){
			boolean found = true;
			for(int  i = 0 ; i < seq ; i++){
				System.out.println(i);
				System.out.println(seqArr[i]);
				if(!(toInsert[curr + i].equals(seqArr[i]))){
					found = false;
				}
			}
			if(found) count ++;
			curr++;
		}
		return count;
	}
	
	@Test
	public void ComplexTestNonEmpty(){
		makeTrieSegments();
		complexT.insertAll(trieSegments);
		System.out.println(complexT.getTotalFreq());
		assertFalse(complexT.isEmpty());
		assertEquals(trieSegments.size(), complexT.getTotalFreq());
		Set<String> st = new HashSet<String>(Arrays.asList(toInsert));
		System.out.println("Vocab size is " + complexT.getVocabularySize());
		String sub1 = "Gallop#apace,#";
		String sub2 = "Gallop#";
		int count = complexT.stringFreq(sub1);
		assertEquals(count, getCountInExcerpt(sub1));
		assertEquals(complexT.stringFreq(sub2), getCountInExcerpt(sub2));
	}
	
	@Test
	public void ComplexTestChildren(){
		makeTrieSegments();
		complexT.insertAll(trieSegments);
		TrieNode tn1 = complexT.getRoot();
		Collection<TrieNode> children1 = (Collection<TrieNode>) tn1.getChildren();
		assertEquals(children1.size(), 6);
		TrieNode tn2 = complexT.get("Gallop#apace,#");
		Collection<TrieNode> children2 = (Collection<TrieNode>) tn2.getChildren();
		assertEquals(children2.size(), 4);
	}
}
