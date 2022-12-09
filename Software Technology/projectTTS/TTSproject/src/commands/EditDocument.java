package commands;

import java.awt.event.ActionEvent;

public class EditDocument implements ActionListener{
	public EditDocument() {
		
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public String replaceAll(String oldString, String newString, String oldText) {
		if (!oldString.equals("")) {
			String newText = oldText.replaceAll(oldString, newString);
			return newText;
		}return null;
	}

	
	public String replaceSelection(String start, String end, String text) {
		text = start + text + end;
		return text;
	}

	
}
