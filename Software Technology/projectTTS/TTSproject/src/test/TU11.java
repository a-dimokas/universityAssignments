package test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Document;
import text2speechapis.FakeTextToSpeechAPI;
import view.TextEditor;
import java.awt.event.ActionEvent;

public class TU11 {
	//this test is for speech's volume
	@Test
	public void testVolume() {
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		te.fakeVol = 0.344f;
		te.currDoc.setAudio();
		ActionEvent e = new ActionEvent(te.btnOk_2, 0, null);
		te.actionPerformed(e);
		assertEquals("Volume of TextToSpeechAPI isn't changed",0.344f,
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).volume, 0.002);
	}
	
	//this test is for speech's pitch
	@Test
	public void testPitch() {
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		te.fakePitch = 230.44f;
		te.currDoc.setAudio();
		ActionEvent e = new ActionEvent(te.btnOk, 0, null);
		te.actionPerformed(e);
		assertEquals("pitch of TextToSpeechAPI isn't changed",230.44f,
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).pitch, 0.002);
	}

	//this test is for speech's rate
	@Test
	public void testRate() {
		TextEditor te = new TextEditor();
		te.currDoc = new Document();
		te.currDoc.fakeSpeech = 1;
		te.fakeRate = 358.28f;
		te.currDoc.setAudio();
		ActionEvent e = new ActionEvent(te.btnOk_1, 0, null);
		te.actionPerformed(e);
		assertEquals("rate of TextToSpeechAPI isn't changed",358.28f,
				((FakeTextToSpeechAPI) te.getCurrDoc().getAudio()).rate, 0.002);
	}
}
