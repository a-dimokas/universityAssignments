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

public class TU03 {
	//SaveDocument command
	
	@Test
	public void testSave() {
		TextEditor te = new TextEditor();
		Document newDoc = new Document();
		newDoc.setContents("sample text");
		te.setCurrDoc(newDoc);
		ActionEvent e = new ActionEvent(te.mntmSave, 0, null);
		te.actionPerformed(e);
		String filepath = newDoc.filePath;
		assertEquals("Saved Document's content must match the contents of the Document saved on disk "
				+ "after SaveCommand", getSavedDoc(filepath).getContents(), te.getCurrDoc().getContents());
	}
	
	public Document getSavedDoc(String filepath) {
		FileInputStream fi = null;
		ObjectInputStream oi = null;
		Document currDoc = null;
		try {
			if(filepath.endsWith(".txt")) {
				fi = new FileInputStream(filepath);
			}else {
				fi = new FileInputStream(filepath + ".txt");
			}
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
