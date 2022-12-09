package encodingstrategies;

public class StrategiesFactory {
	
	private EncodingStrategy ret;
	public StrategiesFactory() {
		ret = null;
	}
	public EncodingStrategy createStrategy(String enc) {
		if(enc == "rot") {
			ret = new Rot13Encoding();
			return ret;
		}else if(enc == "at"){
			ret = new AtBashEncoding();
			return ret;
		}return ret;
	}
}
