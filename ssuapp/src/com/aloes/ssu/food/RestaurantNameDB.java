package com.aloes.ssu.food;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aloes.ssu.R;

public class RestaurantNameDB extends Activity {

	SQLiteDatabase database;
	String tableName = "restaurant";
	boolean tableCreated = false;
	String mychose;
	String chose;
	DatabaseHelper helper;

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_database);

		Intent intent = getIntent();
		mychose = intent.getExtras().getString("chose");
		chose = mychose;

		Button dice = (Button) findViewById(R.id.dice);
		Resources res = getResources();
		TextView actName = (TextView) findViewById(R.id.actName);
		if(chose.equals("koreafood"))
			actName.setBackgroundDrawable(res.getDrawable(R.drawable.hanramtop));
		else if(chose.equals("otherfood"))
			actName.setBackgroundDrawable(res.getDrawable(R.drawable.worldramtop));
		else if(chose.equals("japanfood"))
			actName.setBackgroundDrawable(res.getDrawable(R.drawable.jpramtop));
		else if(chose.equals("chinafood"))
			actName.setBackgroundDrawable(res.getDrawable(R.drawable.charamtop));
		else if(chose.equals("meetfood"))
			actName.setBackgroundDrawable(res.getDrawable(R.drawable.usramtop));
		else if(chose.equals("alcohol"))
			actName.setBackgroundDrawable(res.getDrawable(R.drawable.achramtop));
		else if(chose.equals("chickenfood"))
			actName.setBackgroundDrawable(res.getDrawable(R.drawable.chiramtop));

		dice.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				queryData();

			}
		});
	}

	public void queryData() {
		Random rand = new Random();
		int randomGroup = rand.nextInt(5) + 1;
		if (mychose.equals("all"))
			switch (randomGroup) {
			case 1:
				chose = "koreafood";
				break;
			case 2:
				chose = "otherfood";
				break;
			case 3:
				chose = "japanfood";
				break;
			case 4:
				chose = "chinafood";
				break;
			case 5:
				chose = "meetfood";
				break;
			}
		
		try {
			helper = new DatabaseHelper(this, "database.db", null, 1);
			database = helper.getWritableDatabase();

			String sql = "select * from " + chose + " order by random()";

			Cursor cursor = database.rawQuery(sql, null);

			cursor.moveToNext();
			String name = cursor.getString(0);
			String menu = cursor.getString(1);
			menu = menu.replace("/", " ");
			Toast.makeText(
					getApplicationContext(),
					"무작위로 뽑힌 식당 이름 : " + name + "\n" + "메뉴 : " + menu, Toast.LENGTH_LONG).show();

		} catch (Exception ex) {
		}
	}

	private class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}
}
