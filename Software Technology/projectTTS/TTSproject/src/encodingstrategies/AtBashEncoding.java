package encodingstrategies;

import static java.util.Map.entry;
import java.util.Map;

public class AtBashEncoding extends TemplateEncoding{
	
	private Map<String, String> keyLow = Map.ofEntries(
			entry("a", "z"), entry("b", "y"), entry("c", "x"), entry("d", "w"), 
			entry("e", "v"), entry("f", "u"), entry("g", "t"), entry("h", "s"),	
			entry("i", "r"), entry("j", "q"), entry("k", "p"), entry("l", "o"), 
			entry("m", "n"), entry("n", "m"), entry("o", "l"), entry("p", "k"), 
			entry("q", "j"), entry("r", "i"), entry("s", "h"), entry("t", "g"),
			entry("u", "f"), entry("v", "e"), entry("w", "d"), entry("x", "c"),
			entry("y", "b"), entry("z", "a")
	);
		
	private Map<String, String> keyUp = Map.ofEntries(
		entry("A", "Z"), entry("B", "Y"), entry("C", "X"), entry("D", "W"), entry("E", "V"),
		entry("F", "U"), entry("G", "T"), entry("H", "S"), entry("I", "R"), entry("J", "Q"),
		entry("K", "P"), entry("L", "O"), entry("M", "N"), entry("N", "M"), entry("O", "L"),
		entry("P", "K"), entry("Q", "J"), entry("R", "I"), entry("S", "H"), entry("T", "G"),
		entry("U", "F"), entry("V", "E"), entry("W", "D"), entry("X", "C"), entry("Y", "B"),
		entry("Z", "A")
	);
	
	
	
	public AtBashEncoding() {
		
	}
	
	public String encode(String plaintext) {
		String ciphertext = "";
		
		for(int i =0; i < plaintext.length(); i++) {
			char c = plaintext.charAt(i);
			if(Character.isLetter(c)) {
				ciphertext += mapCharacter(c);
			}else {
				ciphertext += c;
			}
				
		}
		return ciphertext;
	}
	
	
	//string.charAt(index)
	public char mapCharacter(char a) {
		
		String s = Character.toString(a);
		
		if (Character.isUpperCase((a))) {
			
			return keyUp.get(s).charAt(0);
	
		}else if(Character.isLowerCase((a))){
			
			return keyLow.get(s).charAt(0);
		}
		return a;
	}
}
