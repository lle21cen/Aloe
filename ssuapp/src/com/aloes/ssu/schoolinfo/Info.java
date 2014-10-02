package com.aloes.ssu.schoolinfo;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.aloes.ssu.R;

public class Info extends Activity implements OnScrollListener{

	private ArrayList<HashMap<String, String>> data;
	private ListView list;
	private SimpleAdapter sa;
	private Net_HTMLParse hp;
	//private TextView dateAndtimeLabel;
	private String category;
	private int currentScrollState;
	private int currentVidibleItemCount;
	private boolean isBottom;
	private boolean isLoading;
	private int scrollLocation=0;
	private int beforeScrollLocation=0;
	
	private View nameView;
	
	private final Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			listUpdate();
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_activity_info);
		Log.d("debug","start");
		
		nameView = (View)findViewById(R.id.nameView);
		isLoading=true;
		
		Intent intent = new Intent(this.getIntent());
		category=intent.getStringExtra("category");
		
		if(category.equals("")==true){
			nameView.setBackgroundResource(R.drawable.topall);
		}else if(category.equals("학사")==true){
			nameView.setBackgroundResource(R.drawable.tophac);
		}else if(category.equals("장학")==true){
			nameView.setBackgroundResource(R.drawable.topzang);
		}else if(category.equals("국제교류")==true){
			nameView.setBackgroundResource(R.drawable.topglobal);
		}else if(category.equals("모집·채용")==true){
			nameView.setBackgroundResource(R.drawable.topalba);
		}else if(category.equals("교내행사")==true){
			nameView.setBackgroundResource(R.drawable.topfest);
		}else if(category.equals("교외해상")==true){
			nameView.setBackgroundResource(R.drawable.topfestival);
		}else if(category.equals("봉사")==true){
			nameView.setBackgroundResource(R.drawable.topbong);
		}
		
		
		
		Log.d("debug","hum....");
		data = new ArrayList<HashMap<String, String>>();
        
        list = (ListView)findViewById(R.id.listView1);
        list.setOnScrollListener( this);
        
        hp = new Net_HTMLParse(Info.this, handler, data);
        
        sa = new SimpleAdapter(Info.this, data, R.layout.info_list_row,
        		new String[]{"subject","date"}, new int[]{R.id.tv_subject, R.id.tv_date});
        Log.d("debug","please");
        hp.open(category);
        
        
        
        list.setAdapter(sa);
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getBaseContext(),Detile.class);
				intent.putExtra("url", data.get(position).get("url"));
				Log.d("debug",data.get(position).get("url"));
				startActivityForResult(intent, 1003);
			}
		});
        
		
		isLoading=false;
		
		try {
			hp.Th.join();
		} catch (InterruptedException e) {

		}
		hp.open(category);
		Log.d("debug","wow~");
	}
	
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
		this.currentVidibleItemCount=visibleItemCount;
		int count=totalItemCount - visibleItemCount;
		Log.d("debug","Scroll");
		
		if(firstVisibleItem >= count && totalItemCount!=0){
			beforeScrollLocation=scrollLocation;
			scrollLocation=list.getMaxScrollAmount();
			isBottom=true;
		}
		else{
			isBottom=false;
		}
	}
	
	public void onScrollStateChanged(AbsListView view,int scrollState){
		this.currentScrollState=scrollState;
		this.isScrollCompleted();
	}
	
	private void isScrollCompleted(){
		if(this.currentVidibleItemCount > 0 && this.currentScrollState == 0 && (!isLoading) && isBottom){
			isLoading=true;
			
			dateUpdate();
			while(!hp.check);
			hp.check=false;
		//	list.setSelection(1000);
		//	list.scrollTo(0,-(scrollLocation-beforeScrollLocation));
		//	list.smoothScrollBy(0, scrollLocation-beforeScrollLocation);
		//	list.smoothScrollToPosition((scrollLocation-beforeScrollLocation));
			isLoading=false;
		}
	}
	
	
	private void listUpdate()
    {
    	sa.notifyDataSetChanged();
    }
    
    private void dateUpdate()
    {
    	hp.open(category);
        list.setAdapter(sa);
        hp.check=true;
    }
    
	
}
