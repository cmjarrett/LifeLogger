package com.name.helloandroid;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class UploadToServer extends Activity{
    private static final boolean DEBUG = true;
    private static final String TAG = "UploadToServer";
    
    private static final int REQUEST_UPLOAD_TO_SERVER = 1;
    private static final int REQUEST_DOWNLOAD_FROM_SERVER = 3;
    static final String EXTRA_RETURN_STRING = "return_string";
    
    private static final String retString = "Hello mr Online Sync.  This is mr Upload to Server";
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DEBUG) { Log.e(TAG, "++ On Create ++"); }
        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.upload_to_server);
        

        

        
    }
	
	public void onDestroy() {
        super.onDestroy();
	}
	
    @Override
    public synchronized void onResume() {
        super.onResume();
        if(DEBUG) Log.e(TAG, "+ ON RESUME +");

    }

    @Override
    public synchronized void onPause() {
        super.onPause();
        if(DEBUG) Log.e(TAG, "- ON PAUSE -");
    }
    
    public void onReturnToOnlineSyncClick(View view)
    {
    	if(DEBUG) { Log.e(TAG, "++ Returning to OnlineSync from uploading to server ++"); }
    	
    	
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RETURN_STRING, retString);
    	
        // Set result and finish this Activity
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
	
}
