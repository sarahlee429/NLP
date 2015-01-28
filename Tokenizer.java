import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * Tokenizer --- A class that parses and formats a given corpus
 * @author Sarah Lee
 */
public class Tokenizer {
	private List<String> tokens;
	private static final String regexPunc = "([\\?!(),\";:/\\|`])";  
	private static final String regexTags = "<[^>]*>";
	private static final String regexNum = "[0-9]";
	private static final String regexBreak = "[\\t\\n\\r\\d]";
	private static JFileChooser ourChooser = new JFileChooser(System.getProperties().getProperty(".."));

	/**
	 * Brings up chooser for user to select a file
	 * @return Scanner for user selected file, null if file not found
	 */
	public Tokenizer(){
		tokens = new ArrayList<String>();
	}
	
	public List<String> getTokens(){
		return tokens;
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
			scan = scan.replaceAll(regexPunc,"");
			scan = scan.replaceAll(regexNum,"");
			scan = scan.replaceAll(regexTags, "");
			scan = scan.replaceAll(regexBreak, "");
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

	public void printTokens() {
		System.out.println(tokens);  
	} 
	
	public static void main(String args[]){
		Tokenizer t = new Tokenizer();
		t.Tokenize();
		t.printTokens();
	}
}
