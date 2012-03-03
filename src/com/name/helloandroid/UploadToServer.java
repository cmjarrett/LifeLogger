package com.name.helloandroid;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class UploadToServer extends Activity{
    private static final boolean DEBUG = true;
    private static final String TAG = "UploadToServer";
    
    private static final int REQUEST_UPLOAD_TO_SERVER = 1;
    private static final int REQUEST_DOWNLOAD_FROM_SERVER = 3;
    private static final int GPS_COORDINATE_LENGTH = 18;
    
    static final String EXTRA_RETURN_STRING = "return_string";
    private static List<String> coordinates;
    private static final String retString = "Hello mr Online Sync.  This is mr Upload to Server";
    private static String GPS_FILENAME = "gps_coordinates";
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DEBUG) { Log.e(TAG, "++ On Create ++"); }
        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.upload_to_server);
        coordinates = new ArrayList<String>();
        ListView et = (ListView) findViewById(R.id.coordinatesAdded);
        
        try{
	        FileInputStream fi = openFileInput(GPS_FILENAME);
	        if(DEBUG) { Log.e(TAG, "Opened the file correctly"); }
	        String str = "";
	        int counter = 1;
	        
	        int byt = fi.read();
	        if(DEBUG) { Log.e(TAG, "Read a byt without error"); }
	        while (byt != -1)
	        {
	        	if(DEBUG) { Log.e(TAG, "Read a byt without error |  " + (char)byt); }
	        	str = str + (char)byt;
	        	
	        	if(counter == GPS_COORDINATE_LENGTH)
	        	{
	        		coordinates.add(str);
	        		counter = 1;
	        		str = "";
	        		if(DEBUG) { Log.e(TAG, "Adding: " + str); }
	        	}
	        	
	        	byt = fi.read();
	        	counter++;
	        }
	        fi.close();
        }
        catch(Exception e)
        {
        	if(DEBUG) { Log.e(TAG, "I done screwed up!"); }
        }
        et.setAdapter(new ArrayAdapter<String>(this,R.layout.list_item,coordinates));
        //setListAdapter(new ArrayAdapter<String>(this,R.layout.list_item,coordinates));

        

        
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
