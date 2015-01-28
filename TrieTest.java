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

/**
 * TrieTest --- Test the correctness of Trie implementation
 * @author Sarah Lee
 */
public class TrieTest {
	private String testExcerpt = "Gallop#apace,#you#Gallop#apace,#her#Gallop#apace,#me#Gallop#apace,#him#Gallop#you#aspace#";
	private String[] toInsert = testExcerpt.split("#");
	private Trie simpleT = new Trie();
	private Trie complexT = new Trie();

	//Helper function to split up text excerpt
	public List<String> makeTrieSegments(){
		List<String> trieSegments = new ArrayList<String>();
		int curr = 2;
		while(curr < toInsert.length){
			StringBuilder sb = new StringBuilder();
			sb.append(toInsert[curr - 2] + "#");
			sb.append(toInsert[curr - 1]+ "#");
			sb.append(toInsert[curr] + "#");
			curr = curr + 1;
			trieSegments.add(sb.toString());
		}
		return trieSegments;
	}

	//Helper function to find number of times string appears in excerpt
	public int getCountInExcerpt(String s){
		String [] seqArr = s.split("#");
		int seq = seqArr.length, curr = 0, count = 0;
		while(curr < toInsert.length - seq){
			boolean found = true;
			for(int  i = 0 ; i < seq ; i++){
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
	public void SimpleTestEmpty() {
		assertTrue(simpleT.isEmpty());
		assertEquals(0,simpleT.getTotalFreq());
		assertEquals(0,simpleT.getVocabularySize());
	}

	/**
	 * Test state of Trie after insertions. Size of Trie should be equal 
	 * to corpus length Size of vocabulary should equal the number of distinct
	 * words in the Trie 
	 */
	@Test
	public void SimpleTestNonEmpty(){
		simpleT.insertAll(Arrays.asList(toInsert));
		assertFalse(simpleT.isEmpty());
		assertEquals(toInsert.length,simpleT.getTotalFreq());
		Set<String> st = new HashSet<String>(Arrays.asList(toInsert));
		assertEquals(st.size(),simpleT.getVocabularySize());
	}

	/**
	 * Test frequency values of Trie nodes by putting each word of excerpt 
	 * in hash map with count as its value and comparing to Trie nodes counts
	 */
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
		assertTrue(complexT.isEmpty());
		assertEquals(0,complexT.getTotalFreq());
		assertEquals(0,complexT.getVocabularySize());

	}

	/**
	 * Test state of Trie after insertions. Size of Trie should be equal 
	 * to corpus length Size of vocab size should equal the number of distinct
	 * words in the Trie. Test frequency of prefix count after insert. 
	 */
	@Test
	public void ComplexTestInsert(){
		List<String> seg = makeTrieSegments();
		complexT.insertAll(seg);
		String sub1 = "Gallop#apace,#";
		String sub2 = "Gallop#";
		String sub3 = "Gallap#apace#";
		String sub4 = "apace,#";
		assertFalse(complexT.isEmpty());
		assertEquals(seg.size(), complexT.getTotalFreq());
		int count = complexT.stringFreq(sub1);
		assertEquals(count, getCountInExcerpt(sub1));
		assertEquals(complexT.stringFreq(sub2), getCountInExcerpt(sub2));
		assertEquals(complexT.stringFreq(sub3), getCountInExcerpt(sub3));
		assertEquals(complexT.stringFreq(sub4), getCountInExcerpt(sub4));
	}

	/**
	 * Tests number of children for each prefix
	 * including root node
	 */
	@Test
	public void ComplexTestChildren(){
		List<String> seg = makeTrieSegments();
		complexT.insertAll(seg);
		String [] keys = {"Gallop#apace,#","Gallop#","Gallop#you#","Gallop#apace,#you#",
				"apace,#", "you#Gallop#"};
		int [] childCount = {4,2,1,0,4,1};
		for(int i = 0 ; i < keys.length; i++){
			TrieNode tn = complexT.get(keys[i]);
			Collection<TrieNode> children = (Collection<TrieNode>) tn.getChildren();
			assertEquals(children.size(), childCount[i]);
		}
		TrieNode root = complexT.getRoot();
		Collection<TrieNode> rootChildren = (Collection<TrieNode>) root.getChildren();
		assertEquals(rootChildren.size(), 6);
		assertEquals(complexT.stringFreq(""),0);
	}
}
