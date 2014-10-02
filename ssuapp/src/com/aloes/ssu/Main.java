package com.aloes.ssu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.aloes.ssu.campusmap.MapStartActivity;
import com.aloes.ssu.food.MainActivity;
import com.aloes.ssu.schoolinfo.SchoolinfoActivity;
import com.aloes.ssu.setting.SettingStartActivity;
import com.aloes.ssu.timetable.TimetableMain;
import com.google.android.gcm.GCMRegistrar;

public class Main extends Activity {

	private Button timeTableBtn;
	private Button infoBtn;
	private Button foodBtn;
	private Button mapBtn;
	private Button settingBtn;
	private BackPressCloseHandler backPressCloseHandler;
	AsyncTask<?, ?, ?> regIDInsertTask;
	ProgressDialog loagindDialog;
	String regId ;
	String myResult ;
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Firstck Firstck = new Firstck(this);
        Firstck2 Firstck2 = new Firstck2(this);
        getPreferences();
        backPressCloseHandler = new BackPressCloseHandler(this);    
        

        infoBtn = (Button)findViewById(R.id.infoBtn);
        
        infoBtn.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event)
        	{
        		switch(event.getAction()){
        		case MotionEvent.ACTION_DOWN:
        			infoBtn.setBackgroundResource(R.drawable.info2);
        			break;
        		case MotionEvent.ACTION_UP:
        			infoBtn.setBackgroundResource(R.drawable.info);
        			break;
        		}
        		return false;
        	}
        });
        
        infoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), SchoolinfoActivity.class);
				startActivityForResult(intent, 1003);
				finish();
				
			}
		});

        
        
       
        timeTableBtn = (Button)findViewById(R.id.timeTableBtn);
       
        timeTableBtn.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event)
        	{
        		switch(event.getAction()){
        		case MotionEvent.ACTION_DOWN:
        			timeTableBtn.setBackgroundResource(R.drawable.timetable2);
        			break;
        		case MotionEvent.ACTION_UP:
        			timeTableBtn.setBackgroundResource(R.drawable.timetable);
        			break;
        		}
        		return false;
        	}
        });
        
        timeTableBtn.setOnClickListener(new OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				Intent intent = new Intent(getBaseContext(), TimetableMain.class);
 				startActivityForResult(intent, 1003);
 				finish();
 			}
 		});
        
        
        foodBtn = (Button)findViewById(R.id.foodBtn);
        
        foodBtn.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event)
        	{
        		switch(event.getAction()){
        		case MotionEvent.ACTION_DOWN:
        			foodBtn.setBackgroundResource(R.drawable.food2);
        			break;
        		case MotionEvent.ACTION_UP:
        			foodBtn.setBackgroundResource(R.drawable.food);
        			break;
        		}
        		return false;
        	}
        });
        
        	foodBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				startActivityForResult(intent, 1003);
				finish();
			}
		});
        
        
        
        mapBtn = (Button)findViewById(R.id.mapBtn);
        
        mapBtn.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event)
        	{
        		switch(event.getAction()){
        		case MotionEvent.ACTION_DOWN:
        			mapBtn.setBackgroundResource(R.drawable.map2);
        			break;
        		case MotionEvent.ACTION_UP:
        			mapBtn.setBackgroundResource(R.drawable.map0);
        			break;
        		}
        		return false;
        	}
        });
        
    	mapBtn.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), MapStartActivity.class);
			startActivityForResult(intent, 1003);
			finish();
			
		}
	});
        
        settingBtn = (Button)findViewById(R.id.settingBtn);
        
        settingBtn.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event)
        	{
        		switch(event.getAction()){
        		case MotionEvent.ACTION_DOWN:
        			settingBtn.setBackgroundResource(R.drawable.setting2);
        			break;
        		case MotionEvent.ACTION_UP:
        			settingBtn.setBackgroundResource(R.drawable.setting);
        			break;
        		}
        		return false;
        	}
        });
        
        settingBtn.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			Intent intent = new Intent(getBaseContext(), SettingStartActivity.class);
    			startActivityForResult(intent, 1003);
    			
    		}
    	});
    }
    
    private void getPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String isnull = pref.getString("GCM", "");
        if(isnull.equals(""))
        {
        	registerGcm();
        	  SharedPreferences.Editor editor = pref.edit();
              editor.putString("GCM", "1");
              editor.commit();

        }
        else if(isnull.equals("1"))
        {
        	registerGcm();
      	  SharedPreferences.Editor editor = pref.edit();
            editor.putString("GCM", "2");
            editor.commit();
        }
        else if(isnull.equals("2"))
        {
        	registerGcm();
      	  SharedPreferences.Editor editor = pref.edit();
            editor.putString("GCM", "success");
            editor.putString("GCM setting","Von");
            editor.commit();
        }
    }	
    


	
  	 public void registerGcm() {

  	   	  GCMRegistrar.checkDevice(this);
  	   	  GCMRegistrar.checkManifest(this);

  	   	    regId = GCMRegistrar.getRegistrationId(this);

  	   	  if (regId.equals("")) {
  	   	   GCMRegistrar.register(this, "781888830280");
  	   	  } else {
  	   	   Log.e("reg_id", regId);
  	   	  }
  		  sendAPIkey();
  	   	 }

  	  
  		private void sendAPIkey() {

  					regIDInsertTask = new regIDInsertTask().execute(regId);

  				}




  	private class regIDInsertTask extends AsyncTask<String, Void, Void> {

  			

  			@Override

  			protected void onPreExecute() {

  				super.onPreExecute();

  				loagindDialog = ProgressDialog.show(Main.this, "키 등록 중입니다..",

  						"Please wait..", true, false);

  			}

  			@Override
  			protected Void doInBackground(String... params) {
  				HttpPostData(params[0]);
  					
  				return null;
  			}

  			protected void onPostExecute(Void result) {
  				loagindDialog.dismiss();
  		}
  	}


  	public void HttpPostData(String reg_id) { 
  	   try { 
  	        URL url = new URL("http://lemonlab.co.kr/ssu/gcm_reg_insert.php");       // URL 설정 

  	        HttpURLConnection http = (HttpURLConnection) url.openConnection();   // 접속 

  	        //-------------------------- 

  	        //   전송 모드 설정 - 기본적인 설정이다 

  	        //-------------------------- 

  	        http.setDefaultUseCaches(false);                                            

  	        http.setDoInput(true);                        

  	        http.setDoOutput(true);                     

  	        http.setRequestMethod("POST");         




  	        http.setRequestProperty("content-type", "application/x-www-form-urlencoded"); 

  	        StringBuffer buffer = new StringBuffer(); 

  	        buffer.append("reg_id").append("=").append(reg_id);                 // php 변수에 값 대입 

  	       

  	        OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR"); 

  	        PrintWriter writer = new PrintWriter(outStream); 

  	        writer.write(buffer.toString()); 

  	        writer.flush(); 

  	        InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");  

  	        BufferedReader reader = new BufferedReader(tmp); 

  	        StringBuilder builder = new StringBuilder(); 

  	        String str; 

  	        while ((str = reader.readLine()) != null) {    

  	             builder.append(str + "\n");                 

  	  } 

  	        

  	         myResult = builder.toString();              

  	       

  	   } catch (MalformedURLException e) { 

  	          // 

  	   } catch (IOException e) { 

  	          //  

  	   } // try 

  	} // HttpPostData 
   


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

}




