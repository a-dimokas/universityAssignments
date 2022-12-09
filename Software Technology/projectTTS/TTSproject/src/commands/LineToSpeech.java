package commands;

import java.awt.event.ActionEvent;

import model.Document;

public class LineToSpeech implements ActionListener{
	public Document currDoc;
	public int line;
	public LineToSpeech() {
		
	}
	public void actionPerformed(ActionEvent event) {
		
		if(currDoc.reverse == false) {
			
			currDoc.playLine(line);
		}else {
			currDoc.playReverseLine(line);
		}
	}
}
