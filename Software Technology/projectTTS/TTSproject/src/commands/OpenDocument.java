package commands;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JOptionPane;
import model.Document;

public class OpenDocument implements ActionListener{

	public Document currDoc;
	public OpenDocument() {
		
	}
	
	public Document openDocument(String filepath) {
		FileInputStream fi = null;
		ObjectInputStream oi = null;
		currDoc = null;
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
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
