package com.aloes.ssu.food;

import java.util.ArrayList;

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
import android.widget.Toast;

import com.aloes.ssu.Firstck;
import com.aloes.ssu.R;
import com.aloes.ssu.timetable.DatabaseHelper;

public class AllFoodList extends Activity {

	DatabaseHelper helper;
	SQLiteDatabase db;

	ListView RestaurantList;
	RestListAdapter adapter;

	ArrayList<String> restNames = new ArrayList<String>();
	ArrayList<String> restMenu = new ArrayList<String>();
	ArrayList<String> restPrice = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_restaurant_list);

		Firstck fs = new Firstck(this);
		helper = new DatabaseHelper(this, "database.db", null, 1);
		db = helper.getWritableDatabase();

		Resources res = getResources();

		TextView restType = (TextView) findViewById(R.id.resType);
		restType.setBackgroundDrawable(res.getDrawable(R.drawable.allfoodtop));
		adapter = new RestListAdapter(this);

		RestaurantList = (ListView) findViewById(R.id.item_list);

		RestaurantList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = null;

				RestListView myView = (RestListView) view;
				intent = new Intent(getBaseContext(), SelectedResActivity.class);
				intent.putExtra("name", restNames.get(position));
				intent.putExtra("menu", restMenu.get(position));
				intent.putExtra("price", restPrice.get(position));

				startActivityForResult(intent, 1003);
			}
		});
		setList();
	}

	public void setList() {
		try {
			String[] tableNames = { "alcohol", "chickenfood", "chinafood",
					"japanfood", "koreafood", "meetfood", "otherfood" };

			for (int tableNumber = 0; tableNumber < tableNames.length; tableNumber++) {
				String sql = "select * from " + tableNames[tableNumber];
				Cursor cursor = db.rawQuery(sql, null);
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					adapter.addItem(new RestItems(cursor.getString(0), ""));
					restNames.add(cursor.getString(0));
					restMenu.add(cursor.getString(1));
					restPrice.add(cursor.getString(2));
					cursor.moveToNext();
				}
			}

			RestaurantList.setAdapter(adapter);

		} catch (Exception ex) {
			Log.d("식당리스트오류", ex.getMessage());
		}
	}
}
