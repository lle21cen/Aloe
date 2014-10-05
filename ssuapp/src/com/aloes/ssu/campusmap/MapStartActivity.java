package com.aloes.ssu.campusmap;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aloes.ssu.BackPressCloseHandler;
import com.aloes.ssu.R;
import com.aloes.ssu.food.MainActivity;
import com.aloes.ssu.schoolinfo.SchoolinfoActivity;
import com.aloes.ssu.setting.SettingStartActivity;
import com.aloes.ssu.timetable.TimetableMain;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager.OnCalloutOverlayListener;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;



public class MapStartActivity extends NMapActivity implements OnMapStateChangeListener, OnCalloutOverlayListener, OnItemSelectedListener{

	NMapView mMapView = null;
	NMapController mMapController = null;
	NMapOverlayManager mOverlayManager;
	
	RelativeLayout MapContainer;
	
	NMapViewerResourceProvider mMapViewerResourceProvider = null;
	NMapOverlayManager mOverlManager;
	
	private TextView selected;
	TextView tv;
	private Spinner spinner;
	private BackPressCloseHandler backPressCloseHandler;
	String[] items = { "매점", "휴대폰충전기", "캠퍼스 ATM기" };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_start);
		MapContainer = (RelativeLayout)findViewById(R.id.layout1);
		tv = (TextView) findViewById(R.id.tv);
		
		backPressCloseHandler = new BackPressCloseHandler(this);
	    selected = (TextView) findViewById(R.id.selected);
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		// create map view
		mMapView = new NMapView(this);
		mMapController = mMapView.getMapController();
		
		mMapView.setApiKey("46e5bf6a975609b7e56559436f826292");
	
		MapContainer.addView(mMapView);
		
		mMapView.setClickable(true);
		mMapView.setBuiltInZoomControls(true,null);
		mMapView.setOnMapStateChangeListener(this);
		
		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
		
		mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
		
		int markerId = NMapPOIflagType.PIN;
		int markerId2 = NMapPOIflagType.PIN+4;
		NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
		poiData.beginPOIdata(2);
		poiData.addPOIitem(126.959788, 37.494566, "정보대학교",markerId2,0);
		poiData.addPOIitem(126.957170, 37.496958, "학생회관", markerId,0);
		poiData.endPOIdata();
		
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
		
		poiDataOverlay.showAllPOIdata(0);
		
		mOverlayManager.setOnCalloutOverlayListener(this);
		
		
		//아래 메뉴버튼 클릭
				final Button camfoot1 = (Button)findViewById(R.id.camfoot1); 
				camfoot1.setOnTouchListener(new OnTouchListener()
				    {
				    	public boolean onTouch(View v, MotionEvent event)
				    	{
				    		switch(event.getAction()){
				    		case MotionEvent.ACTION_DOWN:
				    			camfoot1.setBackgroundResource(R.drawable.campuscolbot1);//왜 인식을 못할까
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

	public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
		if (errorInfo == null) { // success
			//mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091),11);
			
		} else { // fail

		}
	}
	
	public void onZoomLevelChange(NMapView mapview, int level){}
	public void onMapCenterChange(NMapView mapview, NGeoPoint center){}
//	public void onAnimationStateChange(NMapView arg0, int animType, int animState){}
	public void onMapCenterChangeFine(NMapView arg0){}
	public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay arg0, NMapOverlayItem arg1, Rect arg2){
		Toast.makeText(this, arg1.getTitle(),Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public void onAnimationStateChange(NMapView arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		selected.setText(items[position]);
		if (items[position].equals(items[0]) == true) {
			
		} else if (items[position].equals(items[1]) == true) {
			
		} else if (items[position].equals(items[2]) == true) {
			
		}
		tv.setText("장소");

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
