package com.example.weatherapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Aboutus extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aboutus, menu);
		return true;
	}
	
	public void goback(View view) {
		// Method for Go Back to Home Page
		Intent intent1=new Intent(Aboutus.this,MainActivity.class);
        startActivity(intent1);
     }

}
