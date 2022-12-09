package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;


import org.junit.Test;

import model.Document;
import view.TextEditor;

public class TU02 {
	//EditDocument command
	@SuppressWarnings("static-access")
	@Test 
	public void testEdit() {
		TextEditor te = new TextEditor();
		String set = "a b c d e f g"
				+ "h i j k l m n o p q r s t u v w x y z";
		te.currDoc.setContents(set);
		te.textArea.setText(te.currDoc.getContents());
		ActionEvent e = new ActionEvent(te.mntmReplaceWord, 0, null);
		te.actionPerformed(e);
		Document newDocument = te.getCurrDoc();
		assertNotEquals("Document's contents must be altered after Edit "
				+ "command", set, newDocument.getContents());
	}

}
