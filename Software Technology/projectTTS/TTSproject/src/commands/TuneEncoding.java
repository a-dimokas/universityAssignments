package commands;

import java.awt.event.ActionEvent;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;

public class TuneEncoding implements ActionListener{
	public Document currDoc;
	public String encod = null;
	public int line = 0;
	StrategiesFactory enFac = new StrategiesFactory();
	EncodingStrategy en ;
	public TuneEncoding() {
		
	}
	public void actionPerformed(ActionEvent event) {
		en = enFac.createStrategy(encod);
		if(line == 0) {
			currDoc.tuneEncodingStrategy(en);
			currDoc.playEncodedContents();
		}else {
			currDoc.lines.get(line-1).tuneEncodingStrategy(en);;
			currDoc.playEncodedLine(line);
		}
	}
}
