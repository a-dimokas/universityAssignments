package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import encodingstrategies.EncodingStrategy;
import text2speechapis.FakeTextToSpeechAPI;
import text2speechapis.FreeTTSAdapter;
import text2speechapis.TextToSpeechAPI;

public class Document implements Serializable{
	private static final long serialVersionUID = 1L;
	public int fakeSpeech = 0; // changed only by tests
	public String author;
	public String title;
	public String filePath;
	public String createDate;
	public boolean reverse = false;
	public void setCreateDate() {
		createDate = this.getCurrentDate();
	}


	public String modifiedDate;
	public void setModifiedDate() {
		modifiedDate = this.getCurrentDate();
	}


	public ArrayList<Line> lines = new ArrayList<Line>();
	
	
	public Document() {
		
	}
	
	public void setContents(String s) {
		lines.clear();
		for (String line : s.split("\n")) {
			Line newLine = new Line();
			newLine.createLine(line);
			lines.add(newLine);
		}
	}
	
	public String getContents() {
		String content="";
		Iterator<Line> i = lines.iterator();
		while(i.hasNext()) {
			content += i.next().getLine();
			if(i.hasNext())
				content += "\n";
		}
		return content;
	}
	
	public String getContentAsLine() {
		String content="";
		Iterator<Line> i = lines.iterator();
		while(i.hasNext()) {
			content += i.next().getLine();
		}
		return content;
	}
	
	public String getLine(int num) {
		return lines.get(num).getLine();
	}
	public String getEncodedLine(int num) {
		return lines.get(num).getEncodedLine();
	}
	
	public transient EncodingStrategy encodingStrategy;
	private transient TextToSpeechAPI audioManager;
	
	public void setAudio() {
		if(fakeSpeech == 0) {
			audioManager = new FreeTTSAdapter();
		}else audioManager = new FakeTextToSpeechAPI();
	}
	public TextToSpeechAPI getAudio() {
		return audioManager;
	}
	
	
	public void playContents() {
		audioManager.play(getContents());
	}
	public void playReverseContents() {
		audioManager.play(reverse(getContentAsLine()));
		reverse = false;
	}
	public void playEncodedContents() {
		String encoded = encodingStrategy.encode(getContents());
		audioManager.play(encoded);
	}
	public String getEncodedContents() {
		return encodingStrategy.encode(getContents());
	}
	public void playLine(int num) {
		lines.get(num-1).setLineAudio(audioManager);
		lines.get(num-1).playLine();
	}
	public void playReverseLine(int num) {
		lines.get(num-1).setLineAudio(audioManager);
		lines.get(num-1).playReverseLine();
		reverse = false;
	}
	public void playEncodedLine(int num) {
		lines.get(num-1).setLineAudio(audioManager);
		lines.get(num-1).playEncodedLine();
	}
	public void tuneEncodingStrategy(EncodingStrategy es) {
		encodingStrategy = es;
	}
	
	
	public String getCurrentDate(){
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
	
	public String reverse(String unreversed) {
		String rev = null;
		for(int i = unreversed.length() - 1; i>=0; i--) {
			rev = rev + unreversed.charAt(i);
		}
		return rev;
	}
	
	
}
