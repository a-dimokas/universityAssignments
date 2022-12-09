package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI{
	
	/**
	 * 
	 */
	private Voice voice; 
	private VoiceManager vm;
	
	private float vol = 5f; //default value 0.5
	public float getVol() {
		return vol;
	}
	public void setVolume(float vol) {
		voice.setVolume(vol/10);//Setting the volume of the voice 
	}
	private float rate = 190f; //deafult value 190
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		voice.setRate(rate);//Setting the rate of the voice
	}
	private float pitch = 150f; //fefault value 150
	public float getPitch() {
		return pitch;
	}
	public void setPitch(float pitch) {
		voice.setPitch(pitch);//Setting the Pitch of the voice
	}
	
	public FreeTTSAdapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin");
	}
	
	public void play(String a) {
        voice.allocate();
		try {
			voice.speak(a);
		} catch (Exception e1) {
            e1.printStackTrace();
        }
	}
	
}
