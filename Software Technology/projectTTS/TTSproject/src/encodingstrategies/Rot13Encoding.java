package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding{
	public Rot13Encoding() {
		
	}
	
	public String encode(String word) {
		
		char[] values = word.toCharArray();
		
        for (int i = 0; i < values.length; i++) {
        	
            char letter = values[i];
            values[i] = mapCharacter(letter);
        
        }
        // Convert array to a new String.
        return new String(values);
    }
	
	public char mapCharacter(char c) {
        if (c >= 'a' && c <= 'z') {
            // Rotate lowercase letters.

            if (c > 'm') {
                c -= 13;
            } 
            else {
                c += 13;
            }
        } 
        
        else if (c >= 'A' && c <= 'Z') {
            // Rotate uppercase letters.

            if (c > 'M') {
                c -= 13;
            } 
            else {
                c += 13;
            }
        }
        return c;
	}
}
