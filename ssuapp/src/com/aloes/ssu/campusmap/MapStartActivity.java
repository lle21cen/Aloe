package com.aloes.ssu.campusmap;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.aloes.ssu.BackPressCloseHandler;
import com.aloes.ssu.R;
import com.aloes.ssu.food.MainActivity;
import com.aloes.ssu.schoolinfo.SchoolinfoActivity;
import com.aloes.ssu.setting.SettingStartActivity;
import com.aloes.ssu.timetable.TimetableMain;

public class MapStartActivity extends Activity implements
		OnItemSelectedListener {

	private TextView selected;
	TextView tv;
	private Spinner spinner;
	private RelativeLayout layout1;
	private RelativeLayout layout2;
	private RelativeLayout layout3;
	//private BackPressCloseHandler backpressevent;
	private BackPressCloseHandler backPressCloseHandler;

	String[] items = { "����", "�޴���������", "ķ�۽� ATM��" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_start);
		tv = (TextView) findViewById(R.id.tv);
		//backpressevent = new BackPressCloseHandler(this);
		backPressCloseHandler = new BackPressCloseHandler(this);
	    selected = (TextView) findViewById(R.id.selected);
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		layout1 = (RelativeLayout) findViewById(R.id.layout1);
		layout2 = (RelativeLayout) findViewById(R.id.layout2);
		layout3 = (RelativeLayout) findViewById(R.id.layout3);

		//������ư
		ImageButton btn1 = (ImageButton) findViewById(R.id.mae1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText("����, �л�ȸ�� 4��");
			}
		});
		ImageButton btn11 = (ImageButton) findViewById(R.id.mae2);
		btn11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText("����, �����1��(��Űģ ��)");
			}
		});
		ImageButton btn12 = (ImageButton) findViewById(R.id.mae3);
		btn12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText("����, �������а� �Ĵ��");
			}
		});
		
		
		//�޴����������ư
		ImageButton btn2 = (ImageButton) findViewById(R.id.ph1);
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tv.setText("�޴��� ������, �������а� 1��");
			}
		});
		ImageButton btn21 = (ImageButton) findViewById(R.id.ph2);
		btn21.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tv.setText("�޴��� ������, �߾ӵ����� 2,3,4��");
			}
		});
		
		
		//ATM�� ��ư
		ImageButton btn31 = (ImageButton) findViewById(R.id.atm1);
		btn31.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tv.setText("ķ�۽� ATM��, �������а� 1��");
			}
		});
		
		ImageButton btn32 = (ImageButton) findViewById(R.id.atm2);
		btn32.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tv.setText("ķ�۽� ATM��, �����Ȧ2��");
			}
		});
		
		
		//�Ʒ� �޴���ư Ŭ��
		final Button camfoot1 = (Button)findViewById(R.id.camfoot1); 
		camfoot1.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			camfoot1.setBackgroundResource(R.drawable.campuscolbot1);//�� �ν��� ���ұ�
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			camfoot1.setBackgroundResource(R.drawable.campusbot1);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		camfoot1.setOnClickListener(new OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				Intent intent = new Intent(getBaseContext(), TimetableMain.class);
 				startActivityForResult(intent, 1003);
 				finish();
 			}
 		});
		
		final Button camfoot2 = (Button)findViewById(R.id.camfoot2); 
		camfoot2.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			camfoot2.setBackgroundResource(R.drawable.campuscolorbot2);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			camfoot2.setBackgroundResource(R.drawable.campusbot2);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		camfoot2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SchoolinfoActivity.class);
				startActivityForResult(intent, 1003);	
				finish();
			}
		});
		
		final Button camfoot3 = (Button)findViewById(R.id.camfoot3); 
		camfoot3.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			camfoot3.setBackgroundResource(R.drawable.campuscolorbot3);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			camfoot3.setBackgroundResource(R.drawable.campusbot3);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		camfoot3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				startActivityForResult(intent, 1003);
				finish();
			}
		});
		final Button camfoot4 = (Button)findViewById(R.id.camfoot4); 
		camfoot4.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			camfoot4.setBackgroundResource(R.drawable.campuscolorbot4);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			camfoot4.setBackgroundResource(R.drawable.campusbot4);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		final Button camfoot5 = (Button)findViewById(R.id.camfoot5); 
		camfoot5.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			camfoot5.setBackgroundResource(R.drawable.campuscolorbot5);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			camfoot5.setBackgroundResource(R.drawable.campusbot5);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		camfoot5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getBaseContext(), SettingStartActivity.class);
				startActivityForResult(intent, 1003);
				finish();
			}
		});
		
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		selected.setText(items[position]);
		if (items[position].equals(items[0]) == true) {
			layout1.setVisibility(View.VISIBLE);
			layout2.setVisibility(View.INVISIBLE);
			layout3.setVisibility(View.INVISIBLE);
		} else if (items[position].equals(items[1]) == true) {
			layout1.setVisibility(View.INVISIBLE);
			layout2.setVisibility(View.VISIBLE);
			layout3.setVisibility(View.INVISIBLE);
		} else if (items[position].equals(items[2]) == true) {
			layout1.setVisibility(View.INVISIBLE);
			layout2.setVisibility(View.INVISIBLE);
			layout3.setVisibility(View.VISIBLE);
		}
		tv.setText("���");

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		selected.setText("");
		tv.setText("");
	}
	
	@Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }


}
