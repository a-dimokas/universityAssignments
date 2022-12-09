package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import encodingstrategies.EncodingStrategy;
import text2speechapis.FreeTTSAdapter;
import text2speechapis.TextToSpeechAPI;

public class Line implements Serializable{
	/**
	 * Iterator<Line> i = lines.iterator();
		while(i.hasNext()) {
			i.next().setLineAudio(audioManager);
		}
	 */
	private static final long serialVersionUID = 1612670777362576806L;
	ArrayList<String> words = new ArrayList<String>();
	private transient EncodingStrategy encodingStrategy;
	private transient TextToSpeechAPI audioManager;
	
	public void setLineAudio(TextToSpeechAPI audiomgr) {
		if(audiomgr==null) {
			audioManager = new FreeTTSAdapter();
		}
		audioManager = audiomgr;
	}
	
	public void playLine() {
		audioManager.play(getLine());
	}
	
	public void playReverseLine() {
		audioManager.play(reverse(getLine()));
	}
	
	public void playEncodedLine() {
		String encodedLine = encodingStrategy.encode(getLine());
		audioManager.play(encodedLine);
	}
	public String getEncodedLine() {
		return encodingStrategy.encode(getLine());
	}
	
	public void tuneEncodingStrategy(EncodingStrategy es) {
		encodingStrategy = es;
	}
	
	public Line() {
		
	}
	
	public void createLine(String s) {
		for (String word : s.split(" ")) {
			words.add(word);
		}
	}
	
	public String getLine() {
		String line="";
		Iterator<String> i = words.iterator();
		while(i.hasNext()) {
			line += i.next() ;
			if(i.hasNext())
				line += " ";
		}
		return line ;
	}
	
	public String reverse(String unreversed) {
		String rev = null;
		for(int i = unreversed.length() - 1; i>=0; i--) {
			rev = rev + unreversed.charAt(i);
		}
		return rev;
	}
	
}
