package test;

import static org.junit.Assert.*;
import org.junit.Test;
import encodingstrategies.EncodingStrategy;
import model.Document;
import view.TextEditor;
import java.awt.event.ActionEvent;

public class TU12 {
	//Change encoding test
	@Test
	public void testChangeEncode() {
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.setAudio();
		ActionEvent e1 = new ActionEvent(te.mntmAtbash_1, 0, null);
		te.actionPerformed(e1);
		EncodingStrategy first = te.currDoc.encodingStrategy;
		ActionEvent e2 = new ActionEvent(te.mntmRot_1, 0, null);
		te.actionPerformed(e2);
		assertNotEquals("Encoding Strategy wasn't changed", first, te.currDoc.encodingStrategy);
	}
}
