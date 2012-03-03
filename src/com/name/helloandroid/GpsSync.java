package com.name.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GpsSync extends Activity{
	
    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    private static final boolean DEBUG = true;
    private static final String TAG = "GpsSync";
    
    /* Items received from our messaging service */
    public static final String DEVICE_NAME = "__DEVICE__";
    public static final String TOAST = "toast";
	
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 3;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DEBUG) Log.e(TAG, "++ ON Create ++");
        setContentView(R.layout.gpslayout);
        
    }
    
    public void onBluetoothDiscoveryClick(View v){
    	if(DEBUG) Log.e(TAG, "++ Creating Discovery Intent++");
    	Intent bluetoothDiscoveryIntent = new Intent(this,BluetoothDiscovery.class);
    	if(DEBUG) Log.e(TAG, "++ Starting Discovery Activity++");
    	startActivityForResult(bluetoothDiscoveryIntent, REQUEST_CONNECT_DEVICE);
    }
    
    
}