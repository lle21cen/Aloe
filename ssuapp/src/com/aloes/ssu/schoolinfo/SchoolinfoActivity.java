package com.aloes.ssu.schoolinfo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.aloes.ssu.BackPressCloseHandler;
import com.aloes.ssu.R;
import com.aloes.ssu.campusmap.MapStartActivity;
import com.aloes.ssu.food.MainActivity;
import com.aloes.ssu.setting.SettingStartActivity;
import com.aloes.ssu.timetable.TimetableMain;

public class SchoolinfoActivity extends Activity {
	
	private InfoButton alarm;
	private InfoButton whole;
	private InfoButton school;
	private InfoButton scholarship;
	private InfoButton grobal;
	private InfoButton job;
	private InfoButton inevent;
	private InfoButton outevent;
	private InfoButton service;
	private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity_schoolinfo);
        
        alarm = (InfoButton)findViewById(R.id.alarm);
        
        InfoButtonOnClickListener IBCL = new InfoButtonOnClickListener();
        backPressCloseHandler = new BackPressCloseHandler(this);
        
        
        whole=(InfoButton)findViewById(R.id.whole);
        school=(InfoButton)findViewById(R.id.school);
        scholarship=(InfoButton)findViewById(R.id.scholarship);
        grobal=(InfoButton)findViewById(R.id.grobal);
        job=(InfoButton)findViewById(R.id.job);
        inevent=(InfoButton)findViewById(R.id.inevent);
        outevent=(InfoButton)findViewById(R.id.outevent);
        service=(InfoButton)findViewById(R.id.service);
        
        alarm.setOnClickListener(IBCL);
        whole.setOnClickListener(IBCL);
        school.setOnClickListener(IBCL);
        scholarship.setOnClickListener(IBCL);
        grobal.setOnClickListener(IBCL);
        job.setOnClickListener(IBCL);
        inevent.setOnClickListener(IBCL);
        outevent.setOnClickListener(IBCL);
        service.setOnClickListener(IBCL);
        
        
      
        
        
      //아래 메뉴버튼 클릭
    	final Button foot1 = (Button)findViewById(R.id.foot1); 
    	foot1.setOnTouchListener(new OnTouchListener()
    	    {
    	    	public boolean onTouch(View v, MotionEvent event)
    	    	{
    	    		switch(event.getAction()){
    	    		case MotionEvent.ACTION_DOWN:
    	    			foot1.setBackgroundResource(R.drawable.infocolbot1);//왜 사진을 못읽을까;;;;;;
    	    			break;
    	    		case MotionEvent.ACTION_UP:
    	    			foot1.setBackgroundResource(R.drawable.infobot1);
    	    			break;
    	    		}
    	    		return false;
    	    	}
    	    });
    	
    	final Button foot2 = (Button)findViewById(R.id.foot2); 
    	foot2.setOnTouchListener(new OnTouchListener()
    	    {
    	    	public boolean onTouch(View v, MotionEvent event)
    	    	{
    	    		switch(event.getAction()){
    	    		case MotionEvent.ACTION_DOWN:
    	    			foot2.setBackgroundResource(R.drawable.infocolorbot2);
    	    			break;
    	    		case MotionEvent.ACTION_UP:
    	    			foot2.setBackgroundResource(R.drawable.infobot2);
    	    			break;
    	    		}
    	    		return false;
    	    	}
    	    });
    	
    	 foot1.setOnClickListener(new OnClickListener() {
  			@Override
  			public void onClick(View v) {
  				Intent intent = new Intent(getBaseContext(), TimetableMain.class);
  				startActivityForResult(intent, 1003);
  				finish();
  			}
  		});
    	
    	final Button foot3 = (Button)findViewById(R.id.foot3); 
    	foot3.setOnTouchListener(new OnTouchListener()
    	    {
    	    	public boolean onTouch(View v, MotionEvent event)
    	    	{
    	    		switch(event.getAction()){
    	    		case MotionEvent.ACTION_DOWN:
    	    			foot3.setBackgroundResource(R.drawable.infocolorbot3);
    	    			break;
    	    		case MotionEvent.ACTION_UP:
    	    			foot3.setBackgroundResource(R.drawable.infobot3);
    	    			break;
    	    		}
    	    		return false;
    	    	}
    	    });
    	foot3.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			startActivityForResult(intent, 1003);
			finish();
		}
	});
    	final Button foot4 = (Button)findViewById(R.id.foot4); 
    	foot4.setOnTouchListener(new OnTouchListener()
    	    {
    	    	public boolean onTouch(View v, MotionEvent event)
    	    	{
    	    		switch(event.getAction()){
    	    		case MotionEvent.ACTION_DOWN:
    	    			foot4.setBackgroundResource(R.drawable.infocolorbot4);
    	    			break;
    	    		case MotionEvent.ACTION_UP:
    	    			foot4.setBackgroundResource(R.drawable.infobot4);
    	    			break;
    	    		}
    	    		return false;
    	    	}
    	    });
    	foot4.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), MapStartActivity.class);
			startActivityForResult(intent, 1003);
			finish();
			
		}
	});
    	final Button foot5 = (Button)findViewById(R.id.foot5); 
    	foot5.setOnTouchListener(new OnTouchListener()
    	    {
    	    	public boolean onTouch(View v, MotionEvent event)
    	    	{
    	    		switch(event.getAction()){
    	    		case MotionEvent.ACTION_DOWN:
    	    			foot5.setBackgroundResource(R.drawable.infocolorbot5);
    	    			break;
    	    		case MotionEvent.ACTION_UP:
    	    			foot5.setBackgroundResource(R.drawable.infobot5);
    	    			break;
    	    		}
    	    		return false;
    	    	}
    	    });
    	
    	foot5.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			Intent intent = new Intent(getBaseContext(),SettingStartActivity.class);
    			startActivityForResult(intent, 1003);
    			finish();
    			
    		}
    	});
    	
        
    }//oncreate end
    
    class InfoButtonOnClickListener implements View.OnClickListener
    {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			
			switch(id){
			case R.id.alarm:
				if(alarm.isSelected() == true){
					alarm.setBackgroundResource(R.drawable.alarm_off);
					alarm.setSelected(!alarm.isSelected());
					}
				else{
					alarm.setBackgroundResource(R.drawable.alarm_on);
					alarm.setSelected(!alarm.isSelected());
				}
				break;
			case R.id.school:
				if(alarm.isSelected() == true){
					if(school.isSelected() == true){
						school.setBackgroundResource(R.drawable.school);
						school.setSelected(!school.isSelected());
					}
					else{
						school.setBackgroundResource(R.drawable.school3);
						school.setSelected(!school.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "학사");
	        		startActivityForResult(intent, 1002);
				}
				break;
			case R.id.whole:
				if(alarm.isSelected() == true){
					if(whole.isSelected() == true){
						whole.setBackgroundResource(R.drawable.whole);
						whole.setSelected(!whole.isSelected());
					}
					else{
						whole.setBackgroundResource(R.drawable.whole3);
						whole.setSelected(!whole.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "");
	        		startActivityForResult(intent, 1002);
				}
				break;
			case R.id.scholarship:
				if(alarm.isSelected() == true){
					if(scholarship.isSelected() == true){
						scholarship.setBackgroundResource(R.drawable.scholarship);
						scholarship.setSelected(!scholarship.isSelected());
					}
					else{
						scholarship.setBackgroundResource(R.drawable.scholarship3);
						scholarship.setSelected(!scholarship.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "장학");
	        		startActivityForResult(intent, 1002);
				}
				break;
			case R.id.grobal:
				if(alarm.isSelected() == true){
					if(grobal.isSelected() == true){
						grobal.setBackgroundResource(R.drawable.global);
						grobal.setSelected(!grobal.isSelected());
					}
					else{
						grobal.setBackgroundResource(R.drawable.global3);
						grobal.setSelected(!grobal.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "국제교류");
	        		startActivityForResult(intent, 1002);
				}
				break;
			case R.id.job:
				if(alarm.isSelected() == true){
					if(job.isSelected() == true){
						job.setBackgroundResource(R.drawable.job);
						job.setSelected(!job.isSelected());
					}
					else{
						job.setBackgroundResource(R.drawable.job3);
						job.setSelected(!job.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "모집·채용");
	        		startActivityForResult(intent, 1002);
				}
				break;
			case R.id.inevent:
				if(alarm.isSelected() == true){
					if(inevent.isSelected() == true){
						inevent.setBackgroundResource(R.drawable.inevent);
						inevent.setSelected(!inevent.isSelected());
					}
					else{
						inevent.setBackgroundResource(R.drawable.inevent3);
						inevent.setSelected(!inevent.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "교내행사");
	        		startActivityForResult(intent, 1002);
				}
				break;
			case R.id.outevent:
				if(alarm.isSelected() == true){
					if(outevent.isSelected() == true){
						outevent.setBackgroundResource(R.drawable.outevent);
						outevent.setSelected(!outevent.isSelected());
					}
					else{
						outevent.setBackgroundResource(R.drawable.outevent3);
						outevent.setSelected(!outevent.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "교외행사");
	        		startActivityForResult(intent, 1002);
				}
				break;
			case R.id.service:
				if(alarm.isSelected() == true){
					if(service.isSelected() == true){
						service.setBackgroundResource(R.drawable.volunteer);
						service.setSelected(!service.isSelected());
					}
					else{
						service.setBackgroundResource(R.drawable.volunteer3);
						service.setSelected(!service.isSelected());
					}
				}
				else{
					Intent intent=new Intent(getBaseContext(),Info.class);
	        		intent.putExtra("category", "봉사");
	        		startActivityForResult(intent, 1002);
				}
				break;
				
			}
			
		}
    	
    
    }
    
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }


	

    
    
}

