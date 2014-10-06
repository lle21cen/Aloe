//package com.aloes.ssu.campusmap;
//
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import com.aloes.ssu.R;
//import com.nhn.android.maps.NMapActivity;
//import com.nhn.android.maps.NMapController;
//import com.nhn.android.maps.NMapOverlay;
//import com.nhn.android.maps.NMapOverlayItem;
//import com.nhn.android.maps.NMapView;
//import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
//import com.nhn.android.maps.maplib.NGeoPoint;
//import com.nhn.android.maps.nmapmodel.NMapError;
//import com.nhn.android.maps.overlay.NMapPOIdata;
//import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
//import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
//import com.nhn.android.mapviewer.overlay.NMapOverlayManager.OnCalloutOverlayListener;
//import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
//
//public class navermaptest extends NMapActivity implements OnMapStateChangeListener, OnCalloutOverlayListener{
//
//	NMapView mMapView = null;
//	NMapController mMapController = null;
//	NMapOverlayManager mOverlayManager;
//	
//	RelativeLayout MapContainer;
//	
//	NMapViewerResourceProvider mMapViewerResourceProvider = null;
//	NMapOverlayManager mOverlManager;
//	
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.map_start);
//		MapContainer = (RelativeLayout)findViewById(R.id.layout1);
//		// create map view
//		mMapView = new NMapView(this);
//		mMapController = mMapView.getMapController();
//		
//		mMapView.setApiKey("46e5bf6a975609b7e56559436f826292");
//	
//		MapContainer.addView(mMapView);
//		
//		mMapView.setClickable(true);
//		mMapView.setBuiltInZoomControls(true,null);
//		mMapView.setOnMapStateChangeListener(this);
//		
//		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
//		
//		mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
//		
//		int markerId = NMapPOIflagType.PIN;
//		int markerId2 = NMapPOIflagType.PIN+4;
//		NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
//		poiData.beginPOIdata(2);
//		poiData.addPOIitem(126.959788, 37.494566, "정보대학교",markerId2,0);
//		poiData.addPOIitem(126.957170, 37.496958, "학생회관", markerId,0);
//		poiData.endPOIdata();
//		
//		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
//		
//		poiDataOverlay.showAllPOIdata(0);
//		
//		mOverlayManager.setOnCalloutOverlayListener(this);
//		
//	}
//
//	public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
//		if (errorInfo == null) { // success
//			//mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091),11);
//			
//		} else { // fail
//
//		}
//	}
//	
//	public void onZoomLevelChange(NMapView mapview, int level){}
//	public void onMapCenterChange(NMapView mapview, NGeoPoint center){}
////	public void onAnimationStateChange(NMapView arg0, int animType, int animState){}
//	public void onMapCenterChangeFine(NMapView arg0){}
//	public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay arg0, NMapOverlayItem arg1, Rect arg2){
//		Toast.makeText(this, arg1.getTitle(),Toast.LENGTH_SHORT).show();
//		return null;
//	}
//
//	@Override
//	public void onAnimationStateChange(NMapView arg0, int arg1, int arg2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
