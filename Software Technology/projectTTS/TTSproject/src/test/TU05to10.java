package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import org.junit.Test;
import model.Document;
import text2speechapis.FakeTextToSpeechAPI;
import view.TextEditor;


public class TU05to10 {

	//TU05
	@Test
	public void testTTSdoc() { //Transform contents to speech
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		String s1 = "this is yet another sample text";
		te.setTextArea(s1);
		te.currDoc.setAudio();
		ActionEvent e1 = new ActionEvent(te.mntmTransform, 0, null);
		te.actionPerformed(e1);
		assertEquals("Tranform Contents failed",
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio, te.getCurrDoc().getContents());
	}
	
	//TU06
	@Test
	public void testLineTTS() { //Transform Line to speech
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		te.fakeLine = 2; // (2nd) second line (String s22) will be test subject
		String s21 = "this is yet\n";
		String s22 = "another sample\n";
		String s23 = "text";
		te.setTextArea(s21+s22+s23);
		te.currDoc.setAudio();
		ActionEvent e2 = new ActionEvent(te.btnOk_4, 0, null);
		te.actionPerformed(e2);
		assertEquals("Transform line contents failed",
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio, 
				te.getCurrDoc().getLine(te.fakeLine - 1));
	}
	
	//TU07
	@Test
	public void testReverseTTS() { //Transform Reverse contents to speech
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		String s3 = "stay home and stay\nsafe";
		te.setTextArea(s3);
		te.currDoc.setAudio();
		ActionEvent e3 = new ActionEvent(te.mntmReverseTransform, 0, null);
		te.actionPerformed(e3);
		assertEquals("Reverse transform of contents failed", 
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio,
				te.getCurrDoc().reverse(te.getCurrDoc().getContentAsLine()));
	}
	
	//TU08
	@Test
	public void testReverseLineTTS() { //Transform Reverse Line to speech
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		te.fakeLine = 1;
		String s31 = "stay home\n";
		String s32 = "and stay\n";
		String s33 = "safe";
		te.setTextArea(s31 + s32 + s33);
		te.currDoc.setAudio();
		ActionEvent e3 = new ActionEvent(te.btnOk_5, 0, null);
		te.actionPerformed(e3);
		assertEquals("Reverse transform of line failed", 
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio,
				te.getCurrDoc().reverse(te.getCurrDoc().getLine(te.fakeLine - 1)));
	}
	
	//TU09
	@Test
	public void testEncode() { //Encode contents and transform
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		String s4 = "stay and stay\nsafe";
		te.setTextArea(s4);
		te.currDoc.setAudio();
		ActionEvent e41 = new ActionEvent(te.mntmAtbash_1, 0, null);
		te.actionPerformed(e41);
		assertEquals("Encode atBash and transform of contents failed", 
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio,
				te.getCurrDoc().getEncodedContents());
		ActionEvent e42 = new ActionEvent(te.mntmRot_1, 0, null);
		te.actionPerformed(e42);
		assertEquals("Encode rot13 and transform of contents failed", 
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio,
				te.getCurrDoc().getEncodedContents());
	}
	
	//TU10
	@Test
	public void testLineEncode() { //Encode line and transform
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		te.fakeLine = 3;
		String s5 = "I gained 15\nkilos during\nthis\nquarantine";
		te.setTextArea(s5);
		te.currDoc.setAudio();
		ActionEvent e51 = new ActionEvent(te.btnOk_3_1, 0, null);
		te.actionPerformed(e51);
		assertEquals("Encode atBash and transform of line failed", 
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio,
				te.getCurrDoc().getEncodedLine(te.fakeLine - 1));
		ActionEvent e52 = new ActionEvent(te.btnOk_3, 0, null);
		te.actionPerformed(e52);
		assertEquals("Encode rot13 and transform of line failed", 
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).toAudio,
				te.getCurrDoc().getEncodedLine(te.fakeLine - 1));
	}
	
}
