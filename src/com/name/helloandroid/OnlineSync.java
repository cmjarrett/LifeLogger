package com.name.helloandroid;

import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OnlineSync extends Activity {
    private static final boolean DEBUG = true;
    private static final String TAG = "GpsSync";
    
    private static final int REQUEST_UPLOAD_TO_SERVER = 1;
    private static final int REQUEST_DOWNLOAD_FROM_SERVER = 3;
    private static final int GPS_COORDINATE_LENGTH = 18;
    private static final String EXTRA_RETURN_STRING = "return_string";
    private static GpsCoordinate[] coordinates;
    private static String GPS_FILENAME = "gps_coordinates";
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DEBUG) { Log.e(TAG, "++ On Create ++"); }
        setContentView(R.layout.onlinelayout);
    }
        
    public void onOnlineSyncClick(View view)
    {
    	if(DEBUG) { Log.e(TAG, "++ Leaving OnlineSync, about to initiate an online sync ++"); }
    	Intent uploadIntent = new Intent(this,UploadToServer.class);

    	coordinates = new GpsCoordinate[40];
    	
    	GpsCoordinate gp;
    	for(int i=0;i<40;i++)
    	{
    			gp = new GpsCoordinate("000000000000" + i,(i*130)/16,(i*330)/11);
    			
    			coordinates[i] = gp;
    	}
    	
    	
    	try{
    		FileOutputStream fos = openFileOutput(GPS_FILENAME, Context.MODE_PRIVATE);
    		for(GpsCoordinate gps : coordinates)
    		{
    			fos.write(gps.toString().substring(0,GPS_COORDINATE_LENGTH).getBytes());
    		}
    		fos.close();
    	}
    	catch(Exception e)
    	{
    		if(DEBUG) { Log.e(TAG, "Error saving gps coordinates to a file so that we can sync"); }
    	}
    	
    	
    	if(DEBUG) Log.e(TAG, "++ Starting UploadToServer++");
    	startActivityForResult(uploadIntent,REQUEST_UPLOAD_TO_SERVER);
    	
    }
        
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(DEBUG) Log.d(TAG, "onActivityResult " + resultCode);
        
        // Get the return string
        String address = data.getExtras()
            .getString(UploadToServer.EXTRA_RETURN_STRING);
        
        
        switch (requestCode) {
        case REQUEST_UPLOAD_TO_SERVER:
            // When Uploading to our server is successful
            if (resultCode == Activity.RESULT_OK) {
            	TextView tView = new TextView(this);
            	tView.setText("Returned from Uploading.  Return Message: " + address);
            	setContentView(tView);
            }
            break;
        case REQUEST_DOWNLOAD_FROM_SERVER:
        	// When Downloading from our server is successful
            if (resultCode == Activity.RESULT_OK) {
            	
            }
            break;
        }
    }
        
}