package commands;

import model.Document;

public class CommandsFactory {
	public int edit = 0;
	public String start, end, replace, word;
	public String tune;
	public float tuneValue;
	public int line = -1;
	public String encod = null;
	public Document currDoc = null;
	public Document getCurrDoc() {
		return currDoc;
	}
	public void setCurrDoc(Document currDoc) {
		this.currDoc = currDoc;
	}
	public String filepath;
	
	//private ReplayManager repMngr;
	public CommandsFactory() {
		//repMngr = new ReplayManager();
	}

	public ActionListener createCommand(String actionWord) {
		if(actionWord == "save") {
			SaveDocument saveDocument = new SaveDocument();
			saveDocument.actionPerformed(null);
			saveDocument.saveDoc(currDoc);
			//repMngr.commandsList.add(saveDocument);
			return saveDocument;
		}
		else if(actionWord == "open") {
			OpenDocument openDocument = new OpenDocument();
			openDocument.actionPerformed(null);
			currDoc = openDocument.openDocument(filepath);
			//repMngr.commandsList.add(openDocument);
			return openDocument;
		}
		else if(actionWord == "new") {
			NewDocument newDocument = new NewDocument();
			newDocument.actionPerformed(null);
			//repMngr.commandsList.add(newDocument);
			return newDocument;
		}
		else if(actionWord == "edit") {
			EditDocument editDocument = new EditDocument();
			if(edit == 1) {
				replace = editDocument.replaceSelection(start, end, replace);
			}else {
				word = editDocument.replaceAll(start, end, replace);
			}
			//repMngr.commandsList.add(editDocument);
			return editDocument;
		}
		else if(actionWord == "speakline") {
			LineToSpeech lineToSpeech = new LineToSpeech();
			lineToSpeech.line = line;
			lineToSpeech.currDoc = currDoc;
			lineToSpeech.actionPerformed(null);
			//repMngr.commandsList.add(lineToSpeech);
			return lineToSpeech;
		}
		else if(actionWord == "speakdoc") {
			DocumentToSpeech documentTS = new DocumentToSpeech();
			documentTS.currDoc = currDoc;
			documentTS.actionPerformed(null);
			//repMngr.commandsList.add(documentTS);
			return documentTS;
		}
		else if(actionWord == "replay") {
			ReplayCommand replayCommand = new ReplayCommand(null); 
			replayCommand.actionPerformed(null);
			//return replayCommand;
		}
		else if(actionWord == "tune") {
			TuneAudio tuneAudio = new TuneAudio(); 
			tuneAudio.currDoc = currDoc;
			tuneAudio.value = tuneValue;
			tuneAudio.tune = tune;
			tuneAudio.actionPerformed(null);
			//repMngr.commandsList.add(tuneAudio);
			return tuneAudio;
		}
		else if(actionWord == "encode") {
			TuneEncoding tuneEncoding = new TuneEncoding(); 
			tuneEncoding.encod = encod;
			tuneEncoding.line = line;
			tuneEncoding.currDoc = currDoc;
			tuneEncoding.actionPerformed(null);
			//repMngr.commandsList.add(tuneEncoding);
			return tuneEncoding;
		}
		return null;
	}
}
