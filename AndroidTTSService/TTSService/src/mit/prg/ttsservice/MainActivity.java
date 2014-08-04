package mit.prg.ttsservice;


import mit.prg.ttsservice.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//start the service from here //MyService is your service class name
		startService(new Intent(this, MySpeechService.class));
		
		

	}

	@Override
	protected void onNewIntent(Intent intent) {
	    setIntent(intent);
	    Intent i = getIntent();
		if(intent.getAction().equals("mit.prg.ttsservice.Speak")) {
			
	           Bundle bundle = intent.getExtras();
	            String text = (String)bundle.get(MySpeechService.SpeechExtra);
	            if(text != null) {
	 
	            	startService(i);
	            }
		}
		
		//super.onNewIntent(intent);
	}
	

	@Override
	protected void onResume() {
		Intent i = getIntent();
		
//		Intent msgIntent = new Intent(this, SimpleIntentService.class);
//		msgIntent.putExtra(MySpeechService.SpeechExtra, "Using an intent service");
//		startService(msgIntent);
		
		super.onResume();
	};
	
	
	public void soSpeech(View v) {
		
		Intent msgIntent = new Intent(this, MySpeechService.class);
		msgIntent.putExtra(MySpeechService.SpeechExtra, "hello world finally");
		startService(msgIntent);
		//startService(new Intent(this, SpeechService.class));

	}
//	




}