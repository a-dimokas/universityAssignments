package text2speechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI {
	public String toAudio;
	public float volume;
	public float pitch;
	public float rate;
	@Override
	public void play(String a) {
		// TODO Auto-generated method stub
		toAudio = a;
	}

	@Override
	public void setVolume(float a) {
		// TODO Auto-generated method stub
		volume = a;
	}

	@Override
	public void setPitch(float a) {
		// TODO Auto-generated method stub
		pitch = a;
	}

	@Override
	public void setRate(float a) {
		// TODO Auto-generated method stub
		rate = a;
	}

}

