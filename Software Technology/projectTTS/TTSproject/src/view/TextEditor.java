package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import model.Document;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TextEditor extends JFrame implements ActionListener{
	public static JTextArea textArea = new JTextArea();
	//MENU
	private JMenuBar menuBar = new JMenuBar();
	//spinner model 
	private SpinnerNumberModel modelLine = new SpinnerNumberModel(1, 1, null, 1);
	private SpinnerNumberModel modelVol = new SpinnerNumberModel(0.0, 0.0, 10.0, 0.5);
	private SpinnerNumberModel modelPitch = new SpinnerNumberModel(0.0, 0.0, null, 10.0);
	//FILE
	private JMenu mnFile = new JMenu("File");
	public JMenuItem mntmNew = new JMenuItem("New");
	public JMenuItem mntmOpen = new JMenuItem("Open");
	public JMenuItem OpenFilepath = new JMenuItem("Open with filepath");
	public JMenuItem mntmSave = new JMenuItem("Save");
	JMenuItem mntmExit = new JMenuItem("Exit");
	//TEXT2SPEECH
	private JMenu mnTexttospeech = new JMenu("TextToSpeech");
	public JMenuItem mntmTransform = new JMenuItem("Transform");
	private JMenu mnLineTransform = new JMenu("Line Transform");
	private JLabel lblLineNumber = new JLabel("Line Number");
	private JSpinner spinner_3 = new JSpinner(modelLine);
	public JButton btnOk_4 = new JButton("OK");
	public JMenuItem mntmReverseTransform = new JMenuItem("Reverse Transform");
	private JMenu mnReverseLineTransform = new JMenu("Reverse Line Transform");
	private JLabel lblLineNumber_1 = new JLabel("Line Number");
	private JSpinner spinner_3_1 = new JSpinner(modelLine);
	public JButton btnOk_5 = new JButton("OK");
	//ENCODE
	private JMenu mnEncode = new JMenu("Encode");
	private JMenu mnEncode_1 = new JMenu("Encode");
	public JMenuItem mntmAtbash_1 = new JMenuItem("AtBash");
	public JMenuItem mntmRot_1 = new JMenuItem("Rot-13");
	private JMenu mnLineEncode = new JMenu("Line Encode");
	private JMenu mnAtbashLine = new JMenu("AtBash Line");
	private JLabel lblLineNumber_2_1 = new JLabel("Line Number");
	private JSpinner spinner_4_1 = new JSpinner(modelLine);
	public JButton btnOk_3_1 = new JButton("OK");
	private JMenu mnRotLine = new JMenu("Rot-13 Line");
	private JLabel lblLineNumber_2 = new JLabel("Line Number");
	private JSpinner spinner_4 = new JSpinner(modelLine);
	public JButton btnOk_3 = new JButton("OK");
	//TUNE
	private JMenu mnTune = new JMenu("Tune");
	private JMenu mnVolume = new JMenu("Volume");
	private JLabel lblIntegerValue = new JLabel("Float Value");
	private JSpinner spinner_2 = new JSpinner(modelVol);
	public JButton btnOk_2 = new JButton("OK");
	private JMenu mnSpeechRate = new JMenu("Speech Rate");
	private JLabel lblIntegerValue_1 = new JLabel("Float Value");
	private JSpinner spinner_1 = new JSpinner(modelPitch);
	public JButton btnOk_1 = new JButton("OK");
	private JMenu mnPitch = new JMenu("Pitch");
	private JLabel lblIntegerValue_1_1 = new JLabel("Float Value");
	private JSpinner spinner = new JSpinner(modelPitch);
	public JButton btnOk = new JButton("OK");
	//REPLAY
	JButton btnReplay = new JButton("Replay");
	//temp document item passed to commands pattern
	public Document currDoc = null;
	public String filepathOpen;
	//EDIT
	public JMenu mnEdit = new JMenu("Edit");
	public JMenuItem mntmReplaceWord = new JMenuItem("Replace word");
	public JMenuItem mntmReplaceSelection = new JMenuItem("Replace selection");
	//fields for testing purposes
	public int fakeLine = 0;
	public float fakeVol;
	public float fakePitch;
	public float fakeRate;
	private final JButton btnInfo = new JButton("Info");
	/**
	 * Launch the application./**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					
					TextEditor frame = new TextEditor();
					frame.setVisible(true);
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
		
	/**
	 * Create the frame.
	 */
	public TextEditor() {
		setTitle("Text To Speech Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 520);
		getContentPane().setLayout(new BorderLayout(0, 0));
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
		getContentPane().add(textArea, BorderLayout.CENTER);
		currDoc = new Document();

		/////////////////////////////////////////////////////////////////////
		// CREATE MENU BAR AND BUTTONS										
		////////////////////////////////////////////////////////////////////
		
		menuBar.add(mnFile);
			mnFile.add(mntmNew);
			mnFile.add(mntmOpen);
			mnFile.add(OpenFilepath);
			mnFile.add(mntmSave);
			mnFile.add(mntmExit);
		
		menuBar.add(mnEdit);
			mnEdit.add(mntmReplaceWord);
			mnEdit.add(mntmReplaceSelection);

			
		menuBar.add(mnTexttospeech);
			mnTexttospeech.add(mntmTransform);
			mnTexttospeech.add(mnLineTransform);
			mnLineTransform.add(lblLineNumber);
			mnLineTransform.add(spinner_3);
				mnLineTransform.add(btnOk_4);
			mnTexttospeech.add(mntmReverseTransform);
			mnTexttospeech.add(mnReverseLineTransform);
				mnReverseLineTransform.add(lblLineNumber_1);
					mnReverseLineTransform.add(spinner_3_1);
						mnReverseLineTransform.add(btnOk_5);

		menuBar.add(mnEncode);
			mnEncode.add(mnEncode_1);
				mnEncode_1.add(mntmAtbash_1);
				mnEncode_1.add(mntmRot_1);
			mnEncode.add(mnLineEncode);
				mnLineEncode.add(mnAtbashLine);
					mnAtbashLine.add(lblLineNumber_2_1);
						mnAtbashLine.add(spinner_4_1);
							mnAtbashLine.add(btnOk_3_1);
				mnLineEncode.add(mnRotLine);
					mnRotLine.add(lblLineNumber_2);
						mnRotLine.add(spinner_4);
							mnRotLine.add(btnOk_3);

		menuBar.add(mnTune);
			mnTune.add(mnVolume);
				mnVolume.add(lblIntegerValue);
					mnVolume.add(spinner_2);
						mnVolume.add(btnOk_2);
			mnTune.add(mnSpeechRate);
				mnSpeechRate.add(lblIntegerValue_1);
					mnSpeechRate.add(spinner_1);
						mnSpeechRate.add(btnOk_1);
			mnTune.add(mnPitch);
				mnPitch.add(lblIntegerValue_1_1);
					mnPitch.add(spinner);
						mnPitch.add(btnOk);

		menuBar.add(btnReplay);
		menuBar.add(btnInfo);
						
		setJMenuBar(menuBar);
		
		
		
		
		
		
		

		//add Listeners
		
		mntmNew.addActionListener(this);
		mntmOpen.addActionListener(this);
		mntmSave.addActionListener(this);
		OpenFilepath.addActionListener(this);
		mntmExit.addActionListener(this);
		
		mntmReplaceWord.addActionListener(this);
		mntmReplaceSelection.addActionListener(this);
			
		mntmTransform.addActionListener(this);
		btnOk_4.addActionListener(this);
		mntmReverseTransform.addActionListener(this);
		btnOk_5.addActionListener(this);
		
		btnOk_2.addActionListener(this);
		btnOk_1.addActionListener(this);
		btnOk.addActionListener(this);
		
		mntmAtbash_1.addActionListener(this);
		mntmRot_1.addActionListener(this);
		btnOk_3_1.addActionListener(this);
		btnOk_3.addActionListener(this);
		
		btnInfo.addActionListener(this);
		btnReplay.addActionListener(this);
	}
	
	//listener method
	public void actionPerformed(ActionEvent ea) 
	{
		
		////////////////////FILE//////////////////////////
		
		
		if(ea.getSource()==mntmNew) 
		{
			String author = JOptionPane.showInputDialog("Please input Author "
					+ "of the Document: ");
			String title = JOptionPane.showInputDialog("Please input Title of "
					+ "the Document: ");
			Text2SpeechEditorView.getCommandsFactory().createCommand("new");
			textArea.setText("");
			currDoc = new Document();
			getCurrDoc().setAudio();
			getCurrDoc().author = author;
			getCurrDoc().title = title;
			getCurrDoc().setCreateDate();	
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
		}
		
		else if(ea.getSource()==mntmOpen) {
			Text2SpeechEditorView.getCommandsFactory().filepath = getFilepath();
			Text2SpeechEditorView.getCommandsFactory().createCommand("open");
			currDoc = Text2SpeechEditorView.getCommandsFactory().getCurrDoc();
			getCurrDoc().setAudio();
			textArea.setText(getCurrDoc().getContents());
		}
		
		else if(ea.getSource()==mntmSave) {
			getCurrDoc().setModifiedDate();
			getCurrDoc().setContents(textArea.getText());
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			Text2SpeechEditorView.getCommandsFactory().createCommand("save");
		}
		
		else if(ea.getSource()==mntmExit) {
			System.exit(0);
		}
		
		else if(ea.getSource()==OpenFilepath) {
			filepathOpen = JOptionPane.showInputDialog("Please input the file "
					+ "path of the Document: \n (eg. /home/heehue/Documents/tts/q.txt) ");
			Text2SpeechEditorView.getCommandsFactory().filepath = filepathOpen;
			Text2SpeechEditorView.getCommandsFactory().createCommand("open");
			currDoc = Text2SpeechEditorView.getCommandsFactory().getCurrDoc();
			getCurrDoc().setAudio();
			textArea.setText(getCurrDoc().getContents());
		}
		
		
		/////////////////////////EDIT/////////////////////////////
		
		
		else if(ea.getSource()==mntmReplaceWord) {
			String word1 = JOptionPane.showInputDialog("Please input the word you wish to be replaced:");
			String word2 = JOptionPane.showInputDialog("Great, now input the word to replace with:");
			if(word1 != "") {
				Text2SpeechEditorView.getCommandsFactory().start = word1;
				Text2SpeechEditorView.getCommandsFactory().replace = textArea.getText();
				Text2SpeechEditorView.getCommandsFactory().end = word2;
				Text2SpeechEditorView.getCommandsFactory().createCommand("edit");
				textArea.setText(Text2SpeechEditorView.getCommandsFactory().word);
				currDoc.setContents(textArea.getText());
			}
		}
		
		else if(ea.getSource()==mntmReplaceSelection){
			String replace = JOptionPane.showInputDialog("Please input the text to replace the selected text:");
			String start = textArea.getText().substring (0, textArea.getSelectionStart());
			String end = textArea.getText().substring (textArea.getSelectionEnd());
			if(replace != "") {
				Text2SpeechEditorView.getCommandsFactory().edit = 1;
				Text2SpeechEditorView.getCommandsFactory().start = start;
				Text2SpeechEditorView.getCommandsFactory().replace = replace;
				Text2SpeechEditorView.getCommandsFactory().end = end;
				Text2SpeechEditorView.getCommandsFactory().createCommand("edit");
				textArea.setText(Text2SpeechEditorView.getCommandsFactory().replace);
				Text2SpeechEditorView.getCommandsFactory().edit = 0;
				currDoc.setContents(textArea.getText());
			}
		}
		
		
		///////////////////////TRANSFORM////////////////////////////
		
		
		else if(ea.getSource()==mntmTransform) {
			getCurrDoc().setContents(textArea.getText());
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			Text2SpeechEditorView.getCommandsFactory().createCommand("speakdoc");
		}
		
		else if(ea.getSource()==btnOk_4) {
			getCurrDoc().setContents(textArea.getText());
			if(fakeLine == 0 ) {
				int line = (int)spinner_3.getValue();
				if (line>countLines() ) {
					String error = Integer.toString(line-1);
					JOptionPane.showMessageDialog(null, "Line number is invalid try a number "
							+ "between 1 and " + error, "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
					Text2SpeechEditorView.getCommandsFactory().line = line;
					Text2SpeechEditorView.getCommandsFactory().createCommand("speakline");
				}
			}else {
				Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
				Text2SpeechEditorView.getCommandsFactory().line = fakeLine;
				Text2SpeechEditorView.getCommandsFactory().createCommand("speakline");
			}
		}
		
		else if(ea.getSource()==mntmReverseTransform) {
			getCurrDoc().setContents(textArea.getText());
			getCurrDoc().reverse = true;
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			Text2SpeechEditorView.getCommandsFactory().createCommand("speakdoc");
			
		}
		
		else if(ea.getSource()==btnOk_5) {
			getCurrDoc().setContents(textArea.getText());
			getCurrDoc().reverse = true;
			if(fakeLine == 0 ) {
				int line = (int)spinner_3_1.getValue();
				if (line>countLines() ) {
					String error = Integer.toString(line-1);
					JOptionPane.showMessageDialog(null, "Line number is invalid try a number "
							+ "between 1 and " + error, "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
					Text2SpeechEditorView.getCommandsFactory().line = line;
					Text2SpeechEditorView.getCommandsFactory().createCommand("speakline");
				}
			}else {
				Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
				Text2SpeechEditorView.getCommandsFactory().line = fakeLine;
				Text2SpeechEditorView.getCommandsFactory().createCommand("speakline");
			}
		}
		
		
		////////////////////////ENCODE///////////////////////
		
		
		else if(ea.getSource()==mntmAtbash_1) {
			getCurrDoc().setContents(textArea.getText());
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			Text2SpeechEditorView.getCommandsFactory().encod = "at";
			Text2SpeechEditorView.getCommandsFactory().line = 0;
			Text2SpeechEditorView.getCommandsFactory().createCommand("encode");
		}
		
		else if(ea.getSource()==mntmRot_1) {
			getCurrDoc().setContents(textArea.getText());
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			Text2SpeechEditorView.getCommandsFactory().encod = "rot";
			Text2SpeechEditorView.getCommandsFactory().line = 0;
			Text2SpeechEditorView.getCommandsFactory().createCommand("encode");	
		}
		
		else if(ea.getSource()==btnOk_3_1) {
			getCurrDoc().setContents(textArea.getText());
			if(fakeLine == 0 ) {
				int line = (int)spinner_4_1.getValue();
				if (line>countLines() ) {
					String error = Integer.toString(line-1);
					JOptionPane.showMessageDialog(null, "Line number is invalid try a number "
							+ "between 1 and " + error, "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
					Text2SpeechEditorView.getCommandsFactory().encod = "at";
					Text2SpeechEditorView.getCommandsFactory().line = line;
					Text2SpeechEditorView.getCommandsFactory().createCommand("encode");
				}
			}else {
				Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
				Text2SpeechEditorView.getCommandsFactory().encod = "at";
				Text2SpeechEditorView.getCommandsFactory().line = fakeLine;
				Text2SpeechEditorView.getCommandsFactory().createCommand("encode");
			}
		}
		
		else if(ea.getSource()==btnOk_3) {
			getCurrDoc().setContents(textArea.getText());
			if(fakeLine == 0 ) {
				int line = (int)spinner_4.getValue();
	
				if (line>countLines() ) {
					String error = Integer.toString(line-1);
					JOptionPane.showMessageDialog(null, "Line number is invalid try a number "
							+ "between 1 and " + error, "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
					Text2SpeechEditorView.getCommandsFactory().encod = "rot";
					Text2SpeechEditorView.getCommandsFactory().line = line;
					Text2SpeechEditorView.getCommandsFactory().createCommand("encode");
				}
			}else {
				Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
				Text2SpeechEditorView.getCommandsFactory().encod = "rot";
				Text2SpeechEditorView.getCommandsFactory().line = fakeLine;
				Text2SpeechEditorView.getCommandsFactory().createCommand("encode");
			}
		}
		
		
		//////////////////////TUNE//////////////////////////
		
		
		else if(ea.getSource()==btnOk_2) {
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			if(currDoc.fakeSpeech != 1) {
				float value = Float.valueOf(String.valueOf(spinner_2.getValue()));
				Text2SpeechEditorView.getCommandsFactory().tuneValue=value;
			}else {
				Text2SpeechEditorView.getCommandsFactory().tuneValue=fakeVol;
			}
			Text2SpeechEditorView.getCommandsFactory().tune = "vol";
			Text2SpeechEditorView.getCommandsFactory().createCommand("tune");
		}
		
		else if(ea.getSource()==btnOk_1) {
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			if(currDoc.fakeSpeech != 1) {
				float value = Float.valueOf(String.valueOf(spinner_1.getValue()));
				Text2SpeechEditorView.getCommandsFactory().tuneValue=value;
			}else {
				Text2SpeechEditorView.getCommandsFactory().tuneValue=fakeRate;
			}
			Text2SpeechEditorView.getCommandsFactory().tune = "speech";
			Text2SpeechEditorView.getCommandsFactory().createCommand("tune");
		}
		
		else if(ea.getSource()==btnOk) {
			Text2SpeechEditorView.getCommandsFactory().setCurrDoc(getCurrDoc());
			if(currDoc.fakeSpeech != 1) {
				float value = Float.valueOf(String.valueOf(spinner.getValue()));
				Text2SpeechEditorView.getCommandsFactory().tuneValue=value;
			}else {
				Text2SpeechEditorView.getCommandsFactory().tuneValue=fakePitch;
			}
			Text2SpeechEditorView.getCommandsFactory().tune = "pit";
			Text2SpeechEditorView.getCommandsFactory().createCommand("tune");
		}
		
		
		/////////////////////////???REPLAY?/////////////////////////////
		
		
		else if(ea.getSource()==btnReplay) {
			Text2SpeechEditorView.getCommandsFactory().createCommand("replay");
		}
		
		///////////////////////INFO////////////////////////////////////////
		else if(ea.getSource()==btnInfo) {
			String info = "Title: " + currDoc.title + "\nAuthor: " +
					currDoc.author + "\nCreation Date: " + currDoc.createDate +
					"\nLast Modified: " + currDoc.modifiedDate;
			JOptionPane.showMessageDialog(null, info, "Document INFO", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private String getFilepath() {
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().endsWith(".txt");
			}
			public String getDescription() {
				return "text file";
			}
		});
		chooser.setCurrentDirectory(new File("C:\\Documents"));
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		return file.getAbsolutePath();
	}
	

	private static int countLines(){
	   String[] lines = textArea.getText().split("\n");
	   return  lines.length;
	}
	
	//The following are for testing purposes
	
	public void setTextArea(String text) {
		textArea.setText(text);
	}
	
	public Document getCurrDoc() {
		return currDoc;
	}
	
	public void setCurrDoc(Document d) {
		currDoc = d;
	}
	
}

