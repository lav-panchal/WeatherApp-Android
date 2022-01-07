package com.example.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity  {
    EditText etCity;
    Button btn1;
    private final String url="http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "07f56a24ebf8b5b3aa0669d546120f61";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = (EditText)findViewById(R.id.etCity);
        btn1=(Button)findViewById(R.id.button1);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Method for option menu
    	if(item.getItemId()==R.id.aboutus){
    		Intent intent1=new Intent(MainActivity.this,Aboutus.class);
            startActivity(intent1);
    	}
		return super.onOptionsItemSelected(item);
	}

	public void getWeatherDetails(View view) {
		// Method for get data from user and pass data to the Output file
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        if(city.equals("")){
            Toast.makeText(getApplicationContext(), "field cannot be empty...!", Toast.LENGTH_SHORT).show();
        }else{ 
            tempUrl = url + "?q=" + city + "&appid=" + appid;
            Intent intent = new Intent(getApplicationContext(), Output.class);
            intent.putExtra("link", tempUrl); 
            startActivity(intent);
        }
     }
  }