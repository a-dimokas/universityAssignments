package view;

import commands.CommandsFactory;
import model.Document;

public class Text2SpeechEditorView {
	public static TextEditor j;
	public Document currentDocument;
	public static CommandsFactory commandsFactory = new CommandsFactory();
	
	public static CommandsFactory getCommandsFactory() {
		return commandsFactory;
	}

	private Text2SpeechEditorView() {
	}
	
	public Document getCurrentDocument() {
		
		return currentDocument;
		
	}

	public static Text2SpeechEditorView getSingletonView() {
		Text2SpeechEditorView singletonView = new Text2SpeechEditorView();
		return singletonView;
	}
	
	public static void main(String[] args) {
		j = new TextEditor();
		j.setVisible(true);
	}




}
