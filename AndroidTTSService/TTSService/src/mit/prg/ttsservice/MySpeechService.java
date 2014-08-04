package mit.prg.ttsservice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;
 

public class MySpeechService extends Service{
 
	public static final String SpeechAction = "mit.prg.ttsservice.Speak";
	public static final String SpeechExtra = "mit.prg.ttsservice.TextToSpeak";

	private static final String TAG = "TTSService";
    //used for keep track on Android running status
    public static Boolean         mIsServiceRunning             = false;
    
 
    private TTSGenerator ttsGen;
 //   private TextToSpeech mTts;
    private int mTtsInitialized = 0;
    private BroadcastReceiver yourReceiver;


 
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
 
    @Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		// TODO Auto-generated method stub
		return super.bindService(service, conn, flags);
	}

	@Override public void onCreate() {
    	
    	super.onCreate();
    	
    	ttsGen = new TTSGenerator();
    	
        final IntentFilter theFilter = new IntentFilter();
        theFilter.addAction(SpeechAction);
        this.yourReceiver = new BroadcastReceiver() {


			@Override
			public void onReceive(Context context, Intent intent) {
				
//		       	Toast.makeText(context, "TTSService got broadcast intent " + intent.toString(), 
//		        		Toast.LENGTH_SHORT).show();
		        
	            //handle the intent
	            Bundle bundle = intent.getExtras();
	            String text = (String)bundle.get(SpeechExtra);
	            if(text != null) {
	            	ttsGen.speak(text);
//	                Toast.makeText(context, "TTSService speaking: " + text, Toast.LENGTH_SHORT).show();
	            }		
			}
        };
        // Registers the receiver so that your service will listen for
        // broadcasts
        this.registerReceiver(this.yourReceiver, theFilter);  	
    }
  
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	
        try {
       
        	ttsGen.init(getApplicationContext());
        	
//        	Toast.makeText(this.getBaseContext(), "TTSService got intent " + intent.toString(), 
//        		Toast.LENGTH_SHORT).show();
        

            //handle the intent
            Bundle bundle = intent.getExtras();
            String text = (String)bundle.get(SpeechExtra);
            if(text != null) {
            	ttsGen.speak(text);
//                Toast.makeText(this.getBaseContext(), "TTSService speaking: " + text, Toast.LENGTH_SHORT).show();
                         }
     
		} catch (Exception e) {

			e.printStackTrace();
			
		}
        
        return START_STICKY;
     }
  
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
    	ttsGen.shutdown();
    	
    	unregisterReceiver(this.yourReceiver);
    }
 

}