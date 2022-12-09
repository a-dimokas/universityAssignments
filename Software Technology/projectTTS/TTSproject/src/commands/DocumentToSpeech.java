package commands;

import java.awt.event.ActionEvent;
import model.Document;

public class DocumentToSpeech implements ActionListener{
	public Document currDoc ;
	public DocumentToSpeech() {
		
	}

	

	@Override
	public void actionPerformed(ActionEvent event) {
		if(currDoc.reverse == false) {
			currDoc.playContents();
		}else {
			currDoc.playReverseContents();
		}
	}
	
}
