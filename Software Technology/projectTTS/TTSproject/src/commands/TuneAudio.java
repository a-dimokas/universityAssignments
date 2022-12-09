package commands;

import java.awt.event.ActionEvent;

import model.Document;

public class TuneAudio implements ActionListener{
	public Document currDoc;
	public float value;
	public String tune;
	public TuneAudio() {
		
	}
	public void actionPerformed(ActionEvent event) {
		if(tune=="vol") {
			currDoc.getAudio().setVolume(value);
		}else if(tune=="pit") {
			currDoc.getAudio().setPitch(value);
		}else {
			currDoc.getAudio().setRate(value);
		}
	}
}
