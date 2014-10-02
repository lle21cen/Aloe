package com.aloes.ssu.food;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.aloes.ssu.Firstck;
import com.aloes.ssu.R;
import com.aloes.ssu.timetable.DatabaseHelper;

public class BarList extends Activity {

	DatabaseHelper helper;
	SQLiteDatabase db;
	
	ListView RestaurantList;
	RestListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_restaurant_list);

		Firstck fs = new Firstck(this);
		helper = new DatabaseHelper(this, "database.db", null, 1);
		db=helper.getWritableDatabase();
		
		Resources res = getResources();
		
		TextView restType = (TextView) findViewById(R.id.resType);
		restType.setBackgroundDrawable(res.getDrawable(R.drawable.achtop));
		adapter = new RestListAdapter(this);

		RestaurantList = (ListView) findViewById(R.id.item_list);

		RestaurantList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = null;
				
				String sql = "select * from alcohol";
				Cursor cursor = db.rawQuery(sql, null);
				cursor.moveToPosition(position);
				
				RestListView myView = (RestListView) view;
				intent = new Intent(getBaseContext(), SelectedResActivity.class);
				intent.putExtra("name", cursor.getString(0));
				intent.putExtra("menu", cursor.getString(1));
				intent.putExtra("price", cursor.getString(2));

				startActivityForResult(intent, 1003);
			}
		});
		setList();
	}
	public void setList(){
		try{
			String sql = "select * from alcohol";
			Cursor cursor = db.rawQuery(sql, null);
			cursor.moveToFirst();
			
			for(int i=0; i<cursor.getCount(); i++){
				adapter.addItem(new RestItems(cursor.getString(0), ""));
				cursor.moveToNext();
				
			}
			
			RestaurantList.setAdapter(adapter);

		}catch(Exception ex){
			Log.d("식당리스트오류", ex.getMessage());
		}
	}
}
