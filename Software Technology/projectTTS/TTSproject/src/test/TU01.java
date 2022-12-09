package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;

import model.Document;
import view.TextEditor;

public class TU01 {
	//NewDocument Command
	@Test
	public void testNew() {
		TextEditor te = new TextEditor();
		ActionEvent e = new ActionEvent(te.mntmNew, 0, null);
		te.actionPerformed(e);
		Document emptyDocument = new Document();
		assertNotEquals("New Document must not be empty", emptyDocument, te.getCurrDoc());
	}

}
