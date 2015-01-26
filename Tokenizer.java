import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
public class Tokenizer {
	public ArrayList<String> tokens;
	private static final String regexSeparator = "([\\?!(),.\";:/\\|`])";  
	private static final String regexClitics = "(:|-|'S|'D|'M|'LL|'RE|'VE|N'T|'s|'d|'m|'ll|'re|'ve|n't)";
	private static final String tags = "<[^>]*>";
	private static final String num = "[0-9]";
	private static final String delimiter = "[\\t\\n\\r\\d]";
	private static final List<String> abbrList =  Arrays.asList("Co.", "Corp.", "vs.", "e.g.", "etc.", "ex.", "cf.",  
			"eg.", "est.", "Dept.", "Mr.", "Jr.", "Ms.", "Mrs.", "Dr.",  
			"Ph.D.","U.S.", "U.K.","Ltd.", "A.M.", "i.e.", "...");  
	private static JFileChooser ourChooser = new JFileChooser(System.getProperties().getProperty(".."));

	/**
	 * Brings up chooser for user to select a file
	 * 
	 * @return Scanner for user selected file, null if file not found
	 */
	public Tokenizer(){
		tokens = new ArrayList<String>();
	}

	public static Scanner getScanner() { 
		int retval = ourChooser.showOpenDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			File f = ourChooser.getSelectedFile();
			Scanner s;
			try {
				s = new Scanner(f);
			} catch (FileNotFoundException e) {
				return null;
			}
			return s;
		}
		return null;
	}

	public void Tokenize(){
		Scanner s = getScanner();
		String scan;
		while(s.hasNextLine()){
			scan = s.nextLine().toLowerCase().trim();
			scan = scan.replaceAll(regexClitics,"");
			scan = scan.replaceAll(regexSeparator,"");
			scan = scan.replaceAll(tags, "");
			scan = scan.replaceAll(delimiter, "");
			List<String> scanList = new ArrayList<String>();
			String [] line = scan.split(" ");
			for(int i = 0; i < line.length;  i++){
				if(line[i].equals("")){
					continue;
				}
				scanList.add(line[i]);
			}
			this.tokens.addAll(scanList);
		}
	}
	public static void main(String args []){
		Tokenizer t = new Tokenizer();
		t.Tokenize();
		t.printTokens();
	}

	public void printTokens() {
		System.out.println(tokens);  
	} 
}
