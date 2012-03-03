package com.name.helloandroid;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import android.widget.TabHost;


public class HelloAndroidActivity extends TabActivity {
    /** Called when the activity is first created. */
	
    // Local Bluetooth adapter
    //private BluetoothAdapter mBluetoothAdapter = null;
    private static final String TAG = "MainAppHelloAndroid";
    private static final boolean DEBUG = true;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DEBUG) Log.e(TAG, "++ ON Create ++");
        setContentView(R.layout.main);
        if(DEBUG) Log.e(TAG, "++ SetContentView ++");
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
        try{
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, GpsSync.class);


        if(DEBUG) Log.e(TAG, "++ Initializing TabSpec ++");
        
        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("gps").setIndicator("gps_sync",
                          res.getDrawable(R.drawable.ic_tab_gpssync))
                      .setContent(intent);
        tabHost.addTab(spec);
        }
        catch(Exception e)
        {
        	if(DEBUG) Log.e(TAG, "++ Caught an exception ++");
        }
        // Do the same for the other tabs
        intent = new Intent().setClass(this, OnlineSync.class);
        spec = tabHost.newTabSpec("online").setIndicator("online_syc",
                          res.getDrawable(R.drawable.ic_tab_onlinesync))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(1);

        /*
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
  		*/
        
    }    
        @Override
        public void onStart() {
            super.onStart();
            if(DEBUG) Log.e(TAG, "++ ON START ++");
            
        /*    
            // If BT is not on, request that it be enabled.
            // setupChat() will then be called during onActivityResult
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            }
		*/
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

        @Override
        public void onStop() {
            super.onStop();
            if(DEBUG) Log.e(TAG, "-- ON STOP --");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if(DEBUG) Log.e(TAG, "--- ON DESTROY ---");
        }
}