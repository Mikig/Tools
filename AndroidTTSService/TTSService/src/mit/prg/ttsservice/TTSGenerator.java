package mit.prg.ttsservice;

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;

public class TTSGenerator implements TextToSpeech.OnInitListener {

	private TextToSpeech tts;
	
	public void init(Context context) {
		if(tts == null)
		{
			tts = new TextToSpeech(context, this);
		}
	}
	
	public void onInit(int status) {
		
		tts.setLanguage(Locale.US);
		
		speak("Finished setting up");
	}

	public void speak(String sayThis) {
	    tts.speak(sayThis, TextToSpeech.QUEUE_FLUSH, null);
	}


	public void shutdown() {
	    tts.shutdown();
	}

}
