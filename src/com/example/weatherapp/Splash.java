package com.example.weatherapp;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {

	 Handler handler;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_splash);
	        // Hold splash screen for 3 seconds after that Redirect to Home Page 
	        handler=new Handler();
	        handler.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                Intent intent=new Intent(Splash.this,MainActivity.class);
	                startActivity(intent);
	                finish();
	            }
	        },3000);
	    }
}
