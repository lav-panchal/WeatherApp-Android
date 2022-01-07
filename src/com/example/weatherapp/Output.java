package com.example.weatherapp;

import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Output extends Activity {
	TextView Result,tv1,tv2,tv3,tv4,tv5;
	String tempUrl = "";
	DecimalFormat df = new DecimalFormat("#.##");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_output);
		Result = (TextView)findViewById(R.id.tvResult);
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		tv3 = (TextView)findViewById(R.id.textView3);
		tv4 = (TextView)findViewById(R.id.textView4);
		tv5 = (TextView)findViewById(R.id.textView5);
		Intent intent = getIntent(); 
		String str = intent.getStringExtra("link");
		
		tempUrl=str;
		StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            	String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");
                    String cityName = jsonResponse.getString("name");
                    
                    JSONObject jsonObjectsys = jsonResponse.getJSONObject("sys");
                    String country = jsonObjectsys.getString("country");
                    
                    Result.setTextColor(Color.rgb(0,0,0));
                    output += "\n Description: " + description
                            + "\n Cloudiness: " + clouds + "%";
                    Result.setText(output);
                    tv1.setText(df.format(temp) + " °C");
                    tv2.setText(cityName+","+country);
                    tv3.setText(humidity+" %");
                    tv4.setText(wind+ " m/s");
                    tv5.setText(pressure+ " hPa");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Invalid Input...!", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(Output.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }
	
	
	 @Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
	    	if(item.getItemId()==R.id.aboutus){
	    		Intent intent1=new Intent(Output.this,Aboutus.class);
	            startActivity(intent1);
	    	}
			return super.onOptionsItemSelected(item);
		}
	
	public void goback(View view) {
		// Method for Go Back to Home Page
		Intent intent1=new Intent(Output.this,MainActivity.class);
        startActivity(intent1);
     }
}



