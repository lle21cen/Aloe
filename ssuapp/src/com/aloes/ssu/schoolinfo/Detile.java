package com.aloes.ssu.schoolinfo;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.aloes.ssu.R;

public class Detile extends Activity {

	private ArrayList<HashMap<String, String>> data;
	private Net_HTMLParse_for_details hp;
	private TextView title;
	private TextView date;
	private TextView contents;
	private TextView file;
	
	
	private final Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_activity_detile);
		
		Intent intent=new Intent(this.getIntent());
		String url=intent.getStringExtra("url");
		data = new ArrayList<HashMap<String, String>>();
		hp=new Net_HTMLParse_for_details(Detile.this,handler,data);
		hp.open(url);
		try{
			hp.Th.join();
		}catch(InterruptedException e){
		}
	//	
		title=(TextView)findViewById(R.id.title);
		date=(TextView)findViewById(R.id.date);
		contents=(TextView)findViewById(R.id.contents);
		
		// data.get(0);

		Log.d("debug Details", data.get(0).get("subject"));
		
		for(int i=0; i < Integer.parseInt(data.get(0).get("filesize"))  ;i++){
			if(i==0){
				file=(TextView)findViewById(R.id.file1);
			}
			else if(i==1){
				file=(TextView)findViewById(R.id.file2);
			}
			else if(i==2){
				file=(TextView)findViewById(R.id.file3);
			}
			else if(i==3){
				file=(TextView)findViewById(R.id.file4);
			}
			else if(i==4){
				file=(TextView)findViewById(R.id.file5);
			}
			else if(i==5){
				file=(TextView)findViewById(R.id.file6);
			}
			else if(i==6){
				file=(TextView)findViewById(R.id.file7);
			}
			else if(i==7){
				file=(TextView)findViewById(R.id.file8);
			}
			else if(i==8){
				file=(TextView)findViewById(R.id.file9);
			}
			else if(i==9){
				file=(TextView)findViewById(R.id.file10);
			}
			Log.d("debug",data.get(0).get("filename"+i));
			file.setText(Html.fromHtml("<a href = \""+data.get(0).get("file"+i)+"\">"+data.get(0).get("filename"+i)));
			file.setMovementMethod(LinkMovementMethod.getInstance());
			
			
			
			
			
			
		}
		
		/*
		file=(TextView)findViewById(R.id.file1);
		Log.d("debug",data.get(0).get("filename"+0));
		file.setText(data.get(0).get("filename"+0));
		Pattern tagMatcher = Pattern.compile("[#]+[A-Za-z0-9-_]+\\b");
		Linkify.addLinks(file, tagMatcher,"http://m.ssu.ac.kr"+data.get(0).get("file"+0));
		*/
		
		title.setText(data.get(0).get("subject"));
		date.setText(data.get(0).get("date"));
		contents.setText(Html.fromHtml(data.get(0).get("contents")));
		contents.setMovementMethod(LinkMovementMethod.getInstance());
		
		
	}

	

}
