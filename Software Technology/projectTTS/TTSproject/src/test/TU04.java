package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import org.junit.Test;

import model.Document;
import view.TextEditor;

public class TU04 {
	//OpenDocument command  test
	
		@Test
		public void testOpen() {
			TextEditor te = new TextEditor();
			ActionEvent e = new ActionEvent(te.OpenFilepath, 0, null);
			te.actionPerformed(e);
			assertEquals("Opened Document's content must match the contents of the Document on disk"
					+ " after OpenCommand", getSavedDoc(te.filepathOpen).getContents(), te.getCurrDoc().getContents());
		}
	
	
	
	public Document getSavedDoc(String filepath) {
		FileInputStream fi = null;
		ObjectInputStream oi = null;
		Document currDoc = null;
		try {
			fi = new FileInputStream(filepath);
			oi = new ObjectInputStream(fi);
	
			// Read object
			currDoc = (Document) oi.readObject();

			
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "File not found: " + filepath,
			        "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			if (fi != null) {
				try {
					fi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oi != null) {
				try {
					oi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return currDoc;
	}
}
