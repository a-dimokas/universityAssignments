package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy{
	public TemplateEncoding() {
		
	}
	public abstract String encode(String word) ;
	public abstract char mapCharacter(char a);
}
