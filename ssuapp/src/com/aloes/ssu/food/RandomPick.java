package com.aloes.ssu.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.aloes.ssu.R;

public class RandomPick extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_random_pick);
		
		Button randomPick = (Button) findViewById(R.id.ramall);
		randomPick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "all");
				startActivityForResult(intent, 1002);
			}
		});
		
		Button ramkorea = (Button) findViewById(R.id.ramkoreafood);
		ramkorea.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "koreafood");
				startActivityForResult(intent, 1002);
			}
		});
		
		Button ramflour = (Button) findViewById(R.id.ramworldfood); // 세계음식
		ramflour.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "otherfood");
				startActivityForResult(intent, 1002);
			}
		});
		
		Button ramjapanes = (Button) findViewById(R.id.ramjapanesfood);
		ramjapanes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "japanfood");
				startActivityForResult(intent, 1002);
			}
		});
		
		Button ramchinese = (Button) findViewById(R.id.ramchinesefood);
		ramchinese.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "chinafood");
				startActivityForResult(intent, 1002);
			}
		});
		
		Button ramwestern = (Button) findViewById(R.id.ramwesternfood);
		ramwestern.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "meetfood");
				startActivityForResult(intent, 1002);
			}
		});
		
		Button ramalcohol = (Button) findViewById(R.id.ramalcohol);
		ramalcohol.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "alcohol");
				startActivityForResult(intent, 1002);
			}
		});
		
		Button ramChicken = (Button) findViewById(R.id.ramchicken);
		ramChicken.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), RestaurantNameDB.class);
				intent.putExtra("chose", "chickenfood");
				startActivityForResult(intent, 1002);				
			}
		});
		final Button foodbot1 = (Button)findViewById(R.id.foodbot01);

		foodbot1.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			foodbot1.setBackgroundResource(R.drawable.foodcolbot1);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			foodbot1.setBackgroundResource(R.drawable.foodbot1);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		    
		final Button foodbot2 = (Button)findViewById(R.id.foodbot02);

		foodbot2.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			foodbot2.setBackgroundResource(R.drawable.foodcolorbot2);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			foodbot2.setBackgroundResource(R.drawable.foodbot2);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		    
		final Button foodbot3 = (Button)findViewById(R.id.foodbot03);

		foodbot3.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			foodbot3.setBackgroundResource(R.drawable.foodcolorbot03);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			foodbot3.setBackgroundResource(R.drawable.foodbot3);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		    
		final Button foodbot4 = (Button)findViewById(R.id.foodbot04);

		foodbot4.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			foodbot4.setBackgroundResource(R.drawable.foodcolorbot4);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			foodbot4.setBackgroundResource(R.drawable.foodbot4);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		    
		final Button foodbot5 = (Button)findViewById(R.id.foodbot05);

		foodbot5.setOnTouchListener(new OnTouchListener()
		    {
		    	public boolean onTouch(View v, MotionEvent event)
		    	{
		    		switch(event.getAction()){
		    		case MotionEvent.ACTION_DOWN:
		    			foodbot5.setBackgroundResource(R.drawable.foodcolorbot5);
		    			break;
		    		case MotionEvent.ACTION_UP:
		    			foodbot5.setBackgroundResource(R.drawable.foodbot5);
		    			break;
		    		}
		    		return false;
		    	}
		    });
		
		
	}

	
}
