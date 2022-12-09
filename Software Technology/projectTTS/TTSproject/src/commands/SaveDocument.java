package commands;

import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Document;

public class SaveDocument implements ActionListener{
	public String filepath;
	public Document currDoc;
	public SaveDocument() {
		
	}
	
	public void saveDoc(Document dc) {
		JFileChooser fc = new JFileChooser();
		ObjectOutputStream o = null;
		FileOutputStream f = null;
		try {
			if (fc.showSaveDialog(null) != JFileChooser.CANCEL_OPTION) {
				filepath = fc.getSelectedFile().getAbsolutePath();
			}
			if(filepath.endsWith(".txt")) {
				f = new FileOutputStream(filepath);
			}else {
				f = new FileOutputStream(filepath + ".txt");
			}
			o = new ObjectOutputStream(f);

			// Write objects to file
			dc.filePath = filepath;
			o.writeObject(dc);

			JOptionPane.showMessageDialog(null, "Saved to " + filepath,
		    		"Save File", JOptionPane.PLAIN_MESSAGE);  
			
		}
		catch (IOException e) {
	    	JOptionPane.showMessageDialog(null, "Cannot save document to file: " + filepath,
	        "Error", JOptionPane.ERROR_MESSAGE);
	    	e.printStackTrace();
		} finally {

			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
			
	}
	public void actionPerformed(ActionEvent event) {
		
	}

	
	
	
	
	
	
	
}
